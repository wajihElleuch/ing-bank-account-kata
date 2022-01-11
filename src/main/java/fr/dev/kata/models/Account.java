package fr.dev.kata.models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Account {

    private UUID id;
    private BigDecimal amount = BigDecimal.ZERO;
    private Customer customer;

    public void addAmount(BigDecimal amount) {
        if (amount != null) {
            this.amount = this.amount.add(amount);
        }
    }
}
