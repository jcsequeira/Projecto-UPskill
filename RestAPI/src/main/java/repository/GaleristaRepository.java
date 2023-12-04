package repository;

import model.Galerista;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GaleristaRepository {

    private Connection con;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public GaleristaRepository(Connection con) {
        this.con = con;
    }

    private static final String SELECT_ALL_GALERISTA_QUERY = "SELECT * FROM galerista";
    private static final String SELECT_GALERISTA_BY_ID_QUERY = "SELECT * FROM galerista WHERE id_colaborador = ?";
    private static final String INSERT_GALERISTA_QUERY = "INSERT INTO galerista (Data_Inicio_Atividade, password, id_colaborador) VALUES (?, ?, ?)";
    private static final String UPDATE_GALERISTA_QUERY = "UPDATE galerista SET Data_Inicio_Atividade = ?, password = ? WHERE id_colaborador = ?";
    private static final String DELETE_GALERISTA_QUERY = "DELETE FROM galerista WHERE id_colaborador = ?";

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

    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

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
