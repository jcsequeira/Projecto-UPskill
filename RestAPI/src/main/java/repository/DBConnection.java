package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code DBConnection} class is responsible for managing the database connection.
 * It uses the singleton pattern to ensure a single instance of the database connection
 * throughout the application.
 */
public class DBConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/explorart";
    private static final String USER = "rest_api";
    private static final String PASSWORD = "P@ssw0rd";

    // Singleton instance of the Connection
    private static final Connection connection;

    // Static block to initialize the Connection
    static {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            // Optional: Uncomment the line below for debugging connection success
            // System.out.println("Connection Success!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection.");
        }
    }

    /**
     * Retrieves the singleton instance of the database connection.
     *
     * @return The database connection.
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
