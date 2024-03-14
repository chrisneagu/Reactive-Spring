package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dao.ReportRepository;
import com.example.webfluxdemo.entity.Report;
import com.example.webfluxdemo.entity.ReportsPage;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    private final WebClient webClient;

    public ReportServiceImpl(ReportRepository reportRepository, WebClient webClient) {
        this.reportRepository = reportRepository;
        this.webClient = webClient;
    }

    @Override
    public Flux<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Mono<Report> findById(Integer id) {
        return reportRepository.findById(id);
    }

    public Flux<Report> saveAll(Flux<Report> reports) {
        return reports.flatMap(report ->
                reportRepository.findById(report.getId())
                        .flatMap(existingReport -> {
                            existingReport.setUpdatedAt(report.getUpdatedAt());
                            existingReport.setSummary(report.getSummary());
                            existingReport.setUrl(report.getUrl());
                            existingReport.setTitle(report.getTitle());
                            existingReport.setImageUrl(report.getImageUrl());
                            existingReport.setPublishedAt(report.getPublishedAt());
                            existingReport.setNewsSite(report.getNewsSite());
                            return reportRepository.save(existingReport);
                        })
                        .switchIfEmpty(reportRepository.save(report.setAsNew())));
    }

    @Override
    public Mono<Report> update(final Report report) {
        return reportRepository.findById(report.getId())
                .flatMap(r -> {
                    r.setUpdatedAt(report.getUpdatedAt());
                    r.setSummary(report.getSummary());
                    r.setUrl(report.getUrl());
                    r.setTitle(report.getTitle());
                    r.setImageUrl(report.getImageUrl());
                    r.setPublishedAt(report.getPublishedAt());
                    r.setNewsSite(report.getNewsSite());
                    return reportRepository.save(r);
                }).switchIfEmpty(reportRepository.save(report.setAsNew()));
    }

    public Flux<Report> fetchReports() {
        return webClient
                .get()
                .uri("https://api.spaceflightnewsapi.net/v4/reports/")
                .retrieve()
                .bodyToMono(ReportsPage.class)
                .flatMapMany(page -> Flux.fromIterable(page.getResults()).map(reportDto -> {
                    Report report = new Report();
                    report.setId(reportDto.getId());
                    report.setTitle(reportDto.getTitle());
                    report.setUrl(reportDto.getUrl());
                    report.setImageUrl(reportDto.getImage_url());
                    report.setNewsSite(reportDto.getNews_site());
                    report.setSummary(reportDto.getSummary());
                    report.setPublishedAt(reportDto.getPublished_at());
                    report.setUpdatedAt(reportDto.getUpdated_at());
                    return report;
                }))
                .log();
    }
}