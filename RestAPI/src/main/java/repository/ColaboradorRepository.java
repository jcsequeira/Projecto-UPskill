package repository;

import model.Colaborador;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorRepository {
    private Connection con;

    public ColaboradorRepository(Connection con) {
        this.con = con;
    }

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
            e.printStackTrace();
            // Handle the exception appropriately
            return false;
        }
        return false;
    }



    public List<Colaborador> getAllColaboradores() {
        List<Colaborador> colaboradorList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Colaborador")) {
            while (resultSet.next()) {
                Colaborador colaborador = new Colaborador();
                colaborador.setId_colaborador(resultSet.getInt("id_colaborador"));
                colaborador.setNome_Colaborador(resultSet.getString("Nome_Colaborador"));
                colaborador.setEmail(resultSet.getString("Email"));
                colaborador.setTelefone(resultSet.getString("Telefone"));
                colaborador.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));

                colaboradorList.add(colaborador);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return colaboradorList;
    }


    public Colaborador getColaboradorById(int colaboradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Colaborador WHERE id_colaborador = ?")) {

            preparedStatement.setInt(1, colaboradorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Colaborador object and return it
                    Colaborador colaborador = new Colaborador();
                    colaborador.setId_colaborador(resultSet.getInt("id_colaborador"));
                    colaborador.setNome_Colaborador(resultSet.getString("Nome_Colaborador"));
                    colaborador.setEmail(resultSet.getString("Email"));
                    colaborador.setTelefone(resultSet.getString("Telefone"));
                    colaborador.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
                    return colaborador;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null; // Return null if the pais is not found or an exception occurs
    }



    public Colaborador addColaborador(Colaborador colaborador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Colaborador (Nome_Colaborador, Email, Telefone, Codigo_Pais) " +
                        "VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, colaborador.getNome_Colaborador());
            preparedStatement.setString(2, colaborador.getEmail());
            preparedStatement.setString(3, colaborador.getTelefone());
            preparedStatement.setInt(4, colaborador.getCodigo_Pais());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Colaborador failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the Colaborador object
                    colaborador.setId_colaborador(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Colaborador failed, no ID obtained.");
                }
            }

            return colaborador;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }


    public Colaborador updateColaborador(int colaboradorId, Colaborador colaborador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Colaborador " +
                        "SET Nome_Colaborador = ?, " +
                        "Email = ?, " +
                        "Telefone = ?, " +
                        "Codigo_Pais = ? " +
                        "WHERE id_colaborador = ?;")) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, colaborador.getNome_Colaborador());
            preparedStatement.setString(2, colaborador.getEmail());
            preparedStatement.setString(3, colaborador.getTelefone());
            preparedStatement.setInt(4, colaborador.getCodigo_Pais());
            preparedStatement.setInt(5, colaboradorId);

            // Execute the update statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating object Colaborador failed, no rows affected.");
            }

            return colaborador;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }


    public String deleteColaborador(int colaboradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM colaborador WHERE id_colaborador = ?")) {
            preparedStatement.setInt(1, colaboradorId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                //throw new SQLException("Deleting Colaborador failed, no rows affected.");
                return "Deleting Colaborador failed, no rows affected.";
            } else {return "Deleting Colaborador successful.";}
        } catch (SQLIntegrityConstraintViolationException e ) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "Deleting Colaborador failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
