package fr.dev.kata;

import fr.dev.kata.models.Account;
import fr.dev.kata.models.Customer;
import fr.dev.kata.services.BankService;
import fr.dev.kata.services.impl.BankServiceImpl;

import java.math.BigDecimal;
import java.util.UUID;

public class KataApplication {
    public static void main(String[] args) {
        Customer customer = new Customer(UUID.randomUUID(), "wajih");
        Account account = new Account(UUID.randomUUID(), BigDecimal.ZERO);
        customer.addAccount(account);
        System.out.println(account.toString());
        BankService bankService = new BankServiceImpl();

        bankService.deposit(account, BigDecimal.TEN);
        bankService.deposit(account, BigDecimal.TEN);

        System.out.println(account.toString());
        bankService.withdraw(account,BigDecimal.TEN);
        bankService.withdraw(account,BigDecimal.TEN);
        System.out.println(account.toString());
        bankService.withdraw(account,BigDecimal.TEN);
    }
}
