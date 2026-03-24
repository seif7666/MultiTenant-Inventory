package com.autocenter.inventory.dto;

import com.autocenter.inventory.model.Dealer;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;


@Data
public class VehicleDTO {
    private String tenantId;
    @NotEmpty
    @NotNull
    private UUID dealerId;
    @DecimalMax(value = "2030", message = "Model cannot exceed 2030!")
    @DecimalMin(value = "200", message = "Model cannot be older than  2000!")
    private Integer model;
    private String status;
    @Positive
    private Double priceMin;
    @Positive
    private Double priceMax;
}
