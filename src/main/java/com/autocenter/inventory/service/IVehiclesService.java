package com.autocenter.inventory.service;

import com.autocenter.inventory.dto.VehicleDTO;
import com.autocenter.inventory.exceptions.ResourceNotFoundException;
import org.jspecify.annotations.Nullable;

import java.util.UUID;

public interface IVehiclesService {
    @Nullable VehicleDTO createVehicle(VehicleDTO vehicle) throws RuntimeException;

    @Nullable VehicleDTO getVehicle(UUID id) throws ResourceNotFoundException;
}
