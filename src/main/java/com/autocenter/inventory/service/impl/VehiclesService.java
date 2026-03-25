package com.autocenter.inventory.service.impl;

import com.autocenter.inventory.dto.VehicleDTO;
import com.autocenter.inventory.exceptions.ResourceNotFoundException;
import com.autocenter.inventory.service.IVehiclesService;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehiclesService implements IVehiclesService {
    @Override
    public @Nullable VehicleDTO createVehicle(VehicleDTO vehicle) throws RuntimeException {
        return null;
    }

    @Override
    public @Nullable VehicleDTO getVehicle(UUID id) throws ResourceNotFoundException {
        return null;
    }
}
