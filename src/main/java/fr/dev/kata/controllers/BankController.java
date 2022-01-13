package fr.dev.kata.controllers;

import fr.dev.kata.models.Bank;
import fr.dev.kata.services.BankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "controller for bank endpoint")
@RestController
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @ApiOperation(value = "create a new bank")
    @PostMapping()
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        Bank result = this.bankService.create(bank);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
