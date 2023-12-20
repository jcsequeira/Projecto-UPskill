package repository;

import model.Colaborador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ColaboradorRepository} class provides data access methods for the {@code Colaborador} entity.
 */
public class ColaboradorRepository {

    private final Connection con;

    /**
     * Constructs a {@code ColaboradorRepository} with the specified database connection.
     *
     * @param con The database connection.
     */
    public ColaboradorRepository(Connection con) {
        this.con = con;
    }

    private static final String SELECT_ALL_COLABORADORES_QUERY = "SELECT * FROM Colaborador";
    private static final String SELECT_COLABORADOR_BY_ID_QUERY = "SELECT * FROM Colaborador WHERE id_colaborador = ?";
    private static final String INSERT_COLABORADOR_QUERY = "INSERT INTO Colaborador (Nome_Colaborador, Email, Telefone, Codigo_Pais) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_COLABORADOR_QUERY = "UPDATE Colaborador SET Nome_Colaborador = ?, Email = ?, Telefone = ?, Codigo_Pais = ? WHERE id_colaborador = ?";
    private static final String DELETE_COLABORADOR_QUERY = "DELETE FROM Colaborador WHERE id_colaborador = ?";

    /**
     * Checks if a Colaborador entity with the specified ID exists in the database.
     *
     * @param idColaborador The ID of the Colaborador entity.
     * @return {@code true} if the Colaborador entity exists; otherwise, {@code false}.
     * @throws SQLException If an SQL exception occurs during the database query.
     */
    public boolean existsColaborador(int idColaborador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT COUNT(*) FROM Colaborador WHERE id_colaborador = ?")) {

            preparedStatement.setInt(1, idColaborador);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return false;
    }

    /**
     * Retrieves a list of all Colaborador entities from the database.
     *
     * @return A list of all Colaborador entities.
     */
    public List<Colaborador> getAllColaboradores() {
        List<Colaborador> colaboradorList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_COLABORADORES_QUERY)) {

            while (resultSet.next()) {
                Colaborador colaborador = mapResultSetToColaborador(resultSet);
                colaboradorList.add(colaborador);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return colaboradorList;
    }

    /**
     * Retrieves a Colaborador entity by its ID from the database.
     *
     * @param colaboradorId The ID of the Colaborador entity to retrieve.
     * @return The Colaborador entity with the specified ID, or {@code null} if not found.
     */
    public Colaborador getColaboradorById(int colaboradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_COLABORADOR_BY_ID_QUERY)) {

            preparedStatement.setInt(1, colaboradorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToColaborador(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    /**
     * Adds a new Colaborador entity to the database.
     *
     * @param colaborador The Colaborador entity to add.
     * @return The added Colaborador entity, or {@code null} if the operation fails.
     */
    public Colaborador addColaborador(Colaborador colaborador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_COLABORADOR_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            setColaboradorPreparedStatementValues(preparedStatement, colaborador);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Colaborador failed, no rows affected.");
            }

            setGeneratedId(preparedStatement, colaborador);

            return colaborador;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing Colaborador entity in the database.
     *
     * @param colaboradorId The ID of the Colaborador entity to update.
     * @param colaborador   The updated Colaborador entity.
     * @return The updated Colaborador entity, or {@code null} if the operation fails.
     */
    public Colaborador updateColaborador(int colaboradorId, Colaborador colaborador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_COLABORADOR_QUERY)) {

            setColaboradorPreparedStatementValues(preparedStatement, colaborador);
            preparedStatement.setInt(5, colaboradorId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating object Colaborador failed, no rows affected.");
            }

            return colaborador;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Deletes a Colaborador entity from the database.
     *
     * @param colaboradorId The ID of the Colaborador entity to delete.
     * @return A status message indicating the result of the deletion operation.
     * @throws SQLException If an SQL exception occurs during the deletion operation.
     */
    public String deleteColaborador(int colaboradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_COLABORADOR_QUERY)) {
            preparedStatement.setInt(1, colaboradorId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting Colaborador failed, no rows affected.");
            } else {
                return "Deleting Colaborador successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Colaborador failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    /**
     * Maps a ResultSet to a Colaborador entity.
     *
     * @param resultSet The ResultSet containing the Colaborador data.
     * @return A Colaborador entity mapped from the ResultSet.
     * @throws SQLException If an SQL exception occurs during ResultSet processing.
     */
    private Colaborador mapResultSetToColaborador(ResultSet resultSet) throws SQLException {
        Colaborador colaborador = new Colaborador();
        colaborador.setId_colaborador(resultSet.getInt("id_colaborador"));
        colaborador.setNome_Colaborador(resultSet.getString("Nome_Colaborador"));
        colaborador.setEmail(resultSet.getString("Email"));
        colaborador.setTelefone(resultSet.getString("Telefone"));
        colaborador.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
        return colaborador;
    }

    /**
     * Sets the values of a Colaborador entity in a PreparedStatement for database operations.
     *
     * @param preparedStatement The PreparedStatement to set values for.
     * @param colaborador       The Colaborador entity whose values are to be set.
     * @throws SQLException If an SQL exception occurs during PreparedStatement setting.
     */
    private void setColaboradorPreparedStatementValues(PreparedStatement preparedStatement, Colaborador colaborador) throws SQLException {
        preparedStatement.setString(1, colaborador.getNome_Colaborador());
        preparedStatement.setString(2, colaborador.getEmail());
        preparedStatement.setString(3, colaborador.getTelefone());
        preparedStatement.setInt(4, colaborador.getCodigo_Pais());
    }

    /**
     * Sets the generated ID from the database into the Colaborador entity.
     *
     * @param preparedStatement The PreparedStatement used for the database operation.
     * @param colaborador       The Colaborador entity to set the generated ID for.
     * @throws SQLException If an SQL exception occurs during ResultSet processing.
     */
    private void setGeneratedId(PreparedStatement preparedStatement, Colaborador colaborador) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                colaborador.setId_colaborador(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating object Colaborador failed, no ID obtained.");
            }
        }
    }
}
