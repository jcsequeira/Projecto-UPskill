package repository;

import model.Colaborador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorRepository {

    private final Connection con;

    public ColaboradorRepository(Connection con) {
        this.con = con;
    }

    private static final String SELECT_ALL_COLABORADORES_QUERY = "SELECT * FROM Colaborador";
    private static final String SELECT_COLABORADOR_BY_ID_QUERY = "SELECT * FROM Colaborador WHERE id_colaborador = ?";
    private static final String INSERT_COLABORADOR_QUERY = "INSERT INTO Colaborador (Nome_Colaborador, Email, Telefone, Codigo_Pais) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_COLABORADOR_QUERY = "UPDATE Colaborador SET Nome_Colaborador = ?, Email = ?, Telefone = ?, Codigo_Pais = ? WHERE id_colaborador = ?";
    private static final String DELETE_COLABORADOR_QUERY = "DELETE FROM Colaborador WHERE id_colaborador = ?";

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

    private Colaborador mapResultSetToColaborador(ResultSet resultSet) throws SQLException {
        Colaborador colaborador = new Colaborador();
        colaborador.setId_colaborador(resultSet.getInt("id_colaborador"));
        colaborador.setNome_Colaborador(resultSet.getString("Nome_Colaborador"));
        colaborador.setEmail(resultSet.getString("Email"));
        colaborador.setTelefone(resultSet.getString("Telefone"));
        colaborador.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
        return colaborador;
    }

    private void setColaboradorPreparedStatementValues(PreparedStatement preparedStatement, Colaborador colaborador) throws SQLException {
        preparedStatement.setString(1, colaborador.getNome_Colaborador());
        preparedStatement.setString(2, colaborador.getEmail());
        preparedStatement.setString(3, colaborador.getTelefone());
        preparedStatement.setInt(4, colaborador.getCodigo_Pais());
    }

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

