package fr.dev.kata;

import fr.dev.kata.models.Account;
import fr.dev.kata.models.Customer;
import fr.dev.kata.services.AccountService;
import fr.dev.kata.services.impl.AccountServiceImpl;

import java.math.BigDecimal;
import java.util.UUID;

public class KataApplication {
    public static void main(String[] args) {
        Customer customer = new Customer(UUID.randomUUID(), "wajih");
        Account account = new Account(UUID.randomUUID(),1);
        customer.addAccount(account);
        System.out.println(account.toString());
        AccountService accountService = new AccountServiceImpl();

        accountService.deposit(customer,1, BigDecimal.TEN);
        accountService.deposit(customer,1, BigDecimal.TEN);

        System.out.println(account.toString());
        accountService.withdraw(customer,1,BigDecimal.TEN);
        accountService.withdraw(customer,1,BigDecimal.TEN);
        System.out.println(account.toString());
        //bankService.withdraw(account,BigDecimal.TEN);
        System.out.println(accountService.getBalance(account));
        System.out.println(accountService.getTransactions(account));
    }
}
