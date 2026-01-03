package com.example.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {
    @Value("${BRAND_API:http://localhost:8082}")
    private String brandsAPI;

    @Value("${CARS_API:http://localhost:8081}")
    private String carsAPI;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("brands", route -> route
                        .path("/api/brands", "/api/brands/{uuid}", "/api/brands/name/{name}")
                        .uri(brandsAPI))
                .route("cars", route -> route
                        .path("/api/cars", "/api/cars/{uuid}", "/api/cars/brand/{uuid}/models")
                        .uri(carsAPI))
                .build();
    }
}
