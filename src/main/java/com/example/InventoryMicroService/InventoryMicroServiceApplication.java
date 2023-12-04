package com.example.InventoryMicroService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class InventoryMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryMicroServiceApplication.class, args);
	}

}
