package com.autocenter.inventory.repos;

import com.autocenter.inventory.model.Dealer;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DealerRepository extends ListPagingAndSortingRepository<Dealer, UUID> {
}
