package fr.dev.kata.controllers;

import fr.dev.kata.dto.TransactionDTO;
import fr.dev.kata.exceptions.AccountNotFoundException;
import fr.dev.kata.models.Account;
import fr.dev.kata.models.Transaction;
import fr.dev.kata.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<List<Account>> getAll() {
        List<Account> result = this.accountService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account result = this.accountService.create(account);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{id}/transaction")
    public ResponseEntity<Transaction> deposit(@PathVariable String id, @RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {
        switch (transactionDTO.getTransactionType()){
            case DEPOSIT: return new ResponseEntity<>(this.accountService.deposit(id, transactionDTO), HttpStatus.OK);
            case WITHDRAW: return new ResponseEntity<>(this.accountService.withdraw(id, transactionDTO), HttpStatus.OK);
            default: return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String id) throws AccountNotFoundException {
        BigDecimal balance= this.accountService.getBalance(id);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactions(@PathVariable String id){
        List<Transaction> transactions = this.accountService.getTransactions(id);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
