package com.event_core_service.eventcoreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EventcoreserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventcoreserviceApplication.class, args);
	}

}
