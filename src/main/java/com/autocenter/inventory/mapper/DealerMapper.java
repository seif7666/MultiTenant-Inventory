package com.autocenter.inventory.mapper;

import com.autocenter.inventory.dto.DealerDTO;
import com.autocenter.inventory.model.Dealer;
import org.springframework.stereotype.Component;

@Component
public class DealerMapper {

    public Dealer map(DealerDTO dealerDTO){
        Dealer dealer= new Dealer();
        map(dealerDTO,dealer);
        return dealer;
    }

    public void map(DealerDTO dealerDTO, Dealer dealer){
        dealer.setName(dealerDTO.getName());
        dealer.setEmail(dealerDTO.getEmail());
        dealer.setTenantId(dealerDTO.getTenantId());
        dealer.setSubscriptionType(dealerDTO.getSubscriptionType());
    }

    public DealerDTO map(Dealer dealer){
        DealerDTO dealerDTO= new DealerDTO();
        dealerDTO.setName(dealer.getName());
        dealerDTO.setEmail(dealer.getEmail());
        dealerDTO.setTenantId(dealer.getTenantId());
        dealerDTO.setSubscriptionType(dealer.getSubscriptionType());
        dealerDTO.setId(dealer.getId());
        return dealerDTO;
    }
}
