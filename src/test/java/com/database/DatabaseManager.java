package com.database;

import com.api.utils.ConfigManager;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = ConfigManager.getProperty("DB_URL");
    private static final String DB_USERNAME = ConfigManager.getProperty("DB_USER_NAME");
    private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
    private static HikariConfig hikariConfig;
    private volatile static HikariDataSource hikariDataSource;
    private static Connection conn; //Any update that happens to this conn variable

    //All threads will be aware of it!
    private DatabaseManager() {

    }

    private static void initializePool() {
        if (conn == null) { //First check which all the parallel threads will enter
            synchronized (DatabaseManager.class) {
                HikariConfig hikariConfig = new HikariConfig();
                hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
                hikariConfig.setUsername(ConfigManager.getProperty("DB_USER_NAME"));
                hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
                hikariConfig.setMaximumPoolSize(Integer.parseInt(ConfigManager.getProperty("MAXIMUM_POOL_SIZE")));
                hikariConfig.setMinimumIdle(Integer.parseInt(ConfigManager.getProperty("MINIMUM_IDLE_COUNT")));
                hikariConfig.setConnectionTimeout(Integer.parseInt(ConfigManager.getProperty("CONNECTION_TIMEOUT_IN_SEC")) * 1000); // 10 sec
                hikariConfig.setIdleTimeout(Integer.parseInt(ConfigManager.getProperty("IDLE_TIMEOUT_IN_SEC")) * 1000);
                hikariConfig.setMaxLifetime(Integer.parseInt(ConfigManager.getProperty("MAX_LIFE_TIME_IN_MIN")) * 60 * 1000); // 30 min
                hikariConfig.setPoolName("HIKARICP_POOL_NAME");

                hikariDataSource = new HikariDataSource(hikariConfig);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        if (hikariDataSource == null) {
            initializePool();
        } else if (hikariDataSource.isClosed()) {
            throw new SQLException("Hikari Data Source is Closed");
        }
        connection = hikariDataSource.getConnection();
        return connection;
    }
}
