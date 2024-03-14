package com.example.webfluxdemo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportDTO {
    private int id;
    private String title;
    private String url;
    private String image_url;
    private String news_site;
    private String summary;
    private String published_at;
    private String updated_at;
}
