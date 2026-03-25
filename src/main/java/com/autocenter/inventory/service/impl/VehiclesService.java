package com.autocenter.inventory.service.impl;

import com.autocenter.inventory.dto.PageControlDTO;
import com.autocenter.inventory.dto.VehicleDTO;
import com.autocenter.inventory.exceptions.ResourceNotFoundException;
import com.autocenter.inventory.mapper.VehicleMapper;
import com.autocenter.inventory.model.Vehicle;
import com.autocenter.inventory.repos.VehicleRepository;
import com.autocenter.inventory.service.IVehiclesService;
import com.autocenter.inventory.service.utilities.PageControlService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehiclesService implements IVehiclesService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final PageControlService pageControlService;

    @Override
    public VehicleDTO createVehicle(VehicleDTO vehicle) throws RuntimeException {
        Vehicle vehicleEntity = vehicleMapper.map(vehicle);
        this.vehicleRepository.save(vehicleEntity);
        vehicle.setId(vehicleEntity.getId());
        return vehicle;
    }

    @Override
    public VehicleDTO getVehicle(UUID id) throws ResourceNotFoundException {
        return this.vehicleMapper.map(this.getVehicleFromDB(id));
    }

    @Override
    public List<VehicleDTO> getVehicles(PageControlDTO pageControlDTO, Map<String, String> params) {
        Pageable pageable = this.pageControlService.getPageControl(pageControlDTO);
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        Vehicle vehicle = new Vehicle();
        boolean filterFlag = false;
        if (params.containsKey("model")) {
            filterFlag = true;
            vehicle.setModel(Integer.valueOf(params.get("model")));
        }
        if (params.containsKey("status")) {
            filterFlag = true;
            exampleMatcher = exampleMatcher.withIgnoreCase("status").withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
            vehicle.setStatus(params.get("status"));
        }
        if (params.containsKey("priceMin")) {
            filterFlag = true;
            vehicle.setPriceMin(Double.valueOf(params.get("priceMin")));
        }
        if (params.containsKey("priceMax")) {
            filterFlag = true;
            vehicle.setPriceMax(Double.valueOf(params.get("priceMax")));
        }
        if (filterFlag) {
            Example<Vehicle> example = Example.of(vehicle, exampleMatcher);
            return this.vehicleRepository.findAll(example, pageable).stream().map(this.vehicleMapper::map).toList();
        }
        return this.vehicleRepository.findAll(pageable).stream().map(this.vehicleMapper::map).toList();
    }

    @Override
    public VehicleDTO updateVehicle(UUID id, VehicleDTO vehicle) {
        Vehicle vehicleEntity = this.getVehicleFromDB(id);
        this.vehicleMapper.map(vehicle, vehicleEntity);
        vehicle.setId(vehicleEntity.getId());
        return vehicle;
    }

    @Override
    public void deleteVehicle(UUID id) {
        this.vehicleRepository.deleteById(id);
    }

    private Vehicle getVehicleFromDB(UUID id) {
        return this.vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
    }
}
