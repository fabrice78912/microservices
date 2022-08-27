package com.fabricedev.orderserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderServApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServApplication.class, args);
	}

}
