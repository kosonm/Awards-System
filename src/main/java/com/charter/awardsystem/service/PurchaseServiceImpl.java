package com.charter.awardsystem.service;

import com.charter.awardsystem.exception.NoDataFoundException;
import com.charter.awardsystem.model.Purchase;
import com.charter.awardsystem.model.constant.AwardConstants;
import com.charter.awardsystem.model.dto.AwardResponse;
import com.charter.awardsystem.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseServiceImpl.class);

    private final PurchaseRepository purchaseRepository;

    @Override
    public AwardResponse getAwardPointsByCustomerId(Long customerId) {

        LOGGER.info("Method getRewardPointsByCustomerId started...");
        List<Purchase> purchasesByCustomerId = purchaseRepository.getPurchasesByCustomerId(customerId);

        if (purchasesByCustomerId.isEmpty()) {
            throw new NoDataFoundException("No purchases found for customer with id: " + customerId);
        }

        TreeMap<Month, Long> pointsByMonth = purchasesByCustomerId
                .stream()
                .collect(
                        Collectors.groupingBy(
                                purchase -> purchase.getDate().getMonth(),
                                TreeMap::new,
                                Collectors.summingLong(purchase -> calculatePoints(purchase.getAmount()))
                        )
                );

        long totalPoints = pointsByMonth.values()
                .stream()
                .mapToLong(Long::longValue)
                .sum();


        return AwardResponse.builder()
                .customerId(customerId)
                .totalPoints(totalPoints)
                .pointsByMonth(pointsByMonth)
                .build();
    }

    private Long calculatePoints(BigDecimal amount) {
        long roundedAmount = amount.longValue();

        if (roundedAmount > AwardConstants.hundredDollarLimit) {
            return (roundedAmount - AwardConstants.hundredDollarLimit) * 2 + AwardConstants.fiftyDollarLimit;
        }
        if (roundedAmount > AwardConstants.fiftyDollarLimit) {
            return roundedAmount - AwardConstants.fiftyDollarLimit;
        }
        return 0L;
    }
}
