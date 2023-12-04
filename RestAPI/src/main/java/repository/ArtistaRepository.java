package repository;

import model.Artista;


import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArtistaRepository {
    private Connection con;

    public ArtistaRepository(Connection con) {
        this.con = con;
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final String SELECT_ALL_ARTIST_QUERY = "SELECT * FROM artista";
    private static final String SELECT_ARTIST_BY_ID_QUERY = "SELECT * FROM artista WHERE id_artista = ?";
    private static final String INSERT_ARTIST_QUERY =
            "INSERT INTO artista (nome_artista, Data_Nascimento, Biografia, Data_Morte, Codigo_Pais, IsArtsy) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ARTIST_QUERY =
            "UPDATE artista SET nome_artista = ?, Data_Nascimento = ?, Biografia = ?, " +
                    "Data_Morte = ?, Codigo_Pais = ?, IsArtsy = ? WHERE id_artista = ?";
    private static final String DELETE_ARTIST_QUERY = "DELETE FROM artista WHERE id_artista = ?";

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

    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    private Artista mapResultSetToArtista(ResultSet resultSet) throws SQLException {
        Artista artista = new Artista();
        artista.setId_artista(resultSet.getInt("id_artista"));
        artista.setNome_artista(resultSet.getString("nome_artista"));
        artista.setData_Nascimento(mapToLocalDate(resultSet.getString("Data_Nascimento")));
        artista.setBiografia(resultSet.getString("Biografia"));
        artista.setData_Morte(mapToLocalDate(resultSet.getString("Data_Morte")));
        artista.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
        artista.setIsArtsy(resultSet.getInt("IsArtsy"));
        return artista;
    }

    private LocalDate mapToLocalDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, formatter) : null;
    }

    private void setArtistaParameters(PreparedStatement preparedStatement, Artista artista) throws SQLException {
        preparedStatement.setString(1, artista.getNome_artista());
        preparedStatement.setObject(2, artista.getData_Nascimento(), java.sql.Types.DATE);
        preparedStatement.setString(3, artista.getBiografia());
        preparedStatement.setObject(4, artista.getData_Morte(), java.sql.Types.DATE);
        preparedStatement.setInt(5, artista.getCodigo_Pais());
        preparedStatement.setInt(6, artista.getIsArtsy());
    }

    private void setGeneratedId(PreparedStatement preparedStatement, Artista artista) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                // Set the generated ID to the obraArte object
                artista.setId_artista(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating object Artista failed, no ID obtained.");
            }
        }
    }
}
