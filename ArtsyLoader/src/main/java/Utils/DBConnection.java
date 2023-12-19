package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The {@code DBConnection} class provides a utility for managing a MySQL database connection.
 * It includes methods for obtaining a connection and closing the connection.
 *
 * <p>The class uses lazy initialization for the database connection and follows
 * Java Database Connectivity (JDBC) standards.</p>
 */
public class DBConnection {

    /** The JDBC URL for the MySQL database. */
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/explorart";

    /** The username for connecting to the database. */
    private static final String USER = "rest_api";

    /** The password for connecting to the database. */
    private static final String PASSWORD = "P@ssw0rd";

    /** The database connection instance. */
    private static Connection connection;

    static {
        try {
            // Load the MySQL JDBC driver and initialize the connection.
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection.", e);
        }
    }

    /**
     * Gets a connection to the MySQL database.
     *
     * @return A {@code Connection} object representing the database connection.
     */
    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                // Lazy initialization of the database connection.
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize database connection.", e);
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     * If the connection is already closed or null, no action is taken.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                // Close the database connection.
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
