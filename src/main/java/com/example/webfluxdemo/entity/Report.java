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
    private @NonNull String imageUrl;
    @Column("news_site")
    private @NonNull String newsSite;
    @Column("summary")
    private @NonNull String summary;
    @Column("published_at")
    private @NonNull String publishedAt;
    @Column("updated_at")
    private @NonNull String updatedAt;

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