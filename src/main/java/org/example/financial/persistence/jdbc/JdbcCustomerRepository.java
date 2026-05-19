package org.example.financial.persistence.jdbc;

import org.example.financial.model.Customer;
import org.example.financial.persistence.CustomerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCustomerRepository extends JdbcSupport implements CustomerRepository {

    private static final String INSERT = """
            INSERT INTO customers (financial_institution_id, full_name, birth_date, registered_at, active)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE customers
            SET financial_institution_id = ?, full_name = ?, birth_date = ?, registered_at = ?, active = ?
            WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM customers WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM customers WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM customers";
    private static final String SELECT_BY_FI = "SELECT * FROM customers WHERE financial_institution_id = ?";

    @Override
    public Customer create(Customer entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getFinancialInstitutionId());
            statement.setString(2, entity.getFullName());
            statement.setDate(3, JdbcUtils.toDate(entity.getBirthDate()));
            statement.setTimestamp(4, JdbcUtils.toTimestamp(entity.getRegisteredAt()));
            statement.setBoolean(5, entity.isActive());
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create customer", e);
        }
    }

    @Override
    public void update(Customer entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getFinancialInstitutionId());
            statement.setString(2, entity.getFullName());
            statement.setDate(3, JdbcUtils.toDate(entity.getBirthDate()));
            statement.setTimestamp(4, JdbcUtils.toTimestamp(entity.getRegisteredAt()));
            statement.setBoolean(5, entity.isActive());
            statement.setLong(6, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update customer", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete customer", e);
        }
    }

    @Override
    public Optional<Customer> findById(Long id) {
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
            throw new RuntimeException("Failed to find customer", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        return queryList(SELECT_ALL);
    }

    @Override
    public List<Customer> findByFinancialInstitutionId(Long financialInstitutionId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_FI)) {
            statement.setLong(1, financialInstitutionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Customer> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(mapRow(resultSet));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find customers by institution", e);
        }
    }

    private List<Customer> queryList(String sql) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Customer> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list customers", e);
        }
    }

    private Customer mapRow(ResultSet resultSet) throws SQLException {
        Customer entity = new Customer();
        entity.setId(resultSet.getLong("id"));
        entity.setFinancialInstitutionId(resultSet.getLong("financial_institution_id"));
        entity.setFullName(resultSet.getString("full_name"));
        entity.setBirthDate(JdbcUtils.getLocalDate(resultSet, "birth_date"));
        entity.setRegisteredAt(JdbcUtils.getLocalDateTime(resultSet, "registered_at"));
        entity.setActive(JdbcUtils.getBoolean(resultSet, "active"));
        return entity;
    }
}
