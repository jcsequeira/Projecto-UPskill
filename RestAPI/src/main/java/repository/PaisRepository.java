package repository;

import model.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisRepository {

    private final Connection con;

    public PaisRepository(Connection con) {
        this.con = con;
    }

    private static final String SELECT_ALL_PAIS_QUERY = "SELECT * FROM Pais";
    private static final String SELECT_PAIS_BY_ID_QUERY = "SELECT * FROM Pais WHERE Codigo_Pais = ?";
    private static final String INSERT_PAIS_QUERY = "INSERT INTO Pais (Nome_Pais, Nacionalidade) VALUES (?, ?)";
    private static final String UPDATE_PAIS_QUERY = "UPDATE Pais SET Nome_Pais = ?, Nacionalidade = ? WHERE Codigo_Pais = ?";
    private static final String DELETE_PAIS_QUERY = "DELETE FROM Pais WHERE Codigo_Pais = ?";

    public List<Pais> getAllPais() {
        List<Pais> paisList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_PAIS_QUERY)) {
            while (resultSet.next()) {
                Pais pais = mapResultSetToPais(resultSet);
                paisList.add(pais);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return paisList;
    }

    public Pais getPaisById(int paisId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_PAIS_BY_ID_QUERY)) {
            preparedStatement.setInt(1, paisId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToPais(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public Pais addPais(Pais pais) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_PAIS_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            setPaisPreparedStatementValues(preparedStatement, pais);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object pais failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    pais.setCodigo_Pais(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object pais failed, no ID obtained.");
                }
            }

            return pais;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public Pais updatePais(int id, Pais pais) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_PAIS_QUERY)) {

            setPaisPreparedStatementValues(preparedStatement, pais);
            preparedStatement.setInt(3, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getPaisById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public void deletePais(int id) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_PAIS_QUERY)) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting pais failed, no rows affected.");
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void setPaisPreparedStatementValues(PreparedStatement preparedStatement, Pais pais)
            throws SQLException {
        preparedStatement.setString(1, pais.getNome_Pais());
        preparedStatement.setString(2, pais.getNacionalidade());
    }

    private Pais mapResultSetToPais(ResultSet resultSet) throws SQLException {
        Pais pais = new Pais();
        pais.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
        pais.setNome_Pais(resultSet.getString("Nome_Pais"));
        pais.setNacionalidade(resultSet.getString("Nacionalidade"));
        return pais;
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}
