package com.evt_open_service.eventopenservice.kafka;


import java.time.Instant;

public record KafkaMessage(
        String eventId,
        EventType eventType,
        Instant occurredAt,
        EventSnapshot payload
) {
}