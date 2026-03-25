package com.autocenter.inventory.mapper;

import com.autocenter.inventory.dto.VehicleDTO;
import com.autocenter.inventory.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle map(VehicleDTO vehicleDTO){
        Vehicle vehicle= new Vehicle();
        map(vehicleDTO,vehicle);
        return vehicle;
    }

    public void map(VehicleDTO vehicleDTO, Vehicle vehicle){
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setTenantId(vehicleDTO.getTenantId());
        vehicle.setStatus(vehicleDTO.getStatus());
        vehicle.setPriceMax(vehicleDTO.getPriceMax());
        vehicle.setPriceMin(vehicleDTO.getPriceMin());
    }

    public VehicleDTO map(Vehicle vehicle){
        VehicleDTO vehicleDTO= new VehicleDTO();
        vehicleDTO.setId(vehicle.getId());
        vehicleDTO.setDealerId(vehicle.getDealer().getId());
        vehicleDTO.setStatus(vehicle.getStatus());
        vehicleDTO.setModel(vehicle.getModel());
        vehicleDTO.setTenantId(vehicle.getTenantId());
        vehicleDTO.setPriceMax(vehicle.getPriceMax());
        vehicleDTO.setPriceMin(vehicle.getPriceMin());
        return vehicleDTO;
    }
}
