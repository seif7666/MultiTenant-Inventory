package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.DealerDTO;
import com.autocenter.inventory.enums.SubscriptionType;
import com.autocenter.inventory.model.Dealer;
import com.autocenter.inventory.repos.DealerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DealersControllerTest {

    @Autowired
    private DealersController controller;
    @Autowired
    private DealerRepository dealerRepository;
    private List<Dealer> initialDealers;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        initialDealers= new LinkedList<>();
        for(int i= 0 ; i<50; i++){
            Dealer dealer= new Dealer();
            dealer.setSubscriptionType((i&1) ==0 ? SubscriptionType.BASIC : SubscriptionType.PREMIUM);
            dealer.setEmail(generateRandomEmail());
            dealer.setName(dealer.getEmail().substring(0,dealer.getEmail().indexOf("@")));
            dealer.setTenantId(new Random().nextInt(Integer.MAX_VALUE)+"");
            initialDealers.add(dealer);
        }
        this.dealerRepository.saveAll(initialDealers);
        System.out.println("========================Starting the Test=======================================");
    }

    private String generateRandomEmail() {
        int length= new Random().nextInt(15)+5;
        String email= "";
        for(int i=0; i<length; i++){
            char letter= (char)('a' + new Random().nextInt(26));
            email+=letter;
        }
        return email+"@";
    }

    @AfterEach
    void tearDown() {
        System.out.println("========================Ending the Test=======================================");
        this.dealerRepository.deleteAll();
    }

    @Test
    void addDealerSuccessfully() {
        DealerDTO dealerDTO = new DealerDTO();
        dealerDTO.setName("Dealer1");
        dealerDTO.setEmail("dealer@com");
        dealerDTO.setSubscriptionType(SubscriptionType.PREMIUM);
        dealerDTO.setTenantId("TenantId");
        this.controller.addDealer(dealerDTO);
        TypedQuery<Dealer> query= entityManager.createQuery("SELECT e FROM Dealer  e WHERE e.email= :email",Dealer.class);
        query.setParameter("email",dealerDTO.getEmail());
        Dealer savedDealer= query.getSingleResult();
        DealerDTO retreivedDTO= controller.getDealer(savedDealer.getId()).getBody();
        assertEquals(dealerDTO.getName(), retreivedDTO.getName());
        assertEquals(dealerDTO.getSubscriptionType(), retreivedDTO.getSubscriptionType());
        assertEquals(dealerDTO.getTenantId(),retreivedDTO.getTenantId());
    }

    @Test
    void addSameDealerTwice() {
        addDealerSuccessfully();
        assertThrows(RuntimeException.class, this::addDealerSuccessfully);
    }

    @Test
    void getDealers() {
        assertEquals(this.initialDealers.size(), this.controller.getDealers(null, null, null).getBody().size());
    }

    @Test
    void updateDealer() {
    }

    @Test
    void deleteDealer() {
    }
}