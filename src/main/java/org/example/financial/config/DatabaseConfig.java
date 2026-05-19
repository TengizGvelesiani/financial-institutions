package org.example.financial.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class DatabaseConfig {

    private static final String CONFIG_FILE = "config.properties";
    private static final HikariDataSource dataSource;

    static {
        Properties properties = loadProperties();
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("jdbc.driver"));
        config.setJdbcUrl(properties.getProperty("jdbc.url"));
        config.setUsername(properties.getProperty("jdbc.username"));
        config.setPassword(properties.getProperty("jdbc.password"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("pool.maximumPoolSize", "10")));
        config.setMinimumIdle(Integer.parseInt(properties.getProperty("pool.minimumIdle", "2")));
        config.setConnectionTimeout(Long.parseLong(properties.getProperty("pool.connectionTimeout", "30000")));
        dataSource = new HikariDataSource(config);
    }

    private DatabaseConfig() {
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Missing " + CONFIG_FILE + " on classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load " + CONFIG_FILE, e);
        }
        return properties;
    }
}
