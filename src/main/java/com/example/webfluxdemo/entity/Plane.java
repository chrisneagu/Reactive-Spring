package com.example.webfluxdemo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table(name = "PLANES")
public class Plane {
    @Id
    private int id;
    private @NonNull String model;
    private @NonNull String manufacturer;
    private int capacity;
    private int maxSpeed;
    private @NonNull String airline;
    private double fuelCapacity;
}
