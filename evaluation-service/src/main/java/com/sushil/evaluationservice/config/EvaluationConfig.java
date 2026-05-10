package com.sushil.evaluationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EvaluationConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
