package fr.dev.kata.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Account {

    private UUID id;
    private BigDecimal amount;

    public void addAmount(BigDecimal amount) {
        if (amount != null) {
            this.amount = this.amount.add(amount);
        }
    }

    public void withdrawAmount(BigDecimal amount) {
        if (amount != null) {
            this.amount = this.amount.subtract(amount);
        }

    }
}
