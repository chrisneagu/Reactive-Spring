package com.example.webfluxdemo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode()
@Table(name = "PLANES")
public class Plane {
    @Id
    @Column("id")
    private int id;
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
}