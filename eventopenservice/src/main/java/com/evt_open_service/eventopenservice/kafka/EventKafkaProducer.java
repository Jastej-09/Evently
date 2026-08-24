package com.evt_open_service.eventopenservice.kafka;

import com.evt_open_service.eventopenservice.service.kafkaEventProducerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final Logger log =
            LoggerFactory.getLogger(kafkaEventProducerService.class);

    public void send(
            String topic,
            String eventId,
            KafkaMessage message
    ) {

        log.info(
                "Publishing Kafka message eventId={} eventType={} topic={}",
                eventId,
                message.eventType(),

                topic
        );
        System.out.println();

        kafkaTemplate.send(
                topic,
                eventId,
                message
        );
        log.info(
                "Published Kafka message eventId={} eventType={} topic={}",
                eventId,
                message.eventType(),
                topic
        );
    }
}