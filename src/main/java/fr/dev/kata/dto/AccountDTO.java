package fr.dev.kata.dto;

import fr.dev.kata.models.Bank;
import fr.dev.kata.models.Customer;
import fr.dev.kata.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public class AccountDTO {
    private String id;
    private BigDecimal amount;
    private int numAccount;
    List<Transaction> transactions ;
    Bank bank;
    Customer customer;
}
