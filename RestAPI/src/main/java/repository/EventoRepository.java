package repository;

import model.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EventoRepository} class is responsible for database operations related to events.
 */
public class EventoRepository {

    // Database connection
    private final Connection con;

    // SQL queries for CRUD operations
    private static final String SELECT_ALL_EVENTO_QUERY = "SELECT * FROM Evento";
    private static final String SELECT_EVENTO_BY_ID_QUERY = "SELECT * FROM Evento WHERE id_Expo = ?";
    private static final String INSERT_EVENTO_QUERY = "INSERT INTO Evento (Nome, Data_inicio, Data_Fim, Descricao, id_Galeria, IsArtsy) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_EVENTO_QUERY = "UPDATE Evento SET Nome = ?, Data_inicio = ?, Data_Fim = ?, Descricao = ?, id_Galeria = ?, IsArtsy = ? WHERE id_Expo = ?";
    private static final String DELETE_EVENTO_QUERY = "DELETE FROM Evento WHERE id_Expo = ?";

    /**
     * Creates a new instance of {@code EventoRepository} with the provided database connection.
     *
     * @param con The database connection.
     */
    public EventoRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of all events from the database.
     *
     * @return A list of events.
     */
    public List<Evento> getAllEvento() {
        List<Evento> eventoList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_EVENTO_QUERY)) {

            while (resultSet.next()) {
                Evento evento = mapResultSetToEvento(resultSet);
                eventoList.add(evento);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return eventoList;
    }

    /**
     * Retrieves an event from the database based on the provided ID.
     *
     * @param eventoId The ID of the event.
     * @return The event, or {@code null} if not found.
     */
    public Evento getEventoById(int eventoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_EVENTO_BY_ID_QUERY)) {

            preparedStatement.setInt(1, eventoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToEvento(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Adds a new event to the database.
     *
     * @param evento The event to be added.
     * @return The added event with the generated ID, or {@code null} if the operation fails.
     */
    public Evento addEvento(Evento evento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_EVENTO_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            setEventoPreparedStatementValues(preparedStatement, evento);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object evento failed, no rows affected.");
            }

            setGeneratedId(preparedStatement, evento);

            return evento;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing event in the database.
     *
     * @param id     The ID of the event to be updated.
     * @param evento The updated event data.
     * @return The updated event, or {@code null} if the operation fails.
     */
    public Evento updateEvento(int id, Evento evento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_EVENTO_QUERY)) {

            setEventoPreparedStatementValues(preparedStatement, evento);
            preparedStatement.setInt(7, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getEventoById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    /**
     * Deletes an event from the database based on the provided ID.
     *
     * @param eventoId The ID of the event to be deleted.
     * @return A message indicating the success or failure of the deletion operation.
     */
    public String deleteEvento(int eventoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_EVENTO_QUERY)) {
            preparedStatement.setInt(1, eventoId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Evento failed, no rows affected.";
            } else {
                return "Deleting Evento successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Evento failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to handle SQLException.
     *
     * @param e The SQLException to be handled.
     */
    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    /**
     * Helper method to map a ResultSet to a Colaborador object.
     *
     * @param resultSet The ResultSet containing the data to be mapped.
     * @return A Colaborador object mapped from the ResultSet.
     * @throws SQLException If an SQL exception occurs during mapping.
     */
    private Evento mapResultSetToEvento(ResultSet resultSet) throws SQLException {
        Evento evento = new Evento();
        evento.setId_Expo(resultSet.getInt("id_Expo"));
        evento.setNome(resultSet.getString("Nome"));
        evento.setData_inicio(resultSet.getDate("Data_inicio").toLocalDate());
        evento.setData_Fim(resultSet.getDate("Data_Fim").toLocalDate());
        evento.setDescricao(resultSet.getString("Descricao"));
        evento.setId_Galeria(resultSet.getInt("id_Galeria"));
        evento.setIsArtsy(resultSet.getInt("IsArtsy"));
        return evento;
    }

    /**
     * Helper method to set PreparedStatement values for an Evento.
     *
     * @param preparedStatement The PreparedStatement to set values for.
     * @param evento            The Evento object providing values.
     * @throws SQLException If an SQL exception occurs during value setting.
     */
    private void setEventoPreparedStatementValues(PreparedStatement preparedStatement, Evento evento) throws SQLException {
        preparedStatement.setString(1, evento.getNome());
        preparedStatement.setDate(2, Date.valueOf(evento.getData_inicio()));
        preparedStatement.setDate(3, Date.valueOf(evento.getData_Fim()));
        preparedStatement.setString(4, evento.getDescricao());
        preparedStatement.setInt(5, evento.getId_Galeria());
        preparedStatement.setInt(6, evento.getIsArtsy());
    }

    /**
     * Helper method to set the generated ID for an Evento.
     *
     * @param preparedStatement The PreparedStatement used for the SQL operation.
     * @param evento            The Evento object to set the generated ID.
     * @throws SQLException If an SQL exception occurs during ID setting.
     */
    private void setGeneratedId(PreparedStatement preparedStatement, Evento evento) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                evento.setId_Expo(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating object evento failed, no ID obtained.");
            }
        }
    }
}
