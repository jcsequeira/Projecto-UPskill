package repository;


import model.Cidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeRepository {

    private Connection con;

    public CidadeRepository(Connection con) {
        this.con = con;
    }

    private static final String SELECT_ALL_CIDADE_QUERY = "SELECT * FROM Cidade";
    private static final String SELECT_CIDADE_BY_ID_QUERY = "SELECT * FROM Cidade WHERE id_Cidade = ?";
    private static final String INSERT_CIDADE_QUERY = "INSERT INTO Cidade (Nome_Cidade) VALUES (?)";
    private static final String UPDATE_CIDADE_QUERY = "UPDATE Cidade SET Nome_Cidade = ? WHERE id_Cidade = ?";
    private static final String DELETE_CIDADE_QUERY = "DELETE FROM Cidade WHERE id_Cidade = ?";

    public List<Cidade> getAllCidade() {
        List<Cidade> cidadeList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_CIDADE_QUERY)) {

            while (resultSet.next()) {
                Cidade cidade = mapResultSetToCidade(resultSet);
                cidadeList.add(cidade);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return cidadeList;
    }

    public Cidade getCidadeById(int cidadeId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_CIDADE_BY_ID_QUERY)) {

            preparedStatement.setInt(1, cidadeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToCidade(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public Cidade addCidade(Cidade cidade) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_CIDADE_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, cidade.getNome_Cidade());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Cidade failed, no rows affected.");
            }

            setGeneratedId(preparedStatement, cidade);

            return cidade;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public Cidade updateCidade(int id, Cidade cidade) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_CIDADE_QUERY)) {

            preparedStatement.setString(1, cidade.getNome_Cidade());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getCidadeById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public String deleteCidade(int id) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_CIDADE_QUERY)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting Cidade failed, no rows affected.");
            } else {
                return "Deleting Cidade successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Cidade failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace(); // Log or handle the exception appropriately
    }

    private Cidade mapResultSetToCidade(ResultSet resultSet) throws SQLException {
        Cidade cidade = new Cidade();
        cidade.setId_Cidade(resultSet.getInt("id_Cidade"));
        cidade.setNome_Cidade(resultSet.getString("Nome_Cidade"));
        return cidade;
    }

    private void setGeneratedId(PreparedStatement preparedStatement, Cidade cidade) throws SQLException {
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                cidade.setId_Cidade(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating object Cidade failed, no ID obtained.");
            }
        }
    }
}
