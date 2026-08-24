# Assignment: Build "Evently" — A 4-Service Event Ticketing Backend

**Audience:** Developer who has completed Java + Spring Boot basics
**Goal:** After finishing this, you should be comfortable working in a real microservices codebase — tracing a request end-to-end across services, and writing unit + integration tests for any layer of it.

> **Rule #1: You must write this code yourself.** You may use AI/Google/Stack Overflow to *understand* concepts and debug errors, but if you can't explain every line in your review session, the assignment is considered incomplete. The review will be a live walkthrough where you explain your code and we change a requirement on the spot.

---

## What You're Building

A backend for a fictional event-ticketing platform ("Evently") where organizers list events (concerts, sports, comedy shows) and the platform tracks them city-wise. Four services, layered the way real production systems are:

```
curl / Postman
    ↓ REST (JSON)
[1] evt-bff                     ← edge service: validates JWT, role check, calls open-service via Feign
    ↓ REST (Feign + OkHttp)
[2] evt-open-service            ← orchestrator: gRPC client + Kafka producer
    ↓ gRPC (protobuf)
[3] evt-core-service            ← business logic + JPA + PostgreSQL

Kafka topic: event.published
    ↓ consumed by
[4] evt-notification-service    ← Kafka consumer → writes to MongoDB
                                   (pre-computed dashboard documents)

Infra (Docker Compose): PostgreSQL, MongoDB, Kafka, Redis
```

### Tech Stack (must match)

| Concern | Tool | Version |
|---|---|---|
| Language | Java | 21+ (latest LTS or newer) |
| Framework | Spring Boot | 3.x |
| Build | Maven (multi-module is a bonus) | — |
| DB (write side) | PostgreSQL via Spring Data JPA | 16 |
| DB (read side) | MongoDB via Spring Data Mongo | 7 |
| Messaging | Apache Kafka (Spring Kafka) | — |
| Service-to-service | OpenFeign (REST) + gRPC (protobuf) | — |
| Cache | Redis (used for OTP storage in Phase 4) | — |
| Containers | Docker + Docker Compose | — |
| Testing | JUnit 5, Mockito, MockMvc, Testcontainers | — |
| Boilerplate | Lombok | — |

---

## Domain Model (keep it small)

**Event** (PostgreSQL, owned by `evt-core-service`)
- `id` (UUID), `eventName`, `organizerName`, `organizerMobile` (unique), `city`, `category` (enum: MUSIC / SPORTS / COMEDY / WORKSHOP / OTHER)
- `status` (enum: DRAFT → PUBLISHED → CANCELLED; PUBLISHED → SOLD_OUT)
- `createdOn`, `modifiedOn` (from a common `BaseEntity` that **every** entity extends)

**EventNotification** (MongoDB, owned by `evt-notification-service`)
- One document per notification: `eventId` (the Kafka message id), `entityId`, `eventName`, `eventType`, `receivedAt`, `processed`

**CityDashboard** (MongoDB, pre-computed read model)
- One document **per city**, shaped exactly like a dashboard screen would need it: `city`, `totalEvents`, `publishedEvents`, `eventsByCategory` (map), `lastUpdatedAt`. Updated by the Kafka consumer on every message. **No GROUP BY at read time** — the document is always read-ready. (This is the "document-per-view" pattern.)

---

## Mandatory Conventions (we will check every one of these)

These are real industry/team conventions. Internalizing them is half the assignment.

1. **API response envelope** — every endpoint returns:
   ```json
   { "success": true, "data": { ... } }                  // success
   { "success": false, "message": "Event not found" }    // failure
   ```
2. **Class naming** — `XxxRequest` (API in), `XxxResponse` (API out), `XxxDTO` (internal between layers), entity classes for DB. A `Response` class must **never** contain another `Response` class as a field — compose with DTOs.
3. **Constructor injection only** — `@RequiredArgsConstructor` + `private final`. No field `@Autowired`.
4. **Global exception handling** — one `@RestControllerAdvice` per service. Business code throws typed exceptions (`ResourceNotFoundException`, `BadRequestException`); controllers contain **zero** try/catch.
5. **No fallback defaults** — if a required header / config / claim is missing, fail loudly with a clear error. Never silently default a userId or role.
6. **Enum mapping across layers** (e.g., proto enum → JPA enum) — explicit `switch` mapper class. Never `Enum.valueOf(other.name())`.
7. **Validation at the edge** — `@Valid` + `@NotBlank`/`@Pattern` on Request classes in the BFF. Inner services trust their callers' contracts but still enforce DB constraints.
8. **Pagination** — every list endpoint takes `page` + `size` and returns `totalElements`, `totalPages` in `data`. Filtering happens **in the DB query**, never "fetch all then filter in Java".
9. **Logging** — `@Slf4j`, structured messages with key=value (`log.info("Event published eventId={} city={}", ...)`). Every request carries a `traceId` (Phase 4) that appears in the logs of **all four** services for one request.
10. **Package naming** — `com.evently.<servicename>`, consistent across all modules.

