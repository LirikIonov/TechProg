package com.sgu.banksspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SemiTransferDto {
    private BigDecimal amount;
    private String currency;
}