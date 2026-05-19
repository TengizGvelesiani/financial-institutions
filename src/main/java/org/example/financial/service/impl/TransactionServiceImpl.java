package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.Transaction;
import org.example.financial.persistence.TransactionRepository;
import org.example.financial.service.TransactionService;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository = RepositoryFactory.transactionRepository();

    @Override
    public Transaction create(Transaction transaction) {
        return repository.create(transaction);
    }

    @Override
    public void update(Transaction transaction) {
        repository.update(transaction);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<Transaction> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Transaction> getByAccountId(Long accountId) {
        return repository.findByAccountId(accountId);
    }
}
