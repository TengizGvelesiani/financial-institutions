package org.example.financial.service;

import org.example.financial.model.Account;
import org.example.financial.model.dto.AccountOverviewDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account create(Account account);

    void update(Account account);

    void delete(Long id);

    Optional<Account> getById(Long id);

    List<Account> getAll();

    List<Account> getByCustomerId(Long customerId);

    List<AccountOverviewDto> getAccountOverview(Long accountId);
}
