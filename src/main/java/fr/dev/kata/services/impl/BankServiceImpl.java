package fr.dev.kata.services.impl;

import fr.dev.kata.models.Account;
import fr.dev.kata.services.BankService;

import java.math.BigDecimal;
import java.util.Objects;

public class BankServiceImpl implements BankService {
    private static final BigDecimal MIN_VALUE = new BigDecimal("0.01");

    @Override
    public void deposit(Account account, BigDecimal amount) {
        Objects.requireNonNull(amount);
        Objects.requireNonNull(account);
        if (amount.compareTo(MIN_VALUE) < 0) {
            throw new IllegalArgumentException("amount can't be less than 0.01");
        }
        account.addAmount(amount);
    }

    @Override
    public void withdraw(Account account, BigDecimal amount) {
        Objects.requireNonNull(amount);
        Objects.requireNonNull(account);

        if(account.getAmount().subtract(amount).compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException("not enough balance");
        }
        account.withdrawAmount(amount);
    }
}
