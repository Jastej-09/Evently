package com.evt_notification_service.evt_notification_service.consumer;

import com.evt_notification_service.evt_notification_service.kafka.EventSnapshot;
import com.evt_notification_service.evt_notification_service.kafka.KafkaMessage;
import com.evt_notification_service.evt_notification_service.service.DashboardService;
import com.evt_notification_service.evt_notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class EventNotificationConsumer {
    private final NotificationService eventNotificationService;
    private final DashboardService  dashboardService;
    @KafkaListener(topics = "event.published",
            groupId = "notification-service")

    public void consume(KafkaMessage kafkaMessage, Acknowledgment acknowledgment) {
        try {
            MDC.put(
                    "traceId",
                    kafkaMessage.traceId()
            );
            log.info("Event message received: {}", kafkaMessage);
            EventSnapshot snapshot = kafkaMessage.payload();
            boolean processed = eventNotificationService.processEvent(kafkaMessage);
            if (processed) {
                dashboardService.updateDashboard(kafkaMessage);
            }
            acknowledgment.acknowledge();
        }finally {

        }
    }

}
