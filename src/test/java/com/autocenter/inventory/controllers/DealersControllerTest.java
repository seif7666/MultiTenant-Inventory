package com.autocenter.inventory.controllers;

import com.autocenter.inventory.dto.DealerDTO;
import com.autocenter.inventory.dto.PageControlDTO;
import com.autocenter.inventory.enums.SubscriptionType;
import com.autocenter.inventory.helpers.DealersGenerator;
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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@ContextConfiguration(classes = DealersGenerator.class)
class DealersControllerTest {

    @Autowired
    private DealersController controller;
    @Autowired
    private DealerRepository dealerRepository;
    private List<Dealer> initialDealers;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private DealersGenerator dealersGenerator;

    @BeforeEach
    void setUp() {
        initialDealers= dealersGenerator.generateDealers();
        this.dealerRepository.saveAll(initialDealers);
        System.out.println("========================Starting the Test=======================================");
    }

    @AfterEach
    void tearDown() {
        System.out.println("========================Ending the Test=======================================");
        this.dealerRepository.deleteAll();
    }

    @Test
    void addDealerSuccessfully() {
        DealerDTO dealerDTO = createDealerDTO();
        this.controller.addDealer(dealerDTO);
        Dealer savedDealer= retrieveDealerByEmail(dealerDTO.getEmail());
        DealerDTO retreivedDTO= controller.getDealer(savedDealer.getId()).getBody();
        assertEquals(dealerDTO.getName(), retreivedDTO.getName());
        assertEquals(dealerDTO.getSubscriptionType(), retreivedDTO.getSubscriptionType());
        assertEquals(dealerDTO.getTenantId(),retreivedDTO.getTenantId());
        assertEquals(savedDealer.getId(),dealerDTO.getId());
    }

    @Test
    void addSameDealerTwice() {
        addDealerSuccessfully();
        assertThrows(RuntimeException.class, this::addDealerSuccessfully);
    }

    @Test
    void testSortingIsWorking() {
        PageControlDTO pageControlDTO= new PageControlDTO(null, null, "name");
        List<DealerDTO>retrievedDealers= this.controller.getDealers(pageControlDTO).getBody();
        List<Dealer> sortedDealers= this.initialDealers.stream().sorted(Comparator.comparing(Dealer::getName)).toList();
        assertEquals(sortedDealers.size(), retrievedDealers.size());
        for(int i = 0; i<sortedDealers.size(); i++){
            assertEquals(sortedDealers.get(i).getName(),retrievedDealers.get(i).getName());
        }
    }

    @Test
    void testPaginationIsWorking() {
        List<DealerDTO> allDealers= this.controller.getDealers(new PageControlDTO(null, null, "name")).getBody();
        int size= allDealers.size();
        int pageSize= 10;
        int pagesExpected= Math.ceilDiv(size,pageSize);
        System.out.println("pagesExpected="+pagesExpected);
        Iterator<DealerDTO> iterator= allDealers.iterator();
        for(int i = 0; i<pagesExpected; i++){
            List<DealerDTO>pagedDealers= this.controller.getDealers(new PageControlDTO( pageSize,i, "name")).getBody();
            System.out.println(pagedDealers.size());
            if(i<pagesExpected-1)
                assertEquals(pageSize,pagedDealers.size());
            for(DealerDTO dealerDTO: pagedDealers){
                DealerDTO currentDTO= iterator.next();
                assertEquals(dealerDTO.getName(),currentDTO.getName());
            }
        }
        assertFalse(iterator.hasNext());



    }

    @Test
    void updateDealer() {
        DealerDTO dealerDTO = createDealerDTO();
        this.controller.addDealer(dealerDTO);
        dealerDTO.setName("Updated Dealer");
        dealerDTO.setTenantId(new Random().nextInt(Integer.MAX_VALUE)+"");
        this.controller.updateDealer(dealerDTO.getId(), dealerDTO);
        DealerDTO updatedDealer= this.controller.getDealer(dealerDTO.getId()).getBody();
        assertEquals(dealerDTO.getName(), updatedDealer.getName());
        assertEquals(dealerDTO.getSubscriptionType(), updatedDealer.getSubscriptionType());
        assertEquals(dealerDTO.getTenantId(),updatedDealer.getTenantId());
    }

    @Test
    void deleteDealer() {
        DealerDTO dealerDTO = createDealerDTO();
        this.controller.addDealer(dealerDTO);
        Dealer dealer= entityManager.find(Dealer.class, dealerDTO.getId());
        assertNotNull(dealer);
        this.controller.deleteDealer(dealerDTO.getId());
        dealer= entityManager.find(Dealer.class, dealerDTO.getId());
        assertNull(dealer);
    }

    private DealerDTO createDealerDTO(){
        DealerDTO dealerDTO = new DealerDTO();
        dealerDTO.setName("Dealer1");
        dealerDTO.setEmail("dealer@com");
        dealerDTO.setSubscriptionType(SubscriptionType.PREMIUM);
        dealerDTO.setTenantId("TenantId");
        return dealerDTO;
    }

    private Dealer retrieveDealerByEmail(String email){
        TypedQuery<Dealer> query= entityManager.createQuery("SELECT e FROM Dealer  e WHERE e.email= :email",Dealer.class);
        query.setParameter("email",email);
        return query.getSingleResult();
    }
}