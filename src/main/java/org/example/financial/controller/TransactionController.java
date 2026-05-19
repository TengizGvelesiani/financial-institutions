package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.Transaction;
import org.example.financial.service.TransactionService;

import java.util.List;
import java.util.Optional;

public class TransactionController {

    private final TransactionService service = ServiceFactory.transactionService();

    public Transaction create(Transaction transaction) {
        return service.create(transaction);
    }

    public void update(Transaction transaction) {
        service.update(transaction);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Optional<Transaction> getById(Long id) {
        return service.getById(id);
    }

    public List<Transaction> getAll() {
        return service.getAll();
    }

    public List<Transaction> getByAccountId(Long accountId) {
        return service.getByAccountId(accountId);
    }
}
