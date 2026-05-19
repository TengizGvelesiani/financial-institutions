package org.example.financial.persistence.jdbc;

import org.example.financial.model.Employee;
import org.example.financial.persistence.EmployeeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcEmployeeRepository extends JdbcSupport implements EmployeeRepository {

    private static final String INSERT = """
            INSERT INTO employees (financial_institution_id, full_name, hire_date, salary)
            VALUES (?, ?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE employees
            SET financial_institution_id = ?, full_name = ?, hire_date = ?, salary = ?
            WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM employees WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM employees WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM employees";
    private static final String SELECT_BY_FI = "SELECT * FROM employees WHERE financial_institution_id = ?";

    @Override
    public Employee create(Employee entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getFinancialInstitutionId());
            statement.setString(2, entity.getFullName());
            statement.setDate(3, JdbcUtils.toDate(entity.getHireDate()));
            statement.setBigDecimal(4, entity.getSalary());
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create employee", e);
        }
    }

    @Override
    public void update(Employee entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getFinancialInstitutionId());
            statement.setString(2, entity.getFullName());
            statement.setDate(3, JdbcUtils.toDate(entity.getHireDate()));
            statement.setBigDecimal(4, entity.getSalary());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update employee", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete employee", e);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
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
            throw new RuntimeException("Failed to find employee", e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Employee> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list employees", e);
        }
    }

    @Override
    public List<Employee> findByFinancialInstitutionId(Long financialInstitutionId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_FI)) {
            statement.setLong(1, financialInstitutionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Employee> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(mapRow(resultSet));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find employees by institution", e);
        }
    }

    private Employee mapRow(ResultSet resultSet) throws SQLException {
        Employee entity = new Employee();
        entity.setId(resultSet.getLong("id"));
        entity.setFinancialInstitutionId(resultSet.getLong("financial_institution_id"));
        entity.setFullName(resultSet.getString("full_name"));
        entity.setHireDate(JdbcUtils.getLocalDate(resultSet, "hire_date"));
        entity.setSalary(resultSet.getBigDecimal("salary"));
        return entity;
    }
}
