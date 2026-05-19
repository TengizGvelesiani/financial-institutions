package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.Account;
import org.example.financial.model.dto.AccountOverviewDto;
import org.example.financial.persistence.AccountRepository;
import org.example.financial.service.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository = RepositoryFactory.accountRepository();

    @Override
    public Account create(Account account) {
        return repository.create(account);
    }

    @Override
    public void update(Account account) {
        repository.update(account);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<Account> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Account> getByCustomerId(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    @Override
    public List<AccountOverviewDto> getAccountOverview(Long accountId) {
        return repository.findAccountOverviewByAccountId(accountId);
    }
}
