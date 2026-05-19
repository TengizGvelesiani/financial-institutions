package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.Account;
import org.example.financial.model.dto.AccountOverviewDto;
import org.example.financial.service.AccountService;

import java.util.List;
import java.util.Optional;

public class AccountController {

    private final AccountService service = ServiceFactory.accountService();

    public Account create(Account account) {
        return service.create(account);
    }

    public void update(Account account) {
        service.update(account);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Optional<Account> getById(Long id) {
        return service.getById(id);
    }

    public List<Account> getAll() {
        return service.getAll();
    }

    public List<Account> getByCustomerId(Long customerId) {
        return service.getByCustomerId(customerId);
    }

    public List<AccountOverviewDto> getAccountOverview(Long accountId) {
        return service.getAccountOverview(accountId);
    }
}
