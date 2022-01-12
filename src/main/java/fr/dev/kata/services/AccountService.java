package fr.dev.kata.services;

import fr.dev.kata.dto.TransactionDTO;
import fr.dev.kata.exceptions.AccountNotFoundException;
import fr.dev.kata.models.Account;
import fr.dev.kata.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account create(Account account);
    Transaction deposit(TransactionDTO transactionDTO) throws AccountNotFoundException;
    Transaction withdraw(TransactionDTO transactionDTO) throws AccountNotFoundException;
    BigDecimal getBalance(String id) throws AccountNotFoundException;
    List<Transaction> getTransactions(String id);
}
