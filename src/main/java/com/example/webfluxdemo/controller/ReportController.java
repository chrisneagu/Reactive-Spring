package com.example.webfluxdemo.controller;

import ch.qos.logback.classic.Logger;
import com.example.webfluxdemo.entity.Report;
import com.example.webfluxdemo.entity.Spacecraft;
import com.example.webfluxdemo.service.ReportService;
import com.example.webfluxdemo.service.SpacecraftService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/reports",
        produces = "application/json"
)
@CrossOrigin(origins = "*")
public class ReportController {
    private final ReportService reportService;
    private final SpacecraftService spacecraftService;
    private static final Logger log = (Logger) LoggerFactory.getLogger(ReportController.class);
    public ReportController(ReportService reportService, SpacecraftService spacecraftService) {
        this.reportService = reportService;
        this.spacecraftService = spacecraftService;
    }

    @GetMapping("/{id}")
    public Mono<Report> reportById(@PathVariable("id") Integer id) {
        log.info("Fetching report with id: {}", id);
        Mono<Report> report = reportService.findById(id).log();
        Flux<Spacecraft> spacecraftFlux = spacecraftService.findAll();
        return report.flatMap(r ->
                spacecraftFlux
                        .filter(spacecraft -> r.getSummary().contains(spacecraft.getModel()))
                        .collectList()
                        .doOnNext(spacecrafts -> {
                            spacecrafts.forEach(spacecraft -> r.setDetails(spacecraft.toString()));
                        })
                        .thenReturn(r)
        );
    }

    @GetMapping("/all/flux")
    public Flux<Report> allReportsFlux() {
        log.info("Fetching all reports as Flux");
        return reportService.findAll().log();
    }

    @PostMapping("/fetch/external")
    public Flux<Report> fetchReportsExternal() {
        log.info("Fetching reports from external API");
        Flux<Report> reports = reportService.fetchReports();
        log.info("Populating database from external DataSource");
        reportService.saveAll(reports)
                .log()
                .subscribe();
        return reports;
    }
}
