package fr.dev.kata.services.impl;

import fr.dev.kata.models.Bank;
import fr.dev.kata.repositories.BankRepository;
import fr.dev.kata.services.BankService;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank create(Bank bank) {
        return this.bankRepository.save(bank);
    }
}
