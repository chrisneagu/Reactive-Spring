package com.example.webfluxdemo.controller;

import ch.qos.logback.classic.Logger;
import com.example.webfluxdemo.entity.Spacecraft;
import com.example.webfluxdemo.service.SpacecraftService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/spacecrafts",
        produces = "application/json"
)
@CrossOrigin(origins = "*")
public class SpacecraftController {
    private final SpacecraftService spacecraftService;
    private static final Logger log = (Logger) LoggerFactory.getLogger(ReportController.class);
    public SpacecraftController(SpacecraftService spacecraftService) {
        this.spacecraftService = spacecraftService;
    }

    @GetMapping
    public Flux<Spacecraft> getAllSpacecrafts(){
        return spacecraftService.findAll().log();
    }

    @GetMapping("/{id}")
    public Mono<Spacecraft> spacecraftById(@PathVariable("id") Integer id) {
        log.info("Fetching spacecraft with id: {}", id);
        return spacecraftService.findById(id).log();
    }

    @GetMapping("/all/flux")
    public Flux<Spacecraft> allSpacecraftsFlux() {
        log.info("Fetching all spacecrafts as Flux");
        return spacecraftService.findAll().log();
    }
    
    @GetMapping("/all/set")
    public Mono<Set<Spacecraft>> allSpacecraftsSet() {
        log.info("Fetching all reports as hashset");
        return spacecraftService.findAllHashSet().log();
    }

    @PostMapping(path = "add", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Spacecraft> postSpacecraft(@RequestBody Spacecraft spacecraft){
        log.info("Inserting/Updating spacecraft: {}", spacecraft);
        return spacecraftService.update(spacecraft).log();
    }
}
