package com.evt_notification_service.evt_notification_service.config;

import com.evt_notification_service.evt_notification_service.kafka.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, KafkaMessage> consumerFactory() {

        Map<String, Object> properties = new HashMap<>();

        properties.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:29092"
        );

        properties.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "notification-service"
        );

        properties.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        properties.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                ErrorHandlingDeserializer.class
        );

        properties.put(
                ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
                JacksonJsonDeserializer.class
        );

        properties.put(
                JacksonJsonDeserializer.VALUE_DEFAULT_TYPE,
                KafkaMessage.class.getName()
        );

        properties.put(
                JacksonJsonDeserializer.TRUSTED_PACKAGES,
                "com.evt_notification_service.evt_notification_service.kafka"
        );

        properties.put(
                ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                false
        );

        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public DefaultErrorHandler errorHandler(
            KafkaTemplate<Object, Object> kafkaTemplate
    ) {
        System.out.println("Error consumer side");
        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(kafkaTemplate);

        FixedBackOff backOff = new FixedBackOff(
                1000L,
                2L
        );

        return new DefaultErrorHandler(
                recoverer,
                backOff
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessage>
    kafkaListenerContainerFactory(
            ConsumerFactory<String, KafkaMessage> consumerFactory,
            CommonErrorHandler errorHandler
    ) {

        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage>
                factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory);

        factory.getContainerProperties()
                .setAckMode(ContainerProperties.AckMode.MANUAL);

        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}