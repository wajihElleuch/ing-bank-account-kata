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

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody TransactionDTO transactionDTO) throws AccountNotFoundException {
            Transaction transaction = this.accountService.deposit(transactionDTO);
            return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withDraw(@RequestBody TransactionDTO transactionDTO){
        Transaction transaction = this.accountService.withdraw(transactionDTO);
        return new ResponseEntity<>(transaction, HttpStatus.OK);
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
