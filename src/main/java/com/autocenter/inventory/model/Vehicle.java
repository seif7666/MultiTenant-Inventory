package com.autocenter.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vehicle_id")
    private UUID id;
    private String tenantId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Dealer dealer;
    private Integer model;
    private String status;
    private Double priceMin;
    private Double priceMax;
}
