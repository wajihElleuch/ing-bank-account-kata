package fr.dev.kata.services;

import fr.dev.kata.models.Account;

import java.math.BigDecimal;

public interface BankService {
    void deposit(Account account, BigDecimal amount);
    void withdraw(Account account, BigDecimal amount);
}
