package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.entity.Report;
import com.example.webfluxdemo.entity.ReportsPage;
import com.example.webfluxdemo.service.ReportService;
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

    public ReportController(ReportService reportService, WebClient webClient) {
        this.reportService = reportService;
        this.webClient = webClient;
    }

    @GetMapping("/{id}")
    public Mono<Report> reportById(@PathVariable("id") Long id) {
        return reportService.findById(id);
    }

    @GetMapping("/all/flux")
    public Flux<Report> allReportsFlux() {
        return reportService.findAll();
    }

    public Flux<Report> fetchReports() {
        return webClient
                .get()
                .uri("https://api.spaceflightnewsapi.net/v4/reports/")
                .retrieve()
                .bodyToMono(ReportsPage.class)
                .flatMapMany(page -> Flux.fromIterable(page.getResults()));
    }

    @GetMapping("/fetch/external")
    public Flux<Report> fetchReportsExternal() {
        Flux<Report> reports = fetchReports();
        reportService.saveAll(reports).subscribe();
        return reports;
    }
}
