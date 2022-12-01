package com.charter.awardsystem.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Purchase extends AbstractEntity {

    private Long customerId;

    private BigDecimal amount;

    private LocalDateTime date;

}
