package repository;

import model.Movimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing Movimento entities in the database.
 */
public class MovimentoRepository {

    /**
     * The database connection.
     */
    private final Connection con;

    /**
     * SQL query to select all Movimento records.
     */
    private static final String SELECT_ALL_MOVIMENTOS_QUERY = "SELECT * FROM movimento";

    /**
     * SQL query to select a Movimento by its ID.
     */
    private static final String SELECT_MOVIMENTO_BY_ID_QUERY = "SELECT * FROM Movimento WHERE Id_Estilo = ?";

    /**
     * SQL query to insert a new Movimento record.
     */
    private static final String INSERT_MOVIMENTO_QUERY = "INSERT INTO Movimento (Nome_Movimento) VALUES (?)";

    /**
     * SQL query to update an existing Movimento record.
     */
    private static final String UPDATE_MOVIMENTO_QUERY = "UPDATE movimento SET Nome_Movimento = ? WHERE Id_Estilo = ?";

    /**
     * SQL query to delete a Movimento by its ID.
     */
    private static final String DELETE_MOVIMENTO_QUERY = "DELETE FROM movimento WHERE Id_Estilo = ?";

    /**
     * Constructs a new MovimentoRepository with the given database connection.
     *
     * @param con The database connection.
     */
    public MovimentoRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of all Movimento records from the database.
     *
     * @return A list of Movimento objects.
     */
    public List<Movimento> getAllMovimentos() {
        List<Movimento> movimentosList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_MOVIMENTOS_QUERY)) {

            while (resultSet.next()) {
                Movimento movimento = mapResultSetToMovimento(resultSet);
                movimentosList.add(movimento);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return movimentosList;
    }

    /**
     * Retrieves a Movimento by its ID from the database.
     *
     * @param movimentoId The ID of the Movimento to retrieve.
     * @return The Movimento object, or null if not found.
     */
    public Movimento getMovimentoById(int movimentoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_MOVIMENTO_BY_ID_QUERY)) {
            preparedStatement.setInt(1, movimentoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMovimento(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Adds a new Movimento record to the database.
     *
     * @param movimento The Movimento object to add.
     * @return The added Movimento object, or null if the addition failed.
     */
    public Movimento addMovimento(Movimento movimento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_MOVIMENTO_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, movimento.getNome_Movimento());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Movimento failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movimento.setId_Estilo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Movimento failed, no ID obtained.");
                }
            }

            return movimento;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing Movimento record in the database.
     *
     * @param id       The ID of the Movimento to update.
     * @param movimento The Movimento object with updated values.
     * @return The updated Movimento object, or null if the update failed.
     */
    public Movimento updateMovimento(int id, Movimento movimento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_MOVIMENTO_QUERY)) {

            preparedStatement.setString(1, movimento.getNome_Movimento());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getMovimentoById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Deletes a Movimento record from the database.
     *
     * @param movimentoId The ID of the Movimento to delete.
     * @return A status message indicating the success or failure of the deletion.
     */
    public String deleteMovimento(int movimentoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_MOVIMENTO_QUERY)) {

            preparedStatement.setInt(1, movimentoId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Movimento failed, no rows affected.";
            } else {
                return "Deleting Movimento successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Movimento failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to map a ResultSet to a Movimento object.
     *
     * @param resultSet The ResultSet containing Movimento data.
     * @return The mapped Movimento object.
     * @throws SQLException If an SQL exception occurs.
     */
    private Movimento mapResultSetToMovimento(ResultSet resultSet) throws SQLException {
        Movimento movimento = new Movimento();
        movimento.setId_Estilo(resultSet.getInt("Id_Estilo"));
        movimento.setNome_Movimento(resultSet.getString("Nome_Movimento"));
        return movimento;
    }

    /**
     * Helper method to handle SQLException.
     *
     * @param e The SQLException to handle.
     */
    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}
