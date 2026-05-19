package org.example.financial.service;

import org.example.financial.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {

    Transaction create(Transaction transaction);

    void update(Transaction transaction);

    void delete(Long id);

    Optional<Transaction> getById(Long id);

    List<Transaction> getAll();

    List<Transaction> getByAccountId(Long accountId);
}
