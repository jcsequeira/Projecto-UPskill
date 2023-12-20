package repository;

import model.Administrador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AdministradorRepository} class provides data access methods for the {@code Administrador} entity.
 */
public class AdministradorRepository {
    private final Connection con;

    private static final String SELECT_ALL_ADMINISTRADOR_QUERY = "SELECT * FROM Administrador";
    private static final String SELECT_ADMINISTRADOR_BY_ID_QUERY = "SELECT * FROM Administrador WHERE id_colaborador = ?";
    private static final String INSERT_ADMINISTRADOR_QUERY = "INSERT INTO administrador (password, id_colaborador) VALUES (?, ?)";
    private static final String UPDATE_ADMINISTRADOR_QUERY = "UPDATE Administrador SET password = ? WHERE id_colaborador = ?";
    private static final String DELETE_ADMINISTRADOR_QUERY = "DELETE FROM Administrador WHERE id_colaborador = ?";

    /**
     * Constructs an {@code AdministradorRepository} with the specified database connection.
     *
     * @param con The database connection.
     */
    public AdministradorRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of all Administrador entities from the database.
     *
     * @return A list of all Administrador entities.
     */
    public List<Administrador> getAllAdministrador() {
        List<Administrador> administradoresList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ADMINISTRADOR_QUERY)) {

            while (resultSet.next()) {
                Administrador administrador = mapResultSetToAdministrador(resultSet);
                administradoresList.add(administrador);
            }

        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return administradoresList;
    }

    /**
     * Retrieves an Administrador entity by its ID from the database.
     *
     * @param administradorId The ID of the Administrador entity to retrieve.
     * @return The Administrador entity with the specified ID, or {@code null} if not found.
     */
    public Administrador getAdministradorById(int administradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_ADMINISTRADOR_BY_ID_QUERY)) {

            preparedStatement.setInt(1, administradorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToAdministrador(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Adds a new Administrador entity to the database.
     *
     * @param newAdministrador The Administrador entity to add.
     * @return The added Administrador entity, or {@code null} if the operation fails.
     */
    public Administrador addAdministrador(Administrador newAdministrador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_ADMINISTRADOR_QUERY)) {

            preparedStatement.setString(1, newAdministrador.getPassword());
            preparedStatement.setInt(2, newAdministrador.getId_colaborador());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Administrador failed, no rows affected.");
            }

            return newAdministrador;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing Administrador entity in the database.
     *
     * @param id               The ID of the Administrador entity to update.
     * @param updatedAdministrador The updated Administrador entity.
     * @return The updated Administrador entity, or {@code null} if the operation fails.
     */
    public Administrador updateAdministrador(int id, Administrador updatedAdministrador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ADMINISTRADOR_QUERY)) {

            preparedStatement.setString(1, updatedAdministrador.getPassword());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getAdministradorById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Deletes an Administrador entity from the database.
     *
     * @param administradorId The ID of the Administrador entity to delete.
     * @return A message indicating the result of the deletion operation.
     */
    public String deleteAdministrador(int administradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_ADMINISTRADOR_QUERY)) {

            preparedStatement.setInt(1, administradorId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Administrador failed, no rows affected.";
            } else {
                return "Deleting Administrador successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Administrador failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles SQL exceptions by printing the stack trace.
     *
     * @param e The SQLException to handle.
     */
    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    /**
     * Maps a ResultSet to an Administrador entity.
     *
     * @param resultSet The ResultSet containing Administrador entity data.
     * @return The mapped Administrador entity.
     * @throws SQLException If an SQL exception occurs during mapping.
     */
    private Administrador mapResultSetToAdministrador(ResultSet resultSet) throws SQLException {
        Administrador administrador = new Administrador(resultSet.getString("password"),resultSet.getInt("id_colaborador"));
        administrador.setPassword(resultSet.getString("password"));
        administrador.setId_colaborador(resultSet.getInt("id_colaborador"));
        return administrador;
    }
}
