package com.charter.awardsystem.service;

import com.charter.awardsystem.model.dto.AwardResponse;

public interface PurchaseService {

    AwardResponse calculateAllPoints(Long customerId);

    AwardResponse calculatePointsByMonth(int monthNumber);

}
