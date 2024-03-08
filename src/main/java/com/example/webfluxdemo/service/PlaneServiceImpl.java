package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dao.PlaneRepository;
import com.example.webfluxdemo.entity.Plane;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PlaneServiceImpl
    implements PlaneService{

    private final PlaneRepository planeRepository;

    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public Mono<Plane> findById(Long id) {
        return planeRepository.findById(id);
    }
}
