package com.example.webfluxdemo.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.logging.Logger;

@Configuration
public class ProjectConfiguration {
    @Bean
    public WebClient webClient(){
        return WebClient.create();
    }
}