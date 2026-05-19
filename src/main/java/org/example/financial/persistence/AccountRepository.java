package org.example.financial.persistence;

import org.example.financial.model.Account;
import org.example.financial.model.dto.AccountOverviewDto;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account> {

    Optional<Account> findById(Long id);

    List<Account> findAll();

    List<Account> findByCustomerId(Long customerId);

    List<AccountOverviewDto> findAccountOverviewByAccountId(Long accountId);
}
