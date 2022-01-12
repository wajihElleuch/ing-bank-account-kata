package fr.dev.kata.services.impl;

import fr.dev.kata.dto.TransactionDTO;
import fr.dev.kata.exceptions.AccountNotFoundException;
import fr.dev.kata.models.Account;
import fr.dev.kata.models.Transaction;
import fr.dev.kata.models.TransactionType;
import fr.dev.kata.repositories.AccountRepository;
import fr.dev.kata.services.AccountService;
import fr.dev.kata.services.TransactionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private static final BigDecimal MIN_VALUE = new BigDecimal("0.01");

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

   @Override
   @Transactional
    public Transaction deposit(TransactionDTO transactionDTO) throws AccountNotFoundException {
        if (transactionDTO.getAmount().compareTo(MIN_VALUE) > 0) {
            Optional<Account> account = this.accountRepository.findById(transactionDTO.getAccountId());
            if (account.isPresent()) {
                Account depositAccount = account.get();
                BigDecimal newAmount = depositAccount.getAmount().add(transactionDTO.getAmount());
                depositAccount.setAmount(newAmount);
                this.accountRepository.save(depositAccount);
                return this.transactionService.create(TransactionType.DEPOSIT, depositAccount, transactionDTO.getAmount());
            }
            throw new AccountNotFoundException(String.format("didn't find Account with id: %s", transactionDTO.getAccountId()));
        }
       throw new IllegalArgumentException("amount must be grater than " + MIN_VALUE);
   }

    @Override
    @Transactional
    public synchronized Transaction withdraw(TransactionDTO transactionDTO) {
        if (transactionDTO.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            Optional<Account> account = this.accountRepository.findById(transactionDTO.getAccountId());
            Account withDrawAccount=account.orElseThrow(()->
                    new AccountNotFoundException(String.format("didn't find Account with id: %s", transactionDTO.getAccountId())));
            if (canWithdraw(transactionDTO, account)) {
                BigDecimal newAmount = withDrawAccount.getAmount().subtract(transactionDTO.getAmount());
                withDrawAccount.setAmount(newAmount);
                this.accountRepository.save(withDrawAccount);
                return this.transactionService.create(TransactionType.WITHDRAW, withDrawAccount, transactionDTO.getAmount());
            }
            throw new IllegalArgumentException("can't withdraw insufficient balance");
        }
        throw new IllegalArgumentException("amount must be grater than 0");
    }

    private boolean canWithdraw(TransactionDTO transactionDTO, Optional<Account> account) {
        return account.filter(value -> value.getAmount().subtract(transactionDTO.getAmount())
                .compareTo(BigDecimal.ZERO) >= 0).isPresent();
    }

    @Override
    public BigDecimal getBalance(String id) {
        Optional<Account> account = this.accountRepository.findById(id);
        return account.map(Account::getAmount).orElseThrow(()->
                new AccountNotFoundException(String.format("didn't find Account with id: %s", id)));
    }

    @Override
    public List<Transaction> getTransactions(String id) {
        return this.transactionService.getAllByAccountId(id);
    }

    @Override
    public Account create(Account account) {
        return this.accountRepository.save(account);
    }

    @Override
    public List<Account> findAll() {
        return this.accountRepository.findAll();
    }


}
