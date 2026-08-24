package com.evt_notification_service.evt_notification_service.kafka;

public record EventSnapshot(
        String eventName,
        String city,
        String category
) {
}