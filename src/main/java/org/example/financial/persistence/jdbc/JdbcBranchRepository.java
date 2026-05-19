package org.example.financial.persistence.jdbc;

import org.example.financial.model.Branch;
import org.example.financial.persistence.BranchRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBranchRepository extends JdbcSupport implements BranchRepository {

    private static final String INSERT = """
            INSERT INTO branches (financial_institution_id, name, address)
            VALUES (?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE branches SET financial_institution_id = ?, name = ?, address = ? WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM branches WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM branches WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM branches";
    private static final String SELECT_BY_FI = "SELECT * FROM branches WHERE financial_institution_id = ?";

    @Override
    public Branch create(Branch entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getFinancialInstitutionId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getAddress());
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create branch", e);
        }
    }

    @Override
    public void update(Branch entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getFinancialInstitutionId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getAddress());
            statement.setLong(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update branch", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete branch", e);
        }
    }

    @Override
    public Optional<Branch> findById(Long id) {
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
            throw new RuntimeException("Failed to find branch", e);
        }
    }

    @Override
    public List<Branch> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Branch> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list branches", e);
        }
    }

    @Override
    public List<Branch> findByFinancialInstitutionId(Long financialInstitutionId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_FI)) {
            statement.setLong(1, financialInstitutionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Branch> list = new ArrayList<>();
                while (resultSet.next()) {
                    list.add(mapRow(resultSet));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find branches by institution", e);
        }
    }

    private Branch mapRow(ResultSet resultSet) throws SQLException {
        Branch entity = new Branch();
        entity.setId(resultSet.getLong("id"));
        entity.setFinancialInstitutionId(resultSet.getLong("financial_institution_id"));
        entity.setName(resultSet.getString("name"));
        entity.setAddress(resultSet.getString("address"));
        return entity;
    }
}
