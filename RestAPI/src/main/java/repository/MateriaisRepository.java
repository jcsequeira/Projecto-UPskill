package repository;

import model.Materiais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for managing Materiais entities in the database.
 */
public class MateriaisRepository {

    /**
     * The database connection.
     */
    private final Connection con;

    /**
     * Constructs a new MateriaisRepository with the given database connection.
     *
     * @param con The database connection.
     */
    public MateriaisRepository(Connection con) {
        this.con = con;
    }

    /**
     * SQL query to select all Materiais records.
     */
    private static final String SELECT_ALL_MATERIAIS_QUERY = "SELECT * FROM materiais";

    /**
     * SQL query to select a Materiais by its ID.
     */
    private static final String SELECT_MATERIAL_BY_ID_QUERY = "SELECT * FROM Materiais WHERE id_Material = ?";

    /**
     * SQL query to insert a new Materiais record.
     */
    private static final String INSERT_MATERIAL_QUERY = "INSERT INTO materiais (Tipo_Material) VALUES (?)";

    /**
     * SQL query to update an existing Materiais record.
     */
    private static final String UPDATE_MATERIAL_QUERY = "UPDATE materiais SET Tipo_Material = ? WHERE id_Material = ?";

    /**
     * SQL query to delete a Materiais by its ID.
     */
    private static final String DELETE_MATERIAL_QUERY = "DELETE FROM materiais WHERE id_Material = ?";

    /**
     * Retrieves a list of all Materiais records from the database.
     *
     * @return A list of Materiais objects.
     */
    public List<Materiais> getAllMateriais() {
        List<Materiais> materiaisList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_MATERIAIS_QUERY)) {

            while (resultSet.next()) {
                Materiais materiais = mapResultSetToMateriais(resultSet);
                materiaisList.add(materiais);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return materiaisList;
    }

    /**
     * Retrieves a Materiais by its ID from the database.
     *
     * @param materialId The ID of the Materiais to retrieve.
     * @return The Materiais object, or null if not found.
     */
    public Materiais getMaterialById(int materialId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_MATERIAL_BY_ID_QUERY)) {

            preparedStatement.setInt(1, materialId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMateriais(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Adds a new Materiais record to the database.
     *
     * @param material The Materiais object to add.
     * @return The added Materiais object, or null if the addition failed.
     */
    public Materiais addMaterial(Materiais material) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_MATERIAL_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, material.getTipo_Material());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Materiais failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    material.setId_Material(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Materiais failed, no ID obtained.");
                }
            }

            return material;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing Materiais record in the database.
     *
     * @param id       The ID of the Materiais to update.
     * @param material The Materiais object with updated values.
     * @return The updated Materiais object, or null if the update failed.
     */
    public Materiais updateMaterial(int id, Materiais material) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_MATERIAL_QUERY)) {

            preparedStatement.setString(1, material.getTipo_Material());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getMaterialById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Deletes a Materiais record from the database.
     *
     * @param materialId The ID of the Materiais to delete.
     * @return A status message indicating the success or failure of the deletion.
     */
    public String deleteMaterial(int materialId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_MATERIAL_QUERY)) {

            preparedStatement.setInt(1, materialId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Material failed, no rows affected.";
            } else {
                return "Deleting Material successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Material failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to map a ResultSet to a Materiais object.
     *
     * @param resultSet The ResultSet containing Materiais data.
     * @return The mapped Materiais object.
     * @throws SQLException If an SQL exception occurs.
     */
    private Materiais mapResultSetToMateriais(ResultSet resultSet) throws SQLException {
        Materiais materiais = new Materiais();
        materiais.setId_Material(resultSet.getInt("id_Material"));
        materiais.setTipo_Material(resultSet.getString("Tipo_Material"));
        return materiais;
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
