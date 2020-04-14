package com.sgu.banksspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransferDto {
    private Long accountFrom;
    private BigDecimal amount;
}