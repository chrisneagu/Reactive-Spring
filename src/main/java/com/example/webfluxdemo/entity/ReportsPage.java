package com.example.webfluxdemo.entity;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class ReportsPage {
    private int count;
    private String next;
    private String previous;
    private List<ReportDTO> results;
}
