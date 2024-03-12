package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dao.ReportRepository;
import com.example.webfluxdemo.entity.Report;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository, WebClient webClient) {
        this.reportRepository = reportRepository;
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
                            existingReport.setUpdated_at(report.getUpdated_at());
                            existingReport.setSummary(report.getSummary());
                            existingReport.setUrl(report.getUrl());
                            existingReport.setTitle(report.getTitle());
                            existingReport.setImage_url(report.getImage_url());
                            existingReport.setPublished_at(report.getPublished_at());
                            existingReport.setNews_site(report.getNews_site());
                            return reportRepository.save(existingReport);
                        })
                        .switchIfEmpty(reportRepository.save(report.setAsNew())));
    }

    @Override
    public Mono<Report> update(final Report report) {
        return reportRepository.findById(report.getId())
                .flatMap(r -> {
                    r.setUpdated_at(report.getUpdated_at());
                    r.setSummary(report.getSummary());
                    r.setUrl(report.getUrl());
                    r.setTitle(report.getTitle());
                    r.setImage_url(report.getImage_url());
                    r.setPublished_at(report.getPublished_at());
                    r.setNews_site(report.getNews_site());
                    return reportRepository.save(r);
                }).switchIfEmpty(reportRepository.save(report.setAsNew()));
    }
}