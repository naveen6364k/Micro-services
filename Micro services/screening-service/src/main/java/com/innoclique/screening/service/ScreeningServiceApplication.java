package com.innoclique.screening.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ScreeningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScreeningServiceApplication.class, args);
	}

}
