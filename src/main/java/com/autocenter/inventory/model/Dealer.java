package com.autocenter.inventory.model;

import com.autocenter.inventory.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Dealer {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "dealer_id")
    private UUID id;
    private String tenantId;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private String email;
    private SubscriptionType subscriptionType;
    @OneToMany(mappedBy = "dealer")
    private Set<Vehicle> vehicles;

}
