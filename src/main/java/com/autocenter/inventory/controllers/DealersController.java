package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.DealerDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dealers")
@Validated
public class DealersController {

    @PostMapping
    public ResponseEntity<String> addDealer(@Valid @RequestBody DealerDTO dealer){
        // TODO
        throw new UnsupportedOperationException("Not supported yet.");
    }
    @GetMapping("/{id}")
    public ResponseEntity<DealerDTO> getDealer(@PathVariable UUID id){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param pageNum
     * @param offset
     * @param sort
     * @return
     */
    @GetMapping
    public ResponseEntity<List<DealerDTO>> getDealers(@RequestParam Integer pageNum, @RequestParam Integer offset, @RequestParam String sort){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateDealer(@PathVariable UUID id, @RequestBody DealerDTO dealer){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDealer(@PathVariable UUID id){
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
