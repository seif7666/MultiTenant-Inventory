package com.autocenter.inventory.dto;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;


@Data
public class VehicleDTO {
    private String tenantId;
    @NotEmpty
    @NotNull
    private UUID dealerId;
    @Max(value = 2030, message = "Model cannot exceed 2030!")
    @Min(value = 200, message = "Model cannot be older than  2000!")
    private Integer model;
    private String status;
    @Positive
    private Double priceMin;
    @Positive
    private Double priceMax;
}
