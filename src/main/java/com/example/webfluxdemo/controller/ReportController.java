package com.example.webfluxdemo.controller;

import ch.qos.logback.classic.Logger;
import com.example.webfluxdemo.entity.Report;
import com.example.webfluxdemo.entity.ReportsPage;
import com.example.webfluxdemo.service.ReportService;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/reports",
        produces = "application/json"
)
@CrossOrigin(origins = "*")
public class ReportController {
    private final ReportService reportService;
    private final WebClient webClient;

    private static final Logger log = (Logger) LoggerFactory.getLogger(ReportController.class);
    public ReportController(ReportService reportService, WebClient webClient) {
        this.reportService = reportService;
        this.webClient = webClient;
    }

    @GetMapping("/{id}")
    public Mono<Report> reportById(@PathVariable("id") Integer id) {
        log.info("Fetching report with id: {}", id);
        return reportService.findById(id).log();
    }

    @GetMapping("/all/flux")
    public Flux<Report> allReportsFlux() {
        log.info("Fetching all reports as Flux");
        return reportService.findAll().log();
    }

    public Flux<Report> fetchReports() {
        log.info("Fetching reports from external API");
        return webClient
                .get()
                .uri("https://api.spaceflightnewsapi.net/v4/reports/")
                .retrieve()
                .bodyToMono(ReportsPage.class)
                .flatMapMany(page -> Flux.fromIterable(page.getResults()))
                .log();
    }

    @GetMapping("/fetch/external")
    public Flux<Report> fetchReportsExternal() {
        log.info("Populating database from external datasource");
        Flux<Report> reports = fetchReports();
        reportService.saveAll(reports)
                .log()
                .subscribe();
        return reports;
    }
}
