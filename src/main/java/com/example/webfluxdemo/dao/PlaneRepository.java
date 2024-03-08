package com.example.webfluxdemo.dao;

import com.example.webfluxdemo.entity.Plane;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PlaneRepository
        extends ReactiveCrudRepository<Plane, Long> {
    Mono<Plane> findById(Long id);
}