package fr.dev.kata.controllers;

import fr.dev.kata.models.Bank;
import fr.dev.kata.services.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping()
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        Bank result = this.bankService.create(bank);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
