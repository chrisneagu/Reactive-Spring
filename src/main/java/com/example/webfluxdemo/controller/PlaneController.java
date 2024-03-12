package com.example.webfluxdemo.controller;

import ch.qos.logback.classic.Logger;
import com.example.webfluxdemo.entity.Plane;
import com.example.webfluxdemo.service.PlaneService;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = (Logger) LoggerFactory.getLogger(ReportController.class);
    public PlaneController(PlaneService planeService) {
        this.planeService = planeService;
    }

    @GetMapping
    public Flux<Plane> getAllPlanes(){
        return planeService.findAll().log();
    }

    @GetMapping("/{id}")
    public Mono<Plane> planeById(@PathVariable("id") Integer id) {
        log.info("Fetching plane with id: {}", id);
        return planeService.findById(id).log();
    }

    @GetMapping("/all/flux")
    public Flux<Plane> allPlanesFlux() {
        log.info("Fetching all planes as Flux");
        return planeService.findAll().log();
    }
    
    @GetMapping("/all/set")
    public Mono<Set<Plane>> allPlanesSet() {
        log.info("Fetching all reports as hashset");
        return planeService.findAllHashSet().log();
    }

    @PostMapping(path = "add", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Plane> postPlane(@RequestBody Plane plane){
        log.info("Inserting/Updating plane: {}", plane);
        return planeService.update(plane).log();
    }
}
