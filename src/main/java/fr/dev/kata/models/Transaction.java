package fr.dev.kata.models;
import lombok.Data;

import java.time.Instant;

@Data
public class Transaction {
    private TransactionType type ;
    private final Instant time = Instant.now();

    public Transaction(TransactionType type) {
        this.type = type;
    }
}
