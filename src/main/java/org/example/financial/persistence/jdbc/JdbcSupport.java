package org.example.financial.persistence.jdbc;

import org.example.financial.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcSupport {

    protected Connection getConnection() throws SQLException {
        return DatabaseConfig.getDataSource().getConnection();
    }

    protected long extractGeneratedKey(PreparedStatement statement) throws SQLException {
        try (ResultSet keys = statement.getGeneratedKeys()) {
            if (keys.next()) {
                return keys.getLong(1);
            }
        }
        throw new SQLException("No generated key returned");
    }
}
