package com.autocenter.inventory.helpers;


import com.autocenter.inventory.model.Dealer;
import com.autocenter.inventory.model.Vehicle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class VehiclesGenerator {

    private final DealersGenerator dealersGenerator;
    @Getter
    private List<Dealer> dealers;
    @Getter
    private List<Vehicle> vehicles;

    private String[] companies= {"Mercedes", "BMW", "Skoda", "Audi", "Renault", "KIA", "Hyundai", "Honda", "Volvo", "Volkswagen", "Mazda"};

    public void generateVehicles(){
        dealers = dealersGenerator.generateDealers();
        vehicles=new LinkedList<>();
        for(int i= 0; i<dealers.size()*3; i++){
            Vehicle vehicle = new Vehicle();
            vehicles.add(vehicle);
            vehicle.setDealer(dealers.get(new Random().nextInt(dealers.size())));
            vehicle.setPriceMin(new Random().nextDouble()* Math.pow(10,8));
            vehicle.setPriceMax(new Random().nextDouble()* Math.pow(10,5)+ vehicle.getPriceMin());
            vehicle.setStatus(companies[new Random().nextInt(companies.length)] + " "+this.dealersGenerator.generateName());
            vehicle.setTenantId(new Random().nextInt(10000)+ "");
        }
    }

}
