package com.example.webfluxdemo.service;

import com.example.webfluxdemo.entity.Plane;
import reactor.core.publisher.Mono;

public interface PlaneService {
    Mono<Plane> findById(Long id);
}
