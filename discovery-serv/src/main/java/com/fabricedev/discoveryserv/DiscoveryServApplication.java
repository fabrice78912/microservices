package com.fabricedev.discoveryserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServApplication.class, args);
    }
}
