package org.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route("user-service", r -> r.path("/users/**")
                        .uri("lb://user-service"))
                .route("book-service", r -> r.path("/books/**")
                        .uri("lb://book-service"))
                .route("library-service", r -> r.path("/library/**")
                        .uri("lb://library-service"))
                .build();
    }
}
