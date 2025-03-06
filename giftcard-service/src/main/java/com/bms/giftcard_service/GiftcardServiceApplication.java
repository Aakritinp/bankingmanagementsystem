package com.bms.giftcard_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GiftcardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GiftcardServiceApplication.class, args);
	}

}
