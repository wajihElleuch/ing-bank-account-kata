package fr.dev.kata.services.impl;

import fr.dev.kata.models.Account;
import fr.dev.kata.models.Transaction;
import fr.dev.kata.models.TransactionType;
import fr.dev.kata.repositories.TransactionRepository;
import fr.dev.kata.services.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction create(TransactionType transactionType, Account account, BigDecimal amount) {
        Transaction transaction = new Transaction(transactionType, account, amount);
        return this.transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllByAccountId(String accountId) {
        return this.transactionRepository.findAllByAccount_Id(accountId);
    }
}
