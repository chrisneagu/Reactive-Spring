package com.example.webfluxdemo.entity;

import jakarta.annotation.PostConstruct;
import lombok.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Data
@NoArgsConstructor
public class ReportsPage {
    private int count;
    private String next;
    private String previous;
    private List<Report> results;
}
