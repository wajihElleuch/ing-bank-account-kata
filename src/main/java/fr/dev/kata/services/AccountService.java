package fr.dev.kata.services;

import fr.dev.kata.models.Account;
import fr.dev.kata.models.Customer;
import fr.dev.kata.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void deposit(Customer customer,int numAccount, BigDecimal amount);
    void withdraw(Customer customer,int numAccount, BigDecimal amount);
    BigDecimal getBalance(Account account);
    List<Transaction> getTransactions(Account account);

}
