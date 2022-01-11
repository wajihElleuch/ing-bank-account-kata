package fr.dev.kata.models;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Bank {

    private UUID id;
    private String name;
    private List<Customer> customers;
}
