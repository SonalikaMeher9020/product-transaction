package com.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Setter
@Getter
public class CustomerCostReportDTO {
    private Integer customerId;
    private BigDecimal totalCost;

}
