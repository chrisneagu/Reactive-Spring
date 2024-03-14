package com.example.webfluxdemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ProjectConfiguration {
    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }
}