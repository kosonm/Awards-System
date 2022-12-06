package com.charter.awardsystem.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Month;
import java.util.TreeMap;

@Data
@Builder
public class AwardResponse {

    private Long customerId;

    private Long totalPoints;

    private TreeMap<Month, Long> pointsByMonth;

}
