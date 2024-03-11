package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dao.PlaneRepository;
import com.example.webfluxdemo.entity.Plane;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaneServiceImpl
        implements PlaneService {

    private final PlaneRepository planeRepository;

    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public Mono<Plane> findById(Long id) {
        return planeRepository.findById(id);
    }

    @Override
    public Flux<Plane> findAll() {
        return planeRepository.findAll();
    }

    @Override
    public Mono<Set<Plane>> findAllHashSet() {
        return planeRepository.findAll().collect(Collectors.toCollection(HashSet::new));
    }
}
