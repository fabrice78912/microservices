package com.fabricedev.productserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductServApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServApplication.class, args);
	}

}
