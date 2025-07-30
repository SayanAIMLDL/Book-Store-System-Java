package com.group7.bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // 1. The connection URL now uses 'db' as the hostname.
    private static final String URL = "jdbc:mysql://localhost:3306/bookstore_db";
    private static final String USER = "root";
    private static final String PASSWORD = "mca@9749";

    private static Connection connection = null;

    // 2. This method establishes a connection to the database.
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // 3. This line loads the MySQL driver.
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 4. This line actually connects to the database.
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("INFO: New database connection established.");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found.", e);
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("INFO: Database connection closed.");
            } catch (SQLException e) {
                System.err.println("ERROR: Failed to close database connection. " + e.getMessage());
            }
        }
    }
}