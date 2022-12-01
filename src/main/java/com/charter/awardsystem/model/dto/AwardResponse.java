package com.charter.awardsystem.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AwardResponse {

    private Long customerId;

    private int rewardPoints;

}
