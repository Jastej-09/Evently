package com.evt_bff.evtbff.dto.response;

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