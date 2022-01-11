package fr.dev.kata.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Customer {

    private UUID id;
    private String name;
    private List<Account> accounts;

    public Customer(UUID id, String name) {
        this.id = id;
        this.name = name;
        this.accounts=new ArrayList<>();
    }
    public void addAccount(Account account){
        this.accounts.add(account);
    }

}
