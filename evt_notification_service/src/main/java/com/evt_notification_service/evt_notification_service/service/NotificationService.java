package com.evt_notification_service.evt_notification_service.service;

import com.evt_notification_service.evt_notification_service.dto.EventNotificationResponse;
import com.evt_notification_service.evt_notification_service.kafka.EventSnapshot;
import com.evt_notification_service.evt_notification_service.document.EventNotification;
import com.evt_notification_service.evt_notification_service.kafka.EventSnapshot;
import com.evt_notification_service.evt_notification_service.kafka.KafkaMessage;
import com.evt_notification_service.evt_notification_service.repository.EventNotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final EventNotificationRepository eventNotificationRepository;

    public void processEvent(KafkaMessage message) {
        System.out.println("ProcessEvent Received");
        String eventId =message.eventId();


        if (eventNotificationRepository.existsByEventId((eventId))){
            log.info(
                    "Notification already exists for eventId={}",
                    eventId
            );

            return;
        }

        EventSnapshot snapshot = message.payload();

        EventNotification notification = new EventNotification();

        notification.setEventId(eventId);
        notification.setEventType(message.eventType().toString());
        notification.setOccurredAt(message.occurredAt());

        notification.setEventName(snapshot.eventName());
        notification.setCity(snapshot.city());
        notification.setCategory(snapshot.category());

        eventNotificationRepository.save(notification);

        log.info(
                "Notification created successfully for eventId={}",
                eventId
        );
    }
    public EventNotificationResponse getNotification(String eventId) {

        EventNotification notification =
                eventNotificationRepository.findByEventId(eventId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Notification not found for eventId: " + eventId
                                )
                        );
        UUID uuidEventId = UUID.fromString(notification.getEventId());

        return new EventNotificationResponse(
                uuidEventId,
                notification.getEventType(),
                notification.getOccurredAt(),
                notification.getEventName(),
                notification.getCity(),
                notification.getCategory()
        );
    }
}