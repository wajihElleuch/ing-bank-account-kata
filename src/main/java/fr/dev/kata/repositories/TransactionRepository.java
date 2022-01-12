package fr.dev.kata.repositories;

import fr.dev.kata.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {
    List<Transaction> findAllByAccount_Id(String accountId);
}
