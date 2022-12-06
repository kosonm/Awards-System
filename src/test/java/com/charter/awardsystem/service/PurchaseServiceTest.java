package com.charter.awardsystem.service;

import com.charter.awardsystem.model.Purchase;
import com.charter.awardsystem.repository.PurchaseRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PurchaseServiceTest {

    @InjectMocks
    PurchaseServiceImpl purchaseServiceImpl;

    @Mock
    PurchaseRepository purchaseRepository;

    @Test
    public void testCalculatePointsFormulaForCustomer() {
        // Arrange
        List<Purchase> purchases = getTestPurchases();
        when(purchaseRepository.getPurchasesByCustomerId(1L)).thenReturn(purchases);

        // Act
        final var actual = purchaseServiceImpl.getAwardPointsByCustomerId(1L);

        // Assert
        assertThat(actual.getCustomerId()).isEqualTo(1L);
        assertThat(actual.getTotalPoints()).isEqualTo(2590L);

        assertThat(actual.getPointsByMonth().get(Month.DECEMBER)).isEqualTo(210L);
        assertThat(actual.getPointsByMonth().get(Month.NOVEMBER)).isEqualTo(532L);
        assertThat(actual.getPointsByMonth().get(Month.OCTOBER)).isEqualTo(1848L);
    }

    @Test
    public void testFirstLimit() {
        // Arrange
        Purchase purchase = Purchase.builder()
                .amount(BigDecimal.valueOf(50.99))
                .date(LocalDateTime.now())
                .customerId(1L)
                .build();
        when(purchaseRepository.getPurchasesByCustomerId(anyLong())).thenReturn(List.of(purchase));

        // Act
        final var actual = purchaseServiceImpl.getAwardPointsByCustomerId(anyLong());

        // Assert
        assertThat(actual.getTotalPoints()).isEqualTo(0L);
    }

    @Test
    public void testSecondLimit() {
        // Arrange
        Purchase purchase = Purchase.builder()
                .amount(BigDecimal.valueOf(100.99))
                .date(LocalDateTime.now())
                .customerId(1L)
                .build();
        when(purchaseRepository.getPurchasesByCustomerId(anyLong())).thenReturn(List.of(purchase));

        // Act
        final var actual = purchaseServiceImpl.getAwardPointsByCustomerId(anyLong());

        // Assert
        assertThat(actual.getTotalPoints()).isEqualTo(50L);
    }


    private List<Purchase> getTestPurchases() {
        List<Purchase> result = new ArrayList<>();

        LocalDateTime date = LocalDateTime.of(2022, Month.DECEMBER, 1, 1, 1, 1, 1);

        Purchase purchase = Purchase.builder()
                .customerId(1L)
                .date(date)
                .amount(BigDecimal.valueOf(180))
                .build();

        Purchase purchase2 = Purchase.builder()
                .customerId(1L)
                .date(date.minusMonths(1))
                .amount(BigDecimal.valueOf(341.24))
                .build();

        Purchase purchase3 = Purchase.builder()
                .customerId(1L)
                .date(date.minusMonths(1))
                .amount(BigDecimal.valueOf(9.5))
                .build();

        Purchase purchase4 = Purchase.builder()
                .customerId(1L)
                .date(date.minusMonths(2))
                .amount(BigDecimal.valueOf(999.21))
                .build();

        Purchase purchase5 = Purchase.builder()
                .customerId(1L)
                .date(date)
                .amount(BigDecimal.valueOf(10))
                .build();

        Purchase purchase6 = Purchase.builder()
                .customerId(2L)
                .date(date.minusMonths(2))
                .amount(BigDecimal.valueOf(10))
                .build();

        result.add(purchase);
        result.add(purchase2);
        result.add(purchase3);
        result.add(purchase4);
        result.add(purchase5);
        result.add(purchase6);

        return result;
    }

}
