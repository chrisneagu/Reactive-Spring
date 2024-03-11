package com.example.webfluxdemo.service;

import com.example.webfluxdemo.entity.Plane;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface PlaneService {
    Flux<Plane> findAll();
    Mono<Plane> findById(Long id);
    Mono<Set<Plane>> findAllHashSet();
    Mono<Plane> save(Mono<Plane> plane);
    void deleteById(Long id);
}
