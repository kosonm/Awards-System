package com.charter.awardsystem.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PurchaseDto {

    private Long customerId;

    private BigDecimal amount;

    private LocalDateTime date;

}
