package repository;

import model.Artista;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code ArtistaRepository} class provides data access methods for the {@code Artista} entity.
 */
public class ArtistaRepository {
    private final Connection con;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String SELECT_ALL_ARTIST_QUERY = "SELECT * FROM artista";
    private static final String SELECT_ARTIST_BY_ID_QUERY = "SELECT * FROM artista WHERE id_artista = ?";
    private static final String INSERT_ARTIST_QUERY =
            "INSERT INTO artista (nome_artista, Data_Nascimento, Biografia, Data_Morte, Nacionalidade, IsArtsy) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ARTIST_QUERY =
            "UPDATE artista SET nome_artista = ?, Data_Nascimento = ?, Biografia = ?, " +
                    "Data_Morte = ?, Nacionalidade = ?, IsArtsy = ? WHERE id_artista = ?";
    private static final String DELETE_ARTIST_QUERY = "DELETE FROM artista WHERE id_artista = ?";

    /**
     * Constructs an {@code ArtistaRepository} with the specified database connection.
     *
     * @param con The database connection.
     */
    public ArtistaRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of all Artista entities from the database.
     *
     * @return A list of all Artista entities.
     */
    public List<Artista> getAllArtistas() {
        List<Artista> artistaList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_ARTIST_QUERY)) {

            while (resultSet.next()) {
                Artista artista = mapResultSetToArtista(resultSet);
                artistaList.add(artista);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return artistaList;
    }

    /**
     * Retrieves an Artista entity by its ID from the database.
     *
     * @param artistaId The ID of the Artista entity to retrieve.
     * @return The Artista entity with the specified ID, or {@code null} if not found.
     */
    public Artista getArtistaById(int artistaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_ARTIST_BY_ID_QUERY)) {

            preparedStatement.setInt(1, artistaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToArtista(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    /**
     * Adds a new Artista entity to the database.
     *
     * @param artista The Artista entity to add.
     * @return The added Artista entity, or {@code null} if the operation fails.
     */
    public Artista addArtista(Artista artista) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_ARTIST_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            setArtistaParameters(preparedStatement, artista);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Artista failed, no rows affected.");
            }

            setGeneratedId(preparedStatement, artista);

            return artista;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing Artista entity in the database.
     *
     * @param artistaId      The ID of the Artista entity to update.
     * @param artista The updated Artista entity.
     * @return The updated Artista entity, or {@code null} if the operation fails.
     */
    public Artista updateArtista(int artistaId, Artista artista) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_ARTIST_QUERY)) {

            setArtistaParameters(preparedStatement, artista);
            preparedStatement.setInt(7, artistaId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating object Artista failed, no rows affected.");
            }

            return artista;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Deletes an Artista entity from the database.
     *
     * @param artistaId The ID of the Artista entity to delete.
     * @return A message indicating the result of the deletion operation.
     */
    public String deleteArtista(int artistaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_ARTIST_QUERY)) {
            preparedStatement.setInt(1, artistaId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Artista failed, no rows affected.";
            } else {
                return "Deleting Artista successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Artista failed, no rows affected.";
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
     * Maps a ResultSet to an Artista entity.
     *
     * @param resultSet The ResultSet containing Artista entity data.
     * @return The mapped Artista entity.
     * @throws SQLException If an SQL exception occurs during mapping.
     */
    private Artista mapResultSetToArtista(ResultSet resultSet) throws SQLException {
        Artista artista = new Artista();
        artista.setId_artista(resultSet.getInt("id_artista"));
        artista.setNome_artista(resultSet.getString("nome_artista"));
        artista.setData_Nascimento(mapToLocalDate(resultSet.getString("Data_Nascimento")));
        artista.setBiografia(resultSet.getString("Biografia"));
        artista.setData_Morte(mapToLocalDate(resultSet.getString("Data_Morte")));
        artista.setNacionalidade(resultSet.getString("Nacionalidade"));
        artista.setIsArtsy(resultSet.getInt("IsArtsy"));
        return artista;
    }

    /**
     * Maps a date string to a LocalDate object.
     *
     * @param dateString The date string to map.
     * @return The mapped LocalDate object.
     */
    private LocalDate mapToLocalDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, formatter) : null;
    }

    /**
     * Sets parameters for a PreparedStatement using data from an Artista entity.
     *
     * @param preparedStatement The PreparedStatement to set parameters for.
     * @param artista           The Artista entity providing parameter values.
     * @throws SQLException If an SQL exception occurs during parameter setting.
     */
    private void setArtistaParameters(PreparedStatement preparedStatement, Artista artista) throws SQLException {
        preparedStatement.setString(1, artista.getNome_artista());
        preparedStatement.setObject(2, artista.getData_Nascimento(), java.sql.Types.DATE);
        preparedStatement.setString(3, artista.getBiografia());
        preparedStatement.setObject(4, artista.getData_Morte(), java.sql.Types.DATE);
        preparedStatement.setString(5, artista.getNacionalidade());
        preparedStatement.setInt(6, artista.getIsArtsy());
    }

    /**
     * Sets the generated ID from a PreparedStatement to an Artista entity.
     *
     * @param preparedStatement The PreparedStatement containing the generated ID.
     * @param artista           The Artista entity to set the generated ID for.
     * @throws SQLException If an SQL exception occurs during ID retrieval.
     */
    private void setGeneratedId(PreparedStatement preparedStatement, Artista artista) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                // Set the generated ID to the artista object
                artista.setId_artista(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating object Artista failed, no ID obtained.");
            }
        }
    }
}
