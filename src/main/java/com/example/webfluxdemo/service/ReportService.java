package com.example.webfluxdemo.service;

import com.example.webfluxdemo.entity.Report;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReportService {
    Flux<Report> findAll();
    Mono<Report> findById(Integer id);
    Flux<Report> saveAll(Flux<Report> reports);
    Mono<Report> insert(final Report report);
    Flux<Report> fetchReports();
}
