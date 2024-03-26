package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dao.SpacecraftRepository;
import com.example.webfluxdemo.entity.Spacecraft;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SpacecraftServiceImpl
        implements SpacecraftService {

    private final SpacecraftRepository spacecraftRepository;

    public SpacecraftServiceImpl(SpacecraftRepository spacecraftRepository) {
        this.spacecraftRepository = spacecraftRepository;
    }

    @Override
    public Flux<Spacecraft> findAll() {
        return spacecraftRepository.findAll();
    }

    @Override
    public Mono<Spacecraft> findById(Integer id) {
        return spacecraftRepository.findById(id);
    }

    @Override
    public Mono<Set<Spacecraft>> findAllHashSet() {
        return spacecraftRepository.findAll().collect(Collectors.toCollection(HashSet::new));
    }

    @Override
    public Mono<Spacecraft> save(final Spacecraft spacecraft) {
        return spacecraftRepository.save(spacecraft);
    }

    @Override
    public Mono<Spacecraft> update(final Spacecraft spacecraft) {
        return spacecraftRepository.findById(spacecraft.getId())
                .flatMap(p -> {
                    p.setCompany(spacecraft.getCompany());
                    p.setCapacity(spacecraft.getCapacity());
                    p.setModel(spacecraft.getModel());
                    p.setManufacturer(spacecraft.getManufacturer());
                    p.setMaxSpeed(spacecraft.getMaxSpeed());
                    p.setFuelCapacity(spacecraft.getFuelCapacity());
                    return spacecraftRepository.save(p);
                }).switchIfEmpty(spacecraftRepository.save(spacecraft.setAsNew()));
    }
}
