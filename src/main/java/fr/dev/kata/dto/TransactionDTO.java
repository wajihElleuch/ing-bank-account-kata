package fr.dev.kata.dto;

import fr.dev.kata.models.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionDTO {
    private BigDecimal amount;
    private TransactionType transactionType;
}
