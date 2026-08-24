package com.evt_open_service.eventopenservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class EventopenserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventopenserviceApplication.class, args);
	}

}
