package com.example.webfluxdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REPORTS")
public class Report implements Persistable<Integer> {
    @Id
    @Column("id")
    private Integer id;
    @Column("title")
    private @NonNull String title;
    @Column("url")
    private @NonNull String url;
    @Column("image_url")
    private @NonNull String image_url;
    @Column("news_site")
    private @NonNull String news_site;
    @Column("summary")
    private @NonNull String summary;
    @Column("published_at")
    private @NonNull String published_at;
    @Column("updated_at")
    private @NonNull String updated_at;

    public Report(Integer id, @NonNull String title, @NonNull String url, @NonNull String image_url, @NonNull String news_site, @NonNull String summary, @NonNull String published_at, @NonNull String updated_at) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.image_url = image_url;
        this.news_site = news_site;
        this.summary = summary;
        this.published_at = published_at;
        this.updated_at = updated_at;
    }

    @Transient
    @JsonIgnore
    private boolean newReport;

    @Override
    @JsonIgnore
    @Transient
    public boolean isNew() {
        return this.newReport || id == null;
    }

    public Report setAsNew(){
        this.newReport = true;
        return this;
    }
}