package com.charter.awardsystem.model.dto;

import lombok.*;

import java.time.Month;
import java.util.TreeMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardResponse {

    private Long customerId;

    private Long totalPoints;

    private TreeMap<Month, Long> pointsByMonth;

}
