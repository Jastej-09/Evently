package com.evt_notification_service.evt_notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class EvtNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvtNotificationServiceApplication.class, args);
	}

}
