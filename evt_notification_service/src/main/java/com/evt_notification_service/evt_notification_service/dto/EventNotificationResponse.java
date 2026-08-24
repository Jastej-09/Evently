package com.evt_notification_service.evt_notification_service.dto;
import java.time.Instant;
import java.util.UUID;

public record EventNotificationResponse(
        UUID eventId,
        String eventType,
        Instant occurredAt,
        String eventName,
        String city,
        String category
) {
}