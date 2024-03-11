package com.example.webfluxdemo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode()
@Table(name = "Reports")
public class Report {
    @Id
    private int id;
    private @NonNull String title;
    private @NonNull String url;
    private @NonNull String image_url;
    private @NonNull String news_site;
    private @NonNull String summary;
    private @NonNull String published_at;
    private @NonNull String updated_at;
}