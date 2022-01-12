package fr.dev.kata.services.impl;

import fr.dev.kata.models.Account;
import fr.dev.kata.models.Customer;
import fr.dev.kata.models.Transaction;
import fr.dev.kata.services.AccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private static final BigDecimal MIN_VALUE = new BigDecimal("0.01");

    @Override
    public void deposit(Customer customer, int numAccount, BigDecimal amount) {
        Objects.requireNonNull(amount);
        Objects.requireNonNull(customer);
        Objects.requireNonNull(numAccount);
        Account account = customer.getAccounts().
                stream().
                filter(account1 -> account1.getNumAccount() == numAccount).
                findFirst().orElseThrow(() -> new IllegalArgumentException("account not exist"));
        if (amount.compareTo(MIN_VALUE) < 0) {
            throw new IllegalArgumentException("amount can't be less than 0.01");
        }
        account.addAmount(amount);
    }

    @Override
    public synchronized void withdraw(Customer customer, int numAccount, BigDecimal amount) {
        Objects.requireNonNull(amount);
        Objects.requireNonNull(customer);
        Objects.requireNonNull(numAccount);
        Account account = customer.getAccounts().
                stream().
                filter(account1 -> account1.getNumAccount() == numAccount).
                findFirst().orElseThrow(() -> new IllegalArgumentException("account not exist"));
        if (account.getAmount().subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("not enough balance");
        }
        account.withdrawAmount(amount);
    }

    @Override
    public BigDecimal getBalance(Account account) {
        return account.getAmount();
    }

    @Override
    public List<Transaction> getTransactions(Account account) {
        return account.getTransactions();
    }


}
