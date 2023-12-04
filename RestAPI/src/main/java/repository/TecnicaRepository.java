package repository;

import model.Tecnica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TecnicaRepository {

    private Connection con;

    public TecnicaRepository(Connection con) {
        this.con = con;
    }

    private static final String SELECT_ALL_TECNICA_QUERY = "SELECT * FROM tecnica";
    private static final String SELECT_TECNICA_BY_ID_QUERY = "SELECT * FROM Tecnica WHERE id_Tecnica = ?";
    private static final String INSERT_TECNICA_QUERY = "INSERT INTO Tecnica (Tipo_Tecnica) VALUES (?)";
    private static final String UPDATE_TECNICA_QUERY = "UPDATE Tecnica SET Tipo_Tecnica = ? WHERE id_Tecnica = ?";
    private static final String DELETE_TECNICA_QUERY = "DELETE FROM Tecnica WHERE id_Tecnica = ?";

    public List<Tecnica> getAllTecnicas() {
        List<Tecnica> tecnicasList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_TECNICA_QUERY)) {

            while (resultSet.next()) {
                Tecnica tecnica = mapResultSetToTecnica(resultSet);
                tecnicasList.add(tecnica);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return tecnicasList;
    }

    public Tecnica getTecnicaById(int tecnicaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_TECNICA_BY_ID_QUERY)) {
            preparedStatement.setInt(1, tecnicaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToTecnica(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public Tecnica addTecnica(Tecnica tecnica) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_TECNICA_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, tecnica.getTipo_Tecnica());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Tecnica failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tecnica.setId_Tecnica(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Tecnica failed, no ID obtained.");
                }
            }

            return tecnica;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public Tecnica updateTecnica(int id, Tecnica tecnica) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_TECNICA_QUERY)) {

            preparedStatement.setString(1, tecnica.getTipo_Tecnica());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getTecnicaById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public String deleteTecnica(int tecnicaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_TECNICA_QUERY)) {
            preparedStatement.setInt(1, tecnicaId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                return "Deleting Tecnica failed, no rows affected.";
            } else {
                return "Deleting Tecnica successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Tecnica failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Tecnica mapResultSetToTecnica(ResultSet resultSet) throws SQLException {
        Tecnica tecnica = new Tecnica();
        tecnica.setId_Tecnica(resultSet.getInt("id_Tecnica"));
        tecnica.setTipo_Tecnica(resultSet.getString("Tipo_Tecnica"));
        return tecnica;
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}
