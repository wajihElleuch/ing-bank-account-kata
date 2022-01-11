package fr.dev.kata.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Bank {

    private UUID id;
    private String name;
    private List<Customer> customers;
}
