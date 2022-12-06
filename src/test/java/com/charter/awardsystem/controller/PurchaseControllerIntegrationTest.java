package com.charter.awardsystem.controller;

import com.charter.awardsystem.Application;
import com.charter.awardsystem.model.Purchase;
import com.charter.awardsystem.repository.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Application.class)
@AutoConfigureMockMvc
public class PurchaseControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Test
    public void givenPurchases_whenGetPoints_thenStatus200() throws Exception {
        createTestPurchase();

        mvc.perform(get("/purchase/1/points")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId", is(1)));
    }

    @Test
    public void givenNoPurchases_whenGetPoints_thenStatus404() throws Exception {
        mvc.perform(get("/purchase/1/points")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void givenBadUrl_whenGetPoints_thenStatus404() throws Exception {
        mvc.perform(get("/purchase/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    void createTestPurchase() {
        Purchase purchase = Purchase.builder()
                .amount(BigDecimal.valueOf(180))
                .date(LocalDateTime.now())
                .customerId(1L).build();

        purchaseRepository.save(purchase);
    }
}
