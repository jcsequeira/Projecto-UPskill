package repository;

import model.Galerista;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
/**
 * Repository class for managing Galerista entities in the database.
 */
public class GaleristaRepository {

    /**
     * The database connection.
     */
    private final Connection con;

    /**
     * The date formatter for converting LocalDate to String.
     */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * SQL query to select all Galerista records.
     */
    private static final String SELECT_ALL_GALERISTA_QUERY = "SELECT * FROM galerista";

    /**
     * SQL query to select a Galerista by its ID.
     */
    private static final String SELECT_GALERISTA_BY_ID_QUERY = "SELECT * FROM galerista WHERE id_colaborador = ?";

    /**
     * SQL query to insert a new Galerista record.
     */
    private static final String INSERT_GALERISTA_QUERY = "INSERT INTO galerista (Data_Inicio_Atividade, password, id_colaborador) VALUES (?, ?, ?)";

    /**
     * SQL query to update an existing Galerista record.
     */
    private static final String UPDATE_GALERISTA_QUERY = "UPDATE galerista SET Data_Inicio_Atividade = ?, password = ? WHERE id_colaborador = ?";

    /**
     * SQL query to delete a Galerista by its ID.
     */
    private static final String DELETE_GALERISTA_QUERY = "DELETE FROM galerista WHERE id_colaborador = ?";

    /**
     * Constructs a new GaleristaRepository with the given database connection.
     *
     * @param con The database connection.
     */
    public GaleristaRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of all Galerista records from the database.
     *
     * @return A list of Galerista objects.
     */
    public List<Galerista> getAllGaleristas() {
        List<Galerista> galeristasList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_GALERISTA_QUERY)) {

            while (resultSet.next()) {
                Galerista galerista = mapResultSetToGalerista(resultSet);
                galeristasList.add(galerista);
            }

        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return galeristasList;
    }

    /**
     * Retrieves a Galerista by its ID from the database.
     *
     * @param galeristaId The ID of the Galerista to retrieve.
     * @return The Galerista object, or null if not found.
     */
    public Galerista getGaleristaById(int galeristaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_GALERISTA_BY_ID_QUERY)) {

            preparedStatement.setInt(1, galeristaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToGalerista(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Adds a new Galerista record to the database.
     *
     * @param newGalerista The Galerista object to add.
     * @return The added Galerista object, or null if the addition failed.
     */
    public Galerista addGalerista(Galerista newGalerista) {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_GALERISTA_QUERY)) {

            setGaleristaPreparedStatementValues(preparedStatement, newGalerista);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Galerista failed, no rows affected.");
            }

            return newGalerista;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing Galerista record in the database.
     *
     * @param id              The ID of the Galerista to update.
     * @param updatedGalerista The Galerista object with updated values.
     * @return The updated Galerista object, or null if the update failed.
     */
    public Galerista updateGalerista(int id, Galerista updatedGalerista) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_GALERISTA_QUERY)) {

            setGaleristaPreparedStatementValues(preparedStatement, updatedGalerista);
            preparedStatement.setInt(3, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getGaleristaById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Deletes a Galerista record from the database.
     *
     * @param galeristaId The ID of the Galerista to delete.
     * @return A status message indicating the success or failure of the deletion.
     */
    public String deleteGalerista(int galeristaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_GALERISTA_QUERY)) {

            preparedStatement.setInt(1, galeristaId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Galerista failed, no rows affected.";
            } else {
                return "Deleting Galerista successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Galerista failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to handle SQLException.
     *
     * @param e The SQLException to handle.
     */
    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    /**
     * Helper method to map a ResultSet to a Galerista object.
     *
     * @param resultSet The ResultSet containing Galerista data.
     * @return The mapped Galerista object.
     * @throws SQLException If an SQL exception occurs.
     */
    private Galerista mapResultSetToGalerista(ResultSet resultSet) throws SQLException {
        Galerista galerista = new Galerista();
        if (resultSet.getDate("Data_Inicio_Atividade") != null) {
            galerista.setData_Inicio_Atividade(resultSet.getDate("Data_Inicio_Atividade").toLocalDate());
        } else {
            galerista.setData_Inicio_Atividade(null);
        }
        galerista.setPassword(resultSet.getString("password"));
        galerista.setId_colaborador(resultSet.getInt("id_colaborador"));
        return galerista;
    }

    /**
     * Helper method to set PreparedStatement values for a Galerista.
     *
     * @param preparedStatement The PreparedStatement to set values for.
     * @param galerista         The Galerista object containing values.
     * @throws SQLException If an SQL exception occurs.
     */
    private void setGaleristaPreparedStatementValues(PreparedStatement preparedStatement, Galerista galerista) throws SQLException {
        if (galerista.getData_Inicio_Atividade() != null) {
            preparedStatement.setString(1, galerista.getData_Inicio_Atividade().format(formatter));
        } else {
            preparedStatement.setNull(1, java.sql.Types.DATE);
        }
        preparedStatement.setString(2, galerista.getPassword());
        preparedStatement.setInt(3, galerista.getId_colaborador());
    }
}
