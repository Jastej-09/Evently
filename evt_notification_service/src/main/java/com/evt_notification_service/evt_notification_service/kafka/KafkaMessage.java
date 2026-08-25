package com.evt_notification_service.evt_notification_service.kafka;

import java.time.Instant;

public record KafkaMessage(
        String eventId,
        EventType eventType,
        Instant occurredAt,
        String traceId,
        EventSnapshot payload
) {
}
