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
@Table(name = "PLANES")
public class Plane implements Persistable<Integer> {
    @Id
    @Column("id")
    private Integer id;
    @Column("model")
    private @NonNull String model;
    @Column("manufacturer")
    private @NonNull String manufacturer;
    @Column("capacity")
    private int capacity;
    @Column("maxSpeed")
    private int maxSpeed;
    @Column("airline")
    private @NonNull String airline;
    @Column("fuelCapacity")
    private double fuelCapacity;

    @Override
    @JsonIgnore
    @Transient
    public boolean isNew() {
        return id == null;
    }

    public Plane setAsNew(){
        return this;
    }
}