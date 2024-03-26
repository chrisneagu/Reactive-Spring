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
@Table(name = "SPACECRAFTS")
public class Spacecraft implements Persistable<Integer> {
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
    @Column("company")
    private @NonNull String company;
    @Column("fuelCapacity")
    private double fuelCapacity;

    @Transient
    @JsonIgnore
    private boolean newSpacecraft;

    @Override
    @JsonIgnore
    @Transient
    public boolean isNew() {
        return this.newSpacecraft || id == null;
    }

    public Spacecraft setAsNew(){
        this.newSpacecraft = true;
        return this;
    }
}