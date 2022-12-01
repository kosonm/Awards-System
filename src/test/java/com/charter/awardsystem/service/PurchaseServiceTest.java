package com.charter.awardsystem.service;

import com.charter.awardsystem.model.Purchase;
import com.charter.awardsystem.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class PurchaseServiceTest {


    @Autowired
    PurchaseService purchaseService;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Test
    public void temp(){
        int num1 = 230;
        int divisor = 100;

        System.out.println(num1 % divisor);
        System.out.println(num1 / divisor);


    }


    @Test
    public void shouldCalculateAwards(){
        createTestPurchases();





    }

    private void createTestPurchases(){
        Purchase purchase = new Purchase();
        purchase.setAmount(BigDecimal.valueOf(180));
        purchase.setCustomerId(1l);
        purchase.setDate(LocalDateTime.now());

        Purchase purchase2 = new Purchase();
        purchase.setAmount(BigDecimal.valueOf(70));
        purchase.setCustomerId(1l);
        purchase.setDate(LocalDateTime.now());

        Purchase purchase3 = new Purchase();
        purchase.setAmount(BigDecimal.valueOf(230));
        purchase.setCustomerId(1l);
        purchase.setDate(LocalDateTime.now());

        Purchase purchase4 = new Purchase();
        purchase.setAmount(BigDecimal.valueOf(7));
        purchase.setCustomerId(1l);
        purchase.setDate(LocalDateTime.now());

        List<Purchase> purchases = new ArrayList<>();
        purchases.add(purchase);
        purchases.add(purchase2);
        purchases.add(purchase3);
        purchases.add(purchase4);

        purchaseRepository.saveAll(purchases);
    }



}
