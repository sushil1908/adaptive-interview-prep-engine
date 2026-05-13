package com.sushil.evaluationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients        // ← add this
@EnableDiscoveryClient
public class EvaluationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluationServiceApplication.class, args);
	}

}
