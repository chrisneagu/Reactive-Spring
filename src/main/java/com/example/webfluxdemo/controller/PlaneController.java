package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.entity.Plane;
import com.example.webfluxdemo.service.PlaneService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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

    @GetMapping("/{id}")
    public Mono<Plane> planeById(@PathVariable("id") Long id) {
        return planeService.findById(id);
    }
}
