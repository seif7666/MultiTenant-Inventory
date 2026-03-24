package com.autocenter.inventory.repos;

import com.autocenter.inventory.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, UUID> {
}
