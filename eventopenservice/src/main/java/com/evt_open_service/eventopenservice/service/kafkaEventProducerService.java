package com.evt_open_service.eventopenservice.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.evt_open_service.eventopenservice.kafka.*;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class kafkaEventProducerService {

    private final EventKafkaProducer eventKafkaProducer;

    public void publishEventPublished(
            String eventId,
            String eventName,
            String city,
            String category
    ) {

        EventSnapshot snapshot = new EventSnapshot(
                eventName,
                city,
                category
        );

        KafkaMessage message = new KafkaMessage(
                eventId,
                EventType.EVENT_PUBLISHED,
                Instant.now(),
                snapshot
        );

        eventKafkaProducer.send(
                KafkaTopic.EVENT_PUBLISHED,
                eventId,
                message
        );
    }

    public void publishEventStatusChanged(
            String eventId,
            String eventName,
            String city,
            String category
    ) {

        EventSnapshot snapshot = new EventSnapshot(
                eventName,
                city,
                category
        );

        KafkaMessage message = new KafkaMessage(
                eventId,
                EventType.EVENT_STATUS_CHANGED,
                Instant.now(),
                snapshot
        );

        eventKafkaProducer.send(
                KafkaTopic.EVENT_STATUS_CHANGED,
                eventId,
                message
        );
    }
}