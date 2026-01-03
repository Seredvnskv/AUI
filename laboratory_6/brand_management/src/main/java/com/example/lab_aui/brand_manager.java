package com.example.lab_aui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;


@SpringBootApplication
public class brand_manager {
    @Value("${CARS_API:http://localhost:8081}")
    private String carsApiUrl;

	public static void main(String[] args) {
		SpringApplication.run(brand_manager.class, args);
	}

    @Bean
    @Qualifier("cars")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .rootUri(carsApiUrl)
                .build();
    }
}
