package com.autocenter.inventory.service;

import com.autocenter.inventory.dto.DealerDTO;
import com.autocenter.inventory.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IDealersService {

    DealerDTO createDealer(DealerDTO dealerDto) throws RuntimeException;
    DealerDTO getDealer(UUID id) throws ResourceNotFoundException;
    List<DealerDTO> getDealers(Integer pageNum, Integer offset, String sort) throws RuntimeException;
    DealerDTO updateDealer(UUID id, DealerDTO dealer) throws RuntimeException;
    void deleteDealer(UUID id) throws ResourceNotFoundException;
}
