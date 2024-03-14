package com.example.webfluxdemo.dao;

import com.example.webfluxdemo.entity.Report;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReportRepository
        extends ReactiveCrudRepository<Report, Long> {
    Mono<Report> findById(Integer id);
    Flux<Report> findAll();
    Flux<Report> saveAll(Flux<Report> reports);
    Mono<Report> save(final Report report);
}