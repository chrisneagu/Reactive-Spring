package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.entity.Plane;
import com.example.webfluxdemo.service.PlaneService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/planes",
        produces = "application/json"
)
@CrossOrigin(origins = "*")
public class PlaneController {
    private final PlaneService planeService;

    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public Flux<Plane> getAllPlanes(){
        return planeService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Plane> planeById(@PathVariable("id") Long id) {
        return planeService.findById(id);
    }

    @GetMapping("/all/flux")
    public Flux<Plane> allPlanesFlux() {
        return planeService.findAll();
    }
    
    @GetMapping("/all/set")
    public Mono<Set<Plane>> allPlanesSet() {
        return planeService.findAllHashSet();
      
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Plane> postPlane(@RequestBody Mono<Plane> plane){
        return planeService.save(plane);
    }
}
