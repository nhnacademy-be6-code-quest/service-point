package com.service.point;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicePointApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePointApplication.class, args);
	}

}
