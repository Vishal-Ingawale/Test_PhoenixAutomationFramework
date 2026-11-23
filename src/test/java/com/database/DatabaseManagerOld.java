package com.database;

import com.api.utils.ConfigManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManagerOld {
    private static final String DB_URL = ConfigManager.getProperty("DB_URL");
    private static final String DB_USERNAME = ConfigManager.getProperty("DB_USER_NAME");
    private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
    private volatile static Connection conn; //Any update that happens to this conn variable

    //All threads will be aware of it!
    private DatabaseManagerOld() {

    }

    public static void createConnection() throws SQLException {
        if (conn == null) { //First check which all the parallel threads will enter
            synchronized (DatabaseManagerOld.class) {
                if (conn == null) { //Only and only for the first connection request
                    conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                    System.out.println(conn);
                }
            }
        }
    }
}
