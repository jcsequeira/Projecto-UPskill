package repository;

import model.Galeria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GaleriaRepository {

    private Connection con;

    public GaleriaRepository(Connection con) {
        this.con = con;
    }

    private static final String SELECT_ALL_GALERIA_QUERY = "SELECT * FROM Galeria";
    private static final String SELECT_GALERIA_BY_ID_QUERY = "SELECT * FROM Galeria WHERE id_Galeria = ?";
    private static final String INSERT_GALERIA_QUERY = "INSERT INTO Galeria (Nome_Galeria, Morada, Website, Email, Telefone, id_Cidade, id_colaborador, IsArtsy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_GALERIA_QUERY = "UPDATE Galeria SET Nome_Galeria = ?, Morada = ?, Website = ?, Email = ?, Telefone = ?, id_Cidade = ?, id_colaborador = ?, IsArtsy = ? WHERE id_Galeria = ?";
    private static final String DELETE_GALERIA_QUERY = "DELETE FROM Galeria WHERE id_Galeria = ?";

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

    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

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
