package repository;

import model.Movimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentoRepository {

    private final Connection con;

    private static final String SELECT_ALL_MOVIMENTOS_QUERY = "SELECT * FROM movimento";
    private static final String SELECT_MOVIMENTO_BY_ID_QUERY = "SELECT * FROM Movimento WHERE Id_Estilo = ?";
    private static final String INSERT_MOVIMENTO_QUERY = "INSERT INTO Movimento (Nome_Movimento) VALUES (?)";
    private static final String UPDATE_MOVIMENTO_QUERY = "UPDATE movimento SET Nome_Movimento = ? WHERE Id_Estilo = ?";
    private static final String DELETE_MOVIMENTO_QUERY = "DELETE FROM movimento WHERE Id_Estilo = ?";

    public MovimentoRepository(Connection con) {
        this.con = con;
    }

    public List<Movimento> getAllMovimentos() {
        List<Movimento> movimentosList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_MOVIMENTOS_QUERY)) {

            while (resultSet.next()) {
                Movimento movimento = mapResultSetToMovimento(resultSet);
                movimentosList.add(movimento);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return movimentosList;
    }

    public Movimento getMovimentoById(int movimentoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_MOVIMENTO_BY_ID_QUERY)) {
            preparedStatement.setInt(1, movimentoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMovimento(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public Movimento addMovimento(Movimento movimento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_MOVIMENTO_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, movimento.getNome_Movimento());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Movimento failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    movimento.setId_Estilo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Movimento failed, no ID obtained.");
                }
            }

            return movimento;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public Movimento updateMovimento(int id, Movimento movimento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_MOVIMENTO_QUERY)) {

            preparedStatement.setString(1, movimento.getNome_Movimento());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getMovimentoById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public String deleteMovimento(int movimentoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_MOVIMENTO_QUERY)) {

            preparedStatement.setInt(1, movimentoId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Movimento failed, no rows affected.";
            } else {
                return "Deleting Movimento successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Movimento failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Movimento mapResultSetToMovimento(ResultSet resultSet) throws SQLException {
        Movimento movimento = new Movimento();
        movimento.setId_Estilo(resultSet.getInt("Id_Estilo"));
        movimento.setNome_Movimento(resultSet.getString("Nome_Movimento"));
        return movimento;
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}
