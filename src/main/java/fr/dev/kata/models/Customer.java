package fr.dev.kata.models;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Customer {

    private UUID id;
    private String name;
    private List<Account> accounts;


}
