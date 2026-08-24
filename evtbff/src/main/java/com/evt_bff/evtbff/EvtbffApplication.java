package com.evt_bff.evtbff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EvtbffApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvtbffApplication.class, args);
	}

}
