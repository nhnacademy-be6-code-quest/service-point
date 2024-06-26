package com.service.servicecoupon;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class ServicePointApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePointApplication.class, args);
	}

}
