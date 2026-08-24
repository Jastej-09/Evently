package com.evt_notification_service.evt_notification_service.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "event_notifications")
public class EventNotification {

    private String eventId;

    private String eventType;

    private Instant occurredAt;

    private String eventName;

    private String city;

    private String category;
}