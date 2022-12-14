package com.charter.awardsystem.repository;

import com.charter.awardsystem.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> getPurchasesByCustomerId(Long customerId);

}
