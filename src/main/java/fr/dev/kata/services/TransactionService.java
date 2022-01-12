package fr.dev.kata.services;

import fr.dev.kata.models.Account;
import fr.dev.kata.models.Transaction;
import fr.dev.kata.models.TransactionType;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction create(TransactionType transactionType, Account account, BigDecimal amount);
    List<Transaction> getAllByAccountId(String accountId);
}
