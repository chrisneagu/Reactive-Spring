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
    public Flux<Plane> findAll() {
        return planeRepository.findAll();
    }

    @Override
    public Mono<Plane> findById(Integer id) {
        return planeRepository.findById(id);
    }

    @Override
    public Mono<Set<Plane>> findAllHashSet() {
        return planeRepository.findAll().collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Mono<Plane> save(final Plane plane) {
        return planeRepository.save(plane);
    }

    @Override
    public Mono<Plane> update(final Plane plane) {
        return planeRepository.findById(plane.getId())
                .flatMap(p -> {
                    p.setAirline(plane.getAirline());
                    p.setCapacity(plane.getCapacity());
                    p.setModel(plane.getModel());
                    p.setManufacturer(plane.getManufacturer());
                    p.setMaxSpeed(plane.getMaxSpeed());
                    p.setFuelCapacity(plane.getFuelCapacity());
                    return planeRepository.save(p);
                }).switchIfEmpty(planeRepository.save(plane.setAsNew()));
    }
}