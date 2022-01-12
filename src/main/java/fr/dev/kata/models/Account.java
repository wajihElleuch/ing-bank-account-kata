package fr.dev.kata.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class Account {

    @NonNull
    private UUID id;
    private BigDecimal amount = BigDecimal.ZERO;
    @NonNull
    private int numAccount;
    private List<Transaction> transactions = new ArrayList<>();

    public void addAmount(BigDecimal amount) {
        if (amount != null) {
            this.transactions.add(new Transaction(TransactionType.DEPOSIT));
            this.amount = this.amount.add(amount);
        }
    }

    public void withdrawAmount(BigDecimal amount) {
        if (amount != null) {
            this.transactions.add(new Transaction(TransactionType.WITHDRAW));
            this.amount = this.amount.subtract(amount);
        }

    }
}
