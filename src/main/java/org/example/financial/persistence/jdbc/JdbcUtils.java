package org.example.financial.persistence.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class JdbcUtils {

    private JdbcUtils() {
    }

    public static LocalDate getLocalDate(ResultSet resultSet, String column) throws SQLException {
        Date value = resultSet.getDate(column);
        return value != null ? value.toLocalDate() : null;
    }

    public static LocalDateTime getLocalDateTime(ResultSet resultSet, String column) throws SQLException {
        Timestamp value = resultSet.getTimestamp(column);
        return value != null ? value.toLocalDateTime() : null;
    }

    public static boolean getBoolean(ResultSet resultSet, String column) throws SQLException {
        return resultSet.getInt(column) == 1;
    }

    public static Date toDate(LocalDate localDate) {
        return localDate != null ? Date.valueOf(localDate) : null;
    }

    public static Timestamp toTimestamp(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }
}
