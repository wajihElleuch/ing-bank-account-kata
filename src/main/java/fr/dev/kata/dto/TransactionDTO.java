package fr.dev.kata.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionDTO {
    private String accountId;
    private BigDecimal amount;
}