---

## Phase 0 — Environment & Skeleton

1. Install: JDK, Maven, Docker Desktop, an IDE, Postman/curl, `grpcurl` (optional but useful).
2. Create a git repo `evently`. **Commit at the end of every phase** with a meaningful message. We will read your git history.
3. Create the 4 Spring Boot service folders + a `docker-compose.yml` at the root.
4. Write `docker-compose.yml` with **infra only** for now: `postgres`, `mongo`, `kafka` (single broker, KRaft mode is fine), `redis`. Add healthchecks. Verify all 4 come up healthy with `docker compose up`.

**Deliverable:** `docker compose up` → 4 healthy infra containers. A root `README.md` with a one-paragraph description and the architecture diagram (ASCII is fine).

---

## Phase 1 — evt-core-service: REST + JPA + Postgres + Docker

Build `evt-core-service` as a plain REST service first (you'll convert it to gRPC in Phase 2 — this is intentional; you'll feel *why* the layers exist).

### Endpoints
| Method | Path | Notes |
|---|---|---|
| POST | `/v1/events` | Create event (status DRAFT). Organizer mobile must be unique → 409 with proper envelope if duplicate. |
| GET | `/v1/events/{id}` | 404 envelope if missing. |
| GET | `/v1/events?city=&category=&status=&page=&size=` | All filters optional, **pushed to the DB query** (use JPA Specifications or query methods — do NOT write `(:param IS NULL OR ...)` JPQL). |
| PATCH | `/v1/events/{id}/status` | Enforce transitions: DRAFT→PUBLISHED, PUBLISHED→CANCELLED, PUBLISHED→SOLD_OUT. Illegal transition → 400. |
| GET | `/v1/events/stats` | `{ totalEvents, byStatus: {...}, byCategory: {...} }` |

### Requirements
- `BaseEntity` (`@MappedSuperclass`) with `id`, `createdOn`, `modifiedOn` + auditing (`@EnableJpaAuditing`).
- Layering: `Controller → Service → Repository`. Controller does HTTP-shape work only; all logic in Service.
- Schema via `ddl-auto: update` is acceptable for this assignment (read about what Liquibase is for — 1 paragraph in your README).
- **Dockerfile** for the service (multi-stage: maven build stage → JRE run stage). Add it to docker-compose, `depends_on` postgres with healthcheck condition.
- Connect via env vars (`SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/...`), never hardcoded `localhost`.

**Deliverable:** `docker compose up` → service + postgres. A `curl-examples.md` showing every endpoint working, including the error cases (duplicate mobile, bad transition, 404).

**Self-check questions (answer in README):**
- Why does `localhost` not work inside a container but the service name `postgres` does?
- What does `FetchType.LAZY` do and when does it bite you?
- What happens if two requests create an event with the same organizer mobile at the same time? What actually protects you — the Java check or the DB constraint?

---

## Phase 2 — Split the Stack: BFF → open-service → gRPC backend

Now mirror a real production topology.

### 2a. Convert evt-core-service to gRPC
- Write `event.proto`: `EventService` with `CreateEvent`, `GetEvent`, `ListEvents`, `UpdateEventStatus`, `GetEventStats`. Use proper protobuf style: `snake_case` fields, enums with a `_UNSPECIFIED = 0` value, separate `XxxRequest`/`XxxResponse` messages per RPC.
- Put the proto in a separate Maven module `evt-grpc-contracts` consumed by both server and client (shared-contracts pattern — one source of truth for the wire format).
- Implement the server with `@GrpcService` + `StreamObserver`. Map errors to gRPC `Status` codes: `NOT_FOUND`, `ALREADY_EXISTS`, `INVALID_ARGUMENT`, `INTERNAL`.
- Write an explicit enum mapper class proto-enum ↔ JPA-enum (convention #6).
- Remove the REST controllers from this service — it is now gRPC-only.

### 2b. Build evt-open-service (orchestrator)
- REST endpoints mirroring Phase 1's surface, under `/open/v1/events/**`.
- It holds a **gRPC client** (blocking stub) to evt-core-service and translates: REST Request → proto request → proto response → REST Response.
- gRPC errors must come back as proper HTTP envelopes: `NOT_FOUND` → 404 `{success:false,...}`, `ALREADY_EXISTS` → 409, etc. Centralize this translation — do not repeat it per endpoint.

### 2c. Build evt-bff
- REST endpoints under `/api/v1/events/**`, calling evt-open-service via **OpenFeign**.
- Configure a Feign **ErrorDecoder** so a 404 from open-service becomes your own typed exception → your own 404 envelope (not a generic 500).
- This is the only service the "frontend" (your curl) talks to.

### 2d. Wire it all in Docker Compose
All 3 services + postgres in compose. One `curl` to the BFF must traverse: BFF → (HTTP) → open-service → (gRPC) → core-service → postgres, and back.

**Deliverable:** updated `curl-examples.md` against the **BFF** only. README section: "Life of a request" — trace one POST through all 3 services in your own words, naming the actual classes it passes through.

**Self-check questions:**
- Why use gRPC between internal services but REST at the edge?
- What breaks if the proto file adds a field — which services need rebuilding, and why is the shared-contracts module the answer?
- If open-service is down, what does the BFF return today? What *should* it return?

---

## Phase 3 — Kafka + evt-notification-service + MongoDB

### 3a. Produce events
- Define a standard message wrapper used for **every** Kafka message:
  ```json
  { "eventId": "<uuid>", "eventType": "EVENT_PUBLISHED", "occurredAt": "...", "payload": { ...denormalized event snapshot... } }
  ```
- Topic names in a shared enum/constants class: `event.published`, `event.status.changed`.
- evt-open-service publishes after a successful create / status change, using `KafkaTemplate` + `JsonSerializer`. **Key the message by the entity id** — be ready to explain why (per-entity ordering).
- **Denormalize at produce time**: the payload carries `eventName`, `city`, `category` so the consumer never has to call back to ask.

### 3b. Build evt-notification-service (consumer)
- `@KafkaListener` with **manual acknowledgment**, `ErrorHandlingDeserializer`, a consumer group, and trusted-packages config.
- On each message:
  1. **Idempotency check** — if `eventId` already exists in Mongo, ack and skip. (Kafka is at-least-once; be ready to explain a redelivery scenario.)
  2. Insert an `EventNotification` document.
  3. **Upsert the `CityDashboard` document** for that city — increment counters, update `eventsByCategory`. Document-per-view: the dashboard read is a single `findById(city)`.
- Expose two read endpoints: `GET /v1/notifications?entityId=` and `GET /v1/dashboard/{city}` (returns the document as-is — no aggregation at read time).
- Poison-pill handling: a message that fails 3 times goes to `event.published.dlt` (use `DefaultErrorHandler` + `DeadLetterPublishingRecoverer`). Prove it works by sending one malformed message.

### 3c. Compose
All 4 services + postgres + mongo + kafka + redis in one `docker compose up`.

**Deliverable:** demo script in `curl-examples.md`: create + publish 3 events in 2 cities via the BFF → show `GET /v1/dashboard/{city}` reflects correct counts within seconds. Show the DLT working.

**Self-check questions:**
- Your consumer crashes after writing to Mongo but before ack. What happens on restart, and which line of your code saves you?
- Why is the dashboard a pre-computed document instead of a Mongo aggregation/`GROUP BY` at read time? When would the aggregation approach fall over?
- Why does the producer write the DB row **before** publishing to Kafka, and what is the failure mode if you publish first?

---

## Phase 4 — Auth: OTP login + JWT + Roles + traceId

- `POST /api/v1/auth/generate-otp` `{mobile}` → generate a 6-digit OTP, store in **Redis** with 5-min TTL (key `otp:<mobile>`), log it (no SMS).
- `POST /api/v1/auth/verify-otp` `{mobile, otp}` → on match, mint a JWT (HMAC, `jjwt` library) with claims `userId`, `role`, 15-min expiry + a refresh token. Hardcode 2 users in a table: one `ADMIN`, one `VIEWER`. Role comes **from the user record, never from the request** (convention #5).
- A `OncePerRequestFilter` in the BFF: validates JWT, rejects 401 envelope if missing/expired, puts `userId` + `role` into request context **and MDC**.
- `@RoleRequired("ADMIN")` — write this annotation + an interceptor that enforces it. POST/PATCH endpoints = ADMIN; GETs = any authenticated user. Violation → 403 envelope.
- **traceId**: filter generates a UUID per request → MDC → logging pattern prints it → BFF forwards it as `X-Trace-Id` header via a Feign `RequestInterceptor` → open-service picks it up into its MDC → also embed it in the Kafka message → consumer restores it into MDC. **Acceptance test: grep one traceId across all four services' logs and see the full journey.**

**Deliverable:** curl flow: generate-otp → (read OTP from logs) → verify-otp → call protected endpoint with `Authorization: Bearer ...`; one 401 example, one 403 example, and a paste of one traceId appearing in 4 services' logs.

---

## Phase 5 — Testing (the most important phase)

This is the phase that earns you the right to touch production code. Target: **meaningful tests, not coverage theater**. Each test name must say what behavior it proves: `createEvent_duplicateMobile_returns409`.

### 5a. Unit tests (Mockito) — evt-core-service `EventService`
- Mock the repository. Test: happy path, duplicate mobile → exception, illegal status transition → exception, legal transition passes.
- At least one `ArgumentCaptor` test: verify exactly what entity was passed to `repository.save(...)`.
- At least one `verify(..., never())` test: on validation failure, nothing is saved.

### 5b. Web-slice tests (`@WebMvcTest` + MockMvc) — BFF controller
- Mock the Feign client / service layer. Test: 200 envelope shape (`$.success == true`), validation failure → 400 with message, downstream-not-found → 404 envelope.
- Test the JWT filter: no token → 401; VIEWER token on an ADMIN endpoint → 403 (mint real test tokens with a test secret).

### 5c. Repository tests (`@DataJpaTest` + Testcontainers Postgres)
- Real Postgres in a container — **not H2** (be ready to explain one way H2 and Postgres differ that would make an H2-passing test lie to you).
- Test the filtered list query: seed 5 events, filter by city+status, assert the page content and `totalElements`.
- Test the unique constraint actually fires at DB level.

### 5d. Kafka integration test — evt-notification-service
- `@SpringBootTest` + Testcontainers Kafka (or `@EmbeddedKafka`).
- Publish a real message → await (use Awaitility) → assert the Mongo notification document AND the CityDashboard counters.
- Publish the **same eventId twice** → assert counters incremented once (idempotency proven by test, not by claim).

### 5e. One end-to-end test
- `@SpringBootTest(webEnvironment = RANDOM_PORT)` on the BFF with Testcontainers (or against the running compose stack): login → create event → publish → poll dashboard endpoint until count updates. This single test exercises REST, Feign, gRPC, JPA, Kafka, and Mongo together.

**Deliverable:** `mvn test` green in every module. A `TESTING.md` table: each test class → what behavior it locks down → what type (unit / slice / integration). State your line coverage per module (JaCoCo) — we care more about your table than the number.

---

## Final Submission Checklist

- [ ] Git repo with phase-by-phase commits
- [ ] `docker compose up` from a clean clone brings up everything (this will be the first thing we run)
- [ ] `README.md` — architecture diagram, "life of a request" write-up, self-check answers
- [ ] `curl-examples.md` — every flow including error cases
- [ ] `TESTING.md` — test inventory table
- [ ] All conventions in the "Mandatory Conventions" section followed

## Review / Evaluation (how you'll be graded)

| Weight | Criterion |
|---|---|
| 25% | **Tests** — do they prove behavior? Would they catch a regression? |
| 20% | Correct layering & conventions (envelope, Request/Response naming, exception handling, no fallback defaults) |
| 20% | It works: clean `docker compose up`, all flows demo-able |
| 15% | Live walkthrough — you explain any class we point at, and implement one small requirement change live (e.g., "add a `venuePincode` field end-to-end") |
| 10% | Kafka/Mongo correctness — idempotency, DLT, document-per-view |
| 10% | Git hygiene, README quality, self-check answers |

Good luck. Ask questions early — being blocked silently for days is the only real failure mode here.
