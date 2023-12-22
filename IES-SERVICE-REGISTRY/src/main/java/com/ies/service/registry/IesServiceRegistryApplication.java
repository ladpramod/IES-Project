package com.ies.service.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IesServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(IesServiceRegistryApplication.class, args);
	}

}
