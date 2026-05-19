package org.example.financial.persistence.jdbc;

import org.example.financial.model.Card;
import org.example.financial.persistence.CardRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcCardRepository extends JdbcSupport implements CardRepository {

    private static final String INSERT = """
            INSERT INTO cards (account_id, card_number, issued_at, blocked)
            VALUES (?, ?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE cards SET account_id = ?, card_number = ?, issued_at = ?, blocked = ? WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM cards WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM cards WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM cards";

    @Override
    public Card create(Card entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getAccountId());
            statement.setString(2, entity.getCardNumber());
            statement.setTimestamp(3, JdbcUtils.toTimestamp(entity.getIssuedAt()));
            statement.setBoolean(4, entity.isBlocked());
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create card", e);
        }
    }

    @Override
    public void update(Card entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getAccountId());
            statement.setString(2, entity.getCardNumber());
            statement.setTimestamp(3, JdbcUtils.toTimestamp(entity.getIssuedAt()));
            statement.setBoolean(4, entity.isBlocked());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update card", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete card", e);
        }
    }

    @Override
    public Optional<Card> findById(Long id) {
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
            throw new RuntimeException("Failed to find card", e);
        }
    }

    @Override
    public List<Card> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()) {
            List<Card> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(mapRow(resultSet));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to list cards", e);
        }
    }

    private Card mapRow(ResultSet resultSet) throws SQLException {
        Card entity = new Card();
        entity.setId(resultSet.getLong("id"));
        entity.setAccountId(resultSet.getLong("account_id"));
        entity.setCardNumber(resultSet.getString("card_number"));
        entity.setIssuedAt(JdbcUtils.getLocalDateTime(resultSet, "issued_at"));
        entity.setBlocked(JdbcUtils.getBoolean(resultSet, "blocked"));
        return entity;
    }
}
