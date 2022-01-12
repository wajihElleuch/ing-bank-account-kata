package fr.dev.kata.controllers;

import fr.dev.kata.models.Customer;
import fr.dev.kata.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer result = this.customerService.create(customer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
