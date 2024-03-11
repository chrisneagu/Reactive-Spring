package com.example.webfluxdemo.service;

import com.example.webfluxdemo.entity.Plane;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

public interface PlaneService {
    Mono<Plane> findById(Long id);

    Flux<Plane> findAll();

    Mono<Set<Plane>> findAllHashSet();
}
