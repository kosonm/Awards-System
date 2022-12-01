package com.charter.awardsystem.service;

import com.charter.awardsystem.model.Purchase;
import com.charter.awardsystem.model.dto.AwardResponse;
import com.charter.awardsystem.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;


    @Override
    public AwardResponse calculateAllPoints(Long customerId) {

        LOGGER.info("Method calculateAllPoints started...");
        List<Purchase> purchasesByCustomerId = purchaseRepository.getPurchasesByCustomerId(customerId);

        Integer totalPoints = purchasesByCustomerId
                .stream()
                .map(Purchase::getAmount)
                .map(amount -> doOperations(amount))
                .collect(Collectors.summingInt(Integer::intValue));

        return AwardResponse.builder()
                .customerId(customerId)
                .rewardPoints(totalPoints)
                .build();
    }

    @Override
    public AwardResponse calculatePointsByMonth(int monthNumber) {
        return null;
    }

    private int doOperations(BigDecimal amount) {
        int intValue = amount.intValue(); // TODO: change var name




        return 0;
    }
}
