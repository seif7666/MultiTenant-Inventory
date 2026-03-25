package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.DealerDTO;
import com.autocenter.inventory.dto.PageControlDTO;
import com.autocenter.inventory.service.IDealersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dealers")
@Validated
@RequiredArgsConstructor
public class DealersController {

    private final IDealersService dealersService;

    @PostMapping
    public ResponseEntity<DealerDTO> addDealer(@Valid @RequestBody DealerDTO dealer){
        return ResponseEntity.status(HttpStatus.CREATED).body(dealersService.createDealer(dealer));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DealerDTO> getDealer(@PathVariable UUID id){
        return ResponseEntity.ok(dealersService.getDealer(id));
    }

    /**
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     */
    @GetMapping
    public ResponseEntity<List<DealerDTO>> getDealers(PageControlDTO pageControlDTO){
        return ResponseEntity.ok(dealersService.getDealers(pageControlDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DealerDTO> updateDealer(@PathVariable UUID id, @RequestBody DealerDTO dealer){
        return ResponseEntity.status(HttpStatus.OK).body(this.dealersService.updateDealer(id, dealer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDealer(@PathVariable UUID id){
        this.dealersService.deleteDealer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }
}
