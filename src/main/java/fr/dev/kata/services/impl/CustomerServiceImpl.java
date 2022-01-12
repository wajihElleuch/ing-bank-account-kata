package fr.dev.kata.services.impl;

import fr.dev.kata.models.Customer;
import fr.dev.kata.repositories.CustomerRepository;
import fr.dev.kata.services.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        return this.customerRepository.save(customer);
    }
}
