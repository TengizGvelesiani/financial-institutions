package org.example.financial.persistence;

import org.example.financial.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction> {

    Optional<Transaction> findById(Long id);

    List<Transaction> findAll();

    List<Transaction> findByAccountId(Long accountId);
}
