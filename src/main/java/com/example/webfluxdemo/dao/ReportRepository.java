package com.example.webfluxdemo.dao;

import com.example.webfluxdemo.entity.Report;
import lombok.NonNull;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReportRepository
        extends ReactiveCrudRepository<Report, Long> {
    Mono<Report> findById(Integer id);
    Flux<Report> findAll();
    <S extends Report> @NonNull Mono<S> save(@NonNull S entity);

    <S extends Report> @NonNull Flux<S> saveAll(@NonNull Publisher<S> entityStream);
}