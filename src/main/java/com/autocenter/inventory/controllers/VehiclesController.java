package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.VehicleDTO;
import com.autocenter.inventory.service.IVehiclesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehiclesController {

    private final IVehiclesService vehiclesService;


    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiclesService.createVehicle(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicle(@PathVariable UUID id) {
        return ResponseEntity.ok(vehiclesService.getVehicle(id));
    }

    /**
     *
     * @param params a map containing keys for Filtering and Sorting.
     *               * Filter keys:
     *                  ** model
     *                  ** status
     *                  ** priceMin
     *                  ** priceMax
     *               * Pagination:
     *                  * pageNum
     *                  * pageSize
     *               * Sorting:
     *                  * sort
     * @return
     */
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(@RequestParam Map<String,String> params) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@RequestBody VehicleDTO vehicle) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleDTO> deleteVehicle(@PathVariable UUID id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
