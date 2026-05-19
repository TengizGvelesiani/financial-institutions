package org.example.financial.persistence.jdbc;

import org.example.financial.model.Account;
import org.example.financial.model.dto.AccountOverviewDto;
import org.example.financial.persistence.AccountRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcAccountRepository extends JdbcSupport implements AccountRepository {

    private static final String INSERT = """
            INSERT INTO accounts (customer_id, account_number, balance)
            VALUES (?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE accounts SET customer_id = ?, account_number = ?, balance = ? WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM accounts WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM accounts WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM accounts";
    private static final String SELECT_BY_CUSTOMER = "SELECT * FROM accounts WHERE customer_id = ?";
    private static final String SELECT_OVERVIEW = """
            SELECT fi.id   AS financial_institution_id,
                   fi.name AS financial_institution_name,
                   cu.id   AS customer_id,
                   cu.full_name AS customer_name,
                   a.id    AS account_id,
                   a.account_number,
                   a.balance,
                   tr.id   AS transaction_id,
                   tr.amount AS transaction_amount,
                   lo.id   AS loan_id,
                   lo.principal AS loan_principal,
                   cd.id   AS card_id,
                   cd.card_number,
                   pp.passport_number
            FROM financial_institutions fi
                     INNER JOIN customers cu ON cu.financial_institution_id = fi.id
                     INNER JOIN accounts a ON a.customer_id = cu.id
                     LEFT JOIN transactions tr ON tr.account_id = a.id
                     LEFT JOIN loans lo ON lo.account_id = a.id
                     LEFT JOIN cards cd ON cd.account_id = a.id
                     LEFT JOIN passports pp ON pp.customer_id = cu.id
            WHERE a.id = ?
            """;

    @Override
    public Account create(Account entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getCustomerId());
            statement.setString(2, entity.getAccountNumber());
            statement.setBigDecimal(3, entity.getBalance());
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create account", e);
        }
    }

    @Override
    public void update(Account entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getCustomerId());
            statement.setString(2, entity.getAccountNumber());
            statement.setBigDecimal(3, entity.getBalance());
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update account", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete account", e);
        }
    }

    @Override
    public Optional<Account> findById(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find account", e);
        }
    }

    @Override
    public List<Account> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Account> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list accounts", e);
        }
    }

    @Override
    public List<Account> findByCustomerId(Long customerId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_CUSTOMER)) {
            statement.setLong(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Account> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(mapRow(resultSet));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find accounts by customer", e);
        }
    }

    @Override
    public List<AccountOverviewDto> findAccountOverviewByAccountId(Long accountId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_OVERVIEW)) {
            statement.setLong(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<AccountOverviewDto> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(mapOverview(resultSet));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find account overview", e);
        }
    }

    private Account mapRow(ResultSet resultSet) throws SQLException {
        Account entity = new Account();
        entity.setId(resultSet.getLong("id"));
        entity.setCustomerId(resultSet.getLong("customer_id"));
        entity.setAccountNumber(resultSet.getString("account_number"));
        entity.setBalance(resultSet.getBigDecimal("balance"));
        return entity;
    }

    private AccountOverviewDto mapOverview(ResultSet resultSet) throws SQLException {
        AccountOverviewDto dto = new AccountOverviewDto();
        dto.setFinancialInstitutionId(resultSet.getLong("financial_institution_id"));
        dto.setFinancialInstitutionName(resultSet.getString("financial_institution_name"));
        dto.setCustomerId(resultSet.getLong("customer_id"));
        dto.setCustomerName(resultSet.getString("customer_name"));
        dto.setAccountId(resultSet.getLong("account_id"));
        dto.setAccountNumber(resultSet.getString("account_number"));
        dto.setBalance(resultSet.getBigDecimal("balance"));
        long transactionId = resultSet.getLong("transaction_id");
        dto.setTransactionId(resultSet.wasNull() ? null : transactionId);
        dto.setTransactionAmount(resultSet.getBigDecimal("transaction_amount"));
        long loanId = resultSet.getLong("loan_id");
        dto.setLoanId(resultSet.wasNull() ? null : loanId);
        dto.setLoanPrincipal(resultSet.getBigDecimal("loan_principal"));
        long cardId = resultSet.getLong("card_id");
        dto.setCardId(resultSet.wasNull() ? null : cardId);
        dto.setCardNumber(resultSet.getString("card_number"));
        dto.setPassportNumber(resultSet.getString("passport_number"));
        return dto;
    }
}
