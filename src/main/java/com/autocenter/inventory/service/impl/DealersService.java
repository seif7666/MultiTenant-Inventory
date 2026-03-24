package com.autocenter.inventory.service.impl;

import com.autocenter.inventory.dto.DealerDTO;
import com.autocenter.inventory.exceptions.ResourceNotFoundException;
import com.autocenter.inventory.mapper.DealerMapper;
import com.autocenter.inventory.model.Dealer;
import com.autocenter.inventory.repos.DealerRepository;
import com.autocenter.inventory.service.IDealersService;
import com.autocenter.inventory.validation.impl.DealerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DealersService implements IDealersService {

    private final DealerRepository dealerRepository;
    private final DealerMapper dealerMapper;
    private final DealerValidator dealerValidator;


    public DealerDTO createDealer(DealerDTO dealerDto) throws RuntimeException{
        dealerValidator.validate(dealerDto);
        Dealer dealer = dealerMapper.map(dealerDto);
        this.dealerRepository.save(dealer);
        return dealerDto;
    }

    @Override
    public DealerDTO getDealer(UUID id) throws ResourceNotFoundException {
        Dealer dealerOptional=getDealerFromDB(id);
        return dealerMapper.map(dealerOptional);
    }

    @Override
    public List<DealerDTO> getDealers(Integer pageNum, Integer pageSize, String sort) throws RuntimeException {
        boolean pagingRequired= pageNum != null && pageSize != null;
        boolean sortRequired= sort != null && !sort.isBlank();

        if(pagingRequired && sortRequired)
            return this.dealerRepository.findAll(PageRequest.of(pageNum,pageSize, Sort.by(sort))).get().map(dealerMapper::map).toList();
        else if(pagingRequired)
            return this.dealerRepository.findAll(PageRequest.of(pageNum,pageSize)).get().map(dealerMapper::map).toList();
        else if(sortRequired)
            return this.dealerRepository.findAll(Sort.by(sort)).stream().map(dealerMapper::map).toList();
        return this.dealerRepository.findAll().stream().map(dealerMapper::map).toList();
    }

    @Override
    public DealerDTO updateDealer(UUID id, DealerDTO dealer) throws RuntimeException {
        this.dealerValidator.validate(dealer);
        Dealer dbDealer= this.getDealerFromDB(id);
        dealerMapper.map(dealer,dbDealer);
        this.dealerRepository.save(dbDealer);
        return dealer;
    }

    @Override
    public void deleteDealer(UUID id) throws ResourceNotFoundException {
        this.dealerRepository.deleteById(id);
    }


    private Dealer getDealerFromDB(UUID id){
        return this.dealerRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format("The Dealer with ID %s not found!",id)));
    }
}
