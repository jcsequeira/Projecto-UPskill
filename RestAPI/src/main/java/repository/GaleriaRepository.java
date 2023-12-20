package repository;

import model.Galeria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Repository class for managing Galeria entities in the database.
 */
public class GaleriaRepository {

    /**
     * The database connection.
     */
    private final Connection con;

    /**
     * SQL query to select all Galeria records.
     */
    private static final String SELECT_ALL_GALERIA_QUERY = "SELECT * FROM Galeria";

    /**
     * SQL query to select a Galeria by its ID.
     */
    private static final String SELECT_GALERIA_BY_ID_QUERY = "SELECT * FROM Galeria WHERE id_Galeria = ?";

    /**
     * SQL query to insert a new Galeria record.
     */
    private static final String INSERT_GALERIA_QUERY = "INSERT INTO Galeria (Nome_Galeria, Morada, Website, Email, Telefone, id_Cidade, id_colaborador, IsArtsy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    /**
     * SQL query to update an existing Galeria record.
     */
    private static final String UPDATE_GALERIA_QUERY = "UPDATE Galeria SET Nome_Galeria = ?, Morada = ?, Website = ?, Email = ?, Telefone = ?, id_Cidade = ?, id_colaborador = ?, IsArtsy = ? WHERE id_Galeria = ?";

    /**
     * SQL query to delete a Galeria by its ID.
     */
    private static final String DELETE_GALERIA_QUERY = "DELETE FROM Galeria WHERE id_Galeria = ?";

    /**
     * Constructs a new GaleriaRepository with the given database connection.
     *
     * @param con The database connection.
     */
    public GaleriaRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of all Galeria records from the database.
     *
     * @return A list of Galeria objects.
     */
    public List<Galeria> getAllGaleria() {
        List<Galeria> galeriaList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_GALERIA_QUERY)) {

            while (resultSet.next()) {
                Galeria galeria = mapResultSetToGaleria(resultSet);
                galeriaList.add(galeria);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return galeriaList;
    }

    /**
     * Retrieves a Galeria by its ID from the database.
     *
     * @param galeriaId The ID of the Galeria to retrieve.
     * @return The Galeria object, or null if not found.
     */
    public Galeria getGaleriaById(int galeriaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_GALERIA_BY_ID_QUERY)) {

            preparedStatement.setInt(1, galeriaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToGaleria(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Adds a new Galeria record to the database.
     *
     * @param galeria The Galeria object to add.
     * @return The added Galeria object with its ID set, or null if the addition failed.
     */
    public Galeria addGaleria(Galeria galeria) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_GALERIA_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            setGaleriaPreparedStatementValues(preparedStatement, galeria);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object galeria failed, no rows affected.");
            }

            setGeneratedId(preparedStatement, galeria);

            return galeria;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing Galeria record in the database.
     *
     * @param id      The ID of the Galeria to update.
     * @param galeria The Galeria object with updated values.
     * @return The updated Galeria object, or null if the update failed.
     */
    public Galeria updateGaleria(int id, Galeria galeria) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_GALERIA_QUERY)) {

            setGaleriaPreparedStatementValues(preparedStatement, galeria);
            preparedStatement.setInt(9, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getGaleriaById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Deletes a Galeria record from the database.
     *
     * @param galeriaId The ID of the Galeria to delete.
     * @return A status message indicating the success or failure of the deletion.
     */
    public String deleteGaleria(int galeriaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_GALERIA_QUERY)) {
            preparedStatement.setInt(1, galeriaId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Galeria failed, no rows affected.";
            } else {
                return "Deleting Galeria successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Galeria failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to handle SQLException.
     *
     * @param e The SQLException.
     */
    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    /**
     * Helper method to map a ResultSet to a Galeria object.
     *
     * @param resultSet The ResultSet containing Galeria data.
     * @return The mapped Galeria object.
     * @throws SQLException If an SQL exception occurs.
     */
    private Galeria mapResultSetToGaleria(ResultSet resultSet) throws SQLException {
        Galeria galeria = new Galeria();
        galeria.setId_Galeria(resultSet.getInt("id_Galeria"));
        galeria.setNome_Galeria(resultSet.getString("Nome_Galeria"));
        galeria.setMorada(resultSet.getString("Morada"));
        galeria.setWebsite(resultSet.getString("Website"));
        galeria.setEmail(resultSet.getString("Email"));
        galeria.setTelefone(resultSet.getString("Telefone"));
        galeria.setId_Cidade(resultSet.getInt("id_Cidade"));
        galeria.setId_colaborador(resultSet.getInt("id_colaborador"));
        galeria.setIsArtsy(resultSet.getInt("IsArtsy"));
        return galeria;
    }

    /**
     * Helper method to set PreparedStatement values for a Galeria.
     *
     * @param preparedStatement The PreparedStatement to set values for.
     * @param galeria           The Galeria object containing values.
     * @throws SQLException If an SQL exception occurs.
     */
    private void setGaleriaPreparedStatementValues(PreparedStatement preparedStatement, Galeria galeria) throws SQLException {
        preparedStatement.setString(1, galeria.getNome_Galeria());
        preparedStatement.setString(2, galeria.getMorada());
        preparedStatement.setString(3, galeria.getWebsite());
        preparedStatement.setString(4, galeria.getEmail());
        preparedStatement.setString(5, galeria.getTelefone());
        preparedStatement.setInt(6, galeria.getId_Cidade());
        preparedStatement.setInt(7, galeria.getId_colaborador());
        preparedStatement.setInt(8, galeria.getIsArtsy());
    }

    /**
     * Helper method to set the generated ID for a Galeria.
     *
     * @param preparedStatement The PreparedStatement with generated keys.
     * @param galeria           The Galeria object to set the ID for.
     * @throws SQLException If an SQL exception occurs.
     */
    private void setGeneratedId(PreparedStatement preparedStatement, Galeria galeria) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                galeria.setId_Galeria(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating object galeria failed, no ID obtained.");
            }
        }
    }
}
