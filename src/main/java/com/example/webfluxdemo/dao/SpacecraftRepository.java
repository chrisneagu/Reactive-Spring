package com.example.webfluxdemo.dao;

import com.example.webfluxdemo.entity.Spacecraft;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SpacecraftRepository
        extends ReactiveCrudRepository<Spacecraft, Long> {
    Mono<Spacecraft> findById(Integer id);

    Flux<Spacecraft> findAll();

    Mono<Spacecraft> save(final Spacecraft spacecraft);

    Flux<Spacecraft> findByModelContaining(String summary);
}