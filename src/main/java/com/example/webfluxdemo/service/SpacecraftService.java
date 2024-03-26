package com.example.webfluxdemo.service;

import com.example.webfluxdemo.entity.Spacecraft;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface SpacecraftService {
    Flux<Spacecraft> findAll();
    Mono<Spacecraft> findById(Integer id);
    Mono<Set<Spacecraft>> findAllHashSet();
    Mono<Spacecraft> save(final Spacecraft spacecraft);
    Mono<Spacecraft> update(final Spacecraft spacecraft);
}
