package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.PageControlDTO;
import com.autocenter.inventory.dto.VehicleDTO;
import com.autocenter.inventory.helpers.DealersGenerator;
import com.autocenter.inventory.helpers.VehiclesGenerator;
import com.autocenter.inventory.model.Dealer;
import com.autocenter.inventory.model.Vehicle;
import com.autocenter.inventory.repos.DealerRepository;
import com.autocenter.inventory.repos.VehicleRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VehiclesControllerTest {

    @Autowired
    private VehiclesController vehiclesController;
    @Autowired
    private EntityManager em;
    @Autowired
    private DealerRepository dealerRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehiclesGenerator vehiclesGenerator;

    private List<Dealer> dealers;
    private List<Vehicle> vehicles;


    @BeforeEach
    void setUp() {
        vehiclesGenerator.generateVehicles();
        dealers = vehiclesGenerator.getDealers();
        vehicles = vehiclesGenerator.getVehicles();
        this.dealerRepository.saveAll(dealers);
        this.vehicleRepository.saveAll(vehicles);
        System.out.println("=========================Beginning Test=================");
    }

    @AfterEach
    void tearDown() {
        System.out.println("=========================Ending Test=================");
        this.vehicleRepository.deleteAll();
        this.dealerRepository.deleteAll();
    }

    @Test
    void createVehicle() {
        Dealer dealer= this.dealers.get(new Random().nextInt(dealers.size()));
        VehicleDTO vehicleDTO= new VehicleDTO();
        vehicleDTO.setDealerId(dealer.getId());
        vehicleDTO.setModel(2025);
        vehicleDTO.setStatus("New");
        vehicleDTO.setTenantId(1000+"");
        vehicleDTO.setPriceMax(10000.0);
        vehicleDTO.setPriceMin(5000.0);
        VehicleDTO vehicleDTO1=vehiclesController.createVehicle(vehicleDTO).getBody();
        Vehicle vehicle= this.vehicleRepository.findById(vehicleDTO1.getId()).get();
        assertEquals(vehicleDTO.getModel(),vehicle.getModel());
        assertEquals(vehicleDTO.getStatus(),vehicle.getStatus());
        assertEquals(vehicleDTO.getPriceMax(),vehicle.getPriceMax());
        assertEquals(vehicleDTO.getPriceMin(),vehicle.getPriceMin());
    }

    @Test
    void getVehicle() {
        Vehicle randomVehicle= this.vehicles.get(new Random().nextInt(this.vehicles.size()));
        VehicleDTO vehicleDTO= this.vehiclesController.getVehicle(randomVehicle.getId()).getBody();
        assertEquals(randomVehicle.getModel(),vehicleDTO.getModel());
        assertEquals(randomVehicle.getStatus(),vehicleDTO.getStatus());
        assertEquals(randomVehicle.getPriceMax(),vehicleDTO.getPriceMax());
        assertEquals(randomVehicle.getPriceMin(),vehicleDTO.getPriceMin());
        assertEquals(randomVehicle.getDealer().getId(),vehicleDTO.getDealerId());
    }

    @Test
    void getAllVehiclesWithoutFiltering() {
        assertEquals(this.vehicles.size(), this.vehiclesController.getAllVehicles(new PageControlDTO(),new HashMap<>()).getBody().size());
    }

    @Test
    void getAllVehiclesWithFiltering() {
        String statusFilter= "Mer";
        List<Vehicle>filteredVehicles= vehicles.stream().filter(vehicle -> vehicle.getStatus().contains(statusFilter)).sorted(Comparator.comparing(Vehicle::getId)).toList();
        System.out.printf("Tota Vehicles are %d and the filtered are %d\n",vehicles.size(),filteredVehicles.size());
        List<VehicleDTO> vehicleDTOS= this.vehiclesController.getAllVehicles(new PageControlDTO(null,null,"id"), Map.of("status",statusFilter)).getBody();
        assertEquals(filteredVehicles.size(),vehicleDTOS.size());
        vehicleDTOS.forEach(vehicleDTO -> {
            assertTrue(filteredVehicles.stream().anyMatch(vehicle -> vehicle.getId().equals(vehicleDTO.getId())));
        });
    }

    @Test
    void updateVehicle() {
    }

    @Test
    void deleteVehicle() {
    }
}