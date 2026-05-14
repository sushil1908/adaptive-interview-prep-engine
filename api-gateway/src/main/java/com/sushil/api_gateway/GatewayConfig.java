package com.sushil.api_gateway;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return GatewayRouterFunctions.route("user-service")
                .route(req -> req.path().startsWith("/user/"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> questionServiceRoute() {
        return GatewayRouterFunctions.route("question-service")
                .route(req -> req.path().startsWith("/question/"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> evaluationServiceRoute() {
        return GatewayRouterFunctions.route("evaluation-service")
                .route(req -> req.path().startsWith("/evaluation/"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8082"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> recommendationServiceRoute() {
        return GatewayRouterFunctions.route("recommendation-service")
                .route(req -> req.path().startsWith("/recommendation/"),
                        HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8084"))
                .build();
    }
}