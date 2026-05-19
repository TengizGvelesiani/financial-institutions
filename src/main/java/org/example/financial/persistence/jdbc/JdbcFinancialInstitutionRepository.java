package org.example.financial.persistence.jdbc;

import org.example.financial.model.FinancialInstitution;
import org.example.financial.persistence.FinancialInstitutionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcFinancialInstitutionRepository extends JdbcSupport implements FinancialInstitutionRepository {

    private static final String INSERT = """
            INSERT INTO financial_institutions (name, license_number, active, established_date, created_at)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE financial_institutions
            SET name = ?, license_number = ?, active = ?, established_date = ?, created_at = ?
            WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM financial_institutions WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM financial_institutions WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM financial_institutions";

    @Override
    public FinancialInstitution create(FinancialInstitution entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getLicenseNumber());
            statement.setBoolean(3, entity.isActive());
            statement.setDate(4, JdbcUtils.toDate(entity.getEstablishedDate()));
            statement.setTimestamp(5, JdbcUtils.toTimestamp(entity.getCreatedAt()));
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create financial institution", e);
        }
    }

    @Override
    public void update(FinancialInstitution entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getLicenseNumber());
            statement.setBoolean(3, entity.isActive());
            statement.setDate(4, JdbcUtils.toDate(entity.getEstablishedDate()));
            statement.setTimestamp(5, JdbcUtils.toTimestamp(entity.getCreatedAt()));
            statement.setLong(6, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update financial institution", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete financial institution", e);
        }
    }

    @Override
    public Optional<FinancialInstitution> findById(Long id) {
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
            throw new RuntimeException("Failed to find financial institution", e);
        }
    }

    @Override
    public List<FinancialInstitution> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<FinancialInstitution> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list financial institutions", e);
        }
    }

    private FinancialInstitution mapRow(ResultSet resultSet) throws SQLException {
        FinancialInstitution entity = new FinancialInstitution();
        entity.setId(resultSet.getLong("id"));
        entity.setName(resultSet.getString("name"));
        entity.setLicenseNumber(resultSet.getBigDecimal("license_number"));
        entity.setActive(JdbcUtils.getBoolean(resultSet, "active"));
        entity.setEstablishedDate(JdbcUtils.getLocalDate(resultSet, "established_date"));
        entity.setCreatedAt(JdbcUtils.getLocalDateTime(resultSet, "created_at"));
        return entity;
    }
}
