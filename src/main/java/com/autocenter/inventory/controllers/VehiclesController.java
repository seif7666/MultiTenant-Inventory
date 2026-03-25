package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.PageControlDTO;
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
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(PageControlDTO pageControlDTO,  @RequestParam Map<String, String> params) {
        return ResponseEntity.ok(this.vehiclesService.getVehicles(pageControlDTO, params));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable UUID id, @RequestBody VehicleDTO vehicle) {
        return ResponseEntity.ok().body(this.vehiclesService.updateVehicle(id,vehicle));
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable UUID id) {
        this.vehiclesService.deleteVehicle(id);
    }
}
