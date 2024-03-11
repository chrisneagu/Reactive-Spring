package com.example.webfluxdemo.service;

import com.example.webfluxdemo.dao.ReportRepository;
import com.example.webfluxdemo.entity.Report;
import com.example.webfluxdemo.entity.ReportsPage;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository, WebClient webClient) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Flux<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public Mono<Report> findById(Long id) {
        return reportRepository.findById(id);
    }

    public Flux<Report> saveAll(Flux<Report> reports) {
        return reportRepository.saveAll(reports);
    }
}
