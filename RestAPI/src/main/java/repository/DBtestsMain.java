package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBtestsMain {

    public static void main(String[] args) {
        // Initialize the connection
        Connection connection = DBConnection.getConnection();

        // Perform database operations
        try {
            // Example: Execute a query
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM artista")) {
                while (resultSet.next()) {
                    String columnName = resultSet.getString("nome_artista");
                    System.out.println(columnName);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            // Close the connection when done
            DBConnection.closeConnection();
        }
    }
}
