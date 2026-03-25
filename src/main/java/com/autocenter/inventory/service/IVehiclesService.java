package com.autocenter.inventory.service;

import com.autocenter.inventory.dto.PageControlDTO;
import com.autocenter.inventory.dto.VehicleDTO;
import com.autocenter.inventory.exceptions.ResourceNotFoundException;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IVehiclesService {
    @Nullable VehicleDTO createVehicle(VehicleDTO vehicle) throws RuntimeException;

    @Nullable VehicleDTO getVehicle(UUID id) throws ResourceNotFoundException;

    List<VehicleDTO> getVehicles(PageControlDTO pageControlDTO, Map<String, String> params);

    VehicleDTO updateVehicle(UUID id, VehicleDTO vehicle);

    void deleteVehicle(UUID id);
}
