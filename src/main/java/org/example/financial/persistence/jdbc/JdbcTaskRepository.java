package org.example.financial.persistence.jdbc;

import org.example.financial.model.Task;
import org.example.financial.persistence.TaskRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTaskRepository extends JdbcSupport implements TaskRepository {

    private static final String INSERT = """
            INSERT INTO tasks (employee_id, title, due_date, completed)
            VALUES (?, ?, ?, ?)
            """;
    private static final String UPDATE = """
            UPDATE tasks SET employee_id = ?, title = ?, due_date = ?, completed = ? WHERE id = ?
            """;
    private static final String DELETE = "DELETE FROM tasks WHERE id = ?";

    @Override
    public Task create(Task entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, entity.getEmployeeId());
            statement.setString(2, entity.getTitle());
            statement.setDate(3, JdbcUtils.toDate(entity.getDueDate()));
            statement.setBoolean(4, entity.isCompleted());
            statement.executeUpdate();
            entity.setId(extractGeneratedKey(statement));
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create task", e);
        }
    }

    @Override
    public void update(Task entity) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setLong(1, entity.getEmployeeId());
            statement.setString(2, entity.getTitle());
            statement.setDate(3, JdbcUtils.toDate(entity.getDueDate()));
            statement.setBoolean(4, entity.isCompleted());
            statement.setLong(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update task", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete task", e);
        }
    }
}
