package org.example.financial.persistence.jdbc;

import org.example.financial.model.Transaction;
import org.example.financial.persistence.TransactionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcTransactionRepository extends JdbcSupport implements TransactionRepository {

    private static final String INSERT = """
            INSERT INTO transactions (account_id, amount, transaction_time, description)
            VALUES (?, ?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE transactions
            SET account_id = ?, amount = ?, transaction_time = ?, description = ?
            WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM transactions WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM transactions WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM transactions";
    private static final String SELECT_BY_ACCOUNT = "SELECT * FROM transactions WHERE account_id = ?";

    @Override
    public Transaction create(Transaction entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getAccountId());
            statement.setBigDecimal(2, entity.getAmount());
            statement.setTimestamp(3, JdbcUtils.toTimestamp(entity.getTransactionTime()));
            statement.setString(4, entity.getDescription());
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create transaction", e);
        }
    }

    @Override
    public void update(Transaction entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getAccountId());
            statement.setBigDecimal(2, entity.getAmount());
            statement.setTimestamp(3, JdbcUtils.toTimestamp(entity.getTransactionTime()));
            statement.setString(4, entity.getDescription());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update transaction", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete transaction", e);
        }
    }

    @Override
    public Optional<Transaction> findById(Long id) {
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
            throw new RuntimeException("Failed to find transaction", e);
        }
    }

    @Override
    public List<Transaction> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Transaction> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list transactions", e);
        }
    }

    @Override
    public List<Transaction> findByAccountId(Long accountId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ACCOUNT)) {
            statement.setLong(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Transaction> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(mapRow(resultSet));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find transactions by account", e);
        }
    }

    private Transaction mapRow(ResultSet resultSet) throws SQLException {
        Transaction entity = new Transaction();
        entity.setId(resultSet.getLong("id"));
        entity.setAccountId(resultSet.getLong("account_id"));
        entity.setAmount(resultSet.getBigDecimal("amount"));
        entity.setTransactionTime(JdbcUtils.getLocalDateTime(resultSet, "transaction_time"));
        entity.setDescription(resultSet.getString("description"));
        return entity;
    }
}
