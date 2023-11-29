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


    public List<Cidade> getAllCidade (){
        List<Cidade> cidadeList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from Cidade")){
            while (resultSet.next()){
                Cidade cidade = new Cidade();
                cidade.setId_Cidade(resultSet.getInt("id_Cidade"));
                cidade.setNome_Cidade(resultSet.getString("Nome_cidade"));
                cidadeList.add(cidade);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();}

        return cidadeList;
    }

    public Cidade getCidadeById(int cidadeId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Cidade WHERE id_Cidade = ?")) {

            preparedStatement.setInt(1, cidadeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Cidade object and return it
                    Cidade cidade = new Cidade();
                    cidade.setId_Cidade(resultSet.getInt("id_Cidade"));
                    cidade.setNome_Cidade(resultSet.getString("Nome_Cidade"));
                    return cidade;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the cidade is not found or an exception occurs
    }


    //Versao Assumindo autoIncrement na tabela cidade, na PK CodigoCidade
    //Nota: o metodo vai inserir a mesma na tabela o cidade, desde que o Codigo_Cidade ainda nao exista, mas vai retornar null na resposta
    public Cidade addCidade(Cidade cidade) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Cidade (Nome_Cidade) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            // Set the values for the prepared statement
            preparedStatement.setString(1, cidade.getNome_Cidade());
            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating object cidade failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the cidade object
                    cidade.setId_Cidade(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object cidade failed, no ID obtained.");
                }
            }

            return cidade;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Cidade updateCidade(int id, Cidade cidade) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE cidade SET Nome_Cidade = ? WHERE id_Cidade = ?")) {

            preparedStatement.setString(1, cidade.getNome_Cidade());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                // If the update was successful, return the updated cidade
                return getCidadeById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the update fails or an exception occurs
    }


    public String deleteCidade(int id) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM cidade WHERE id_Cidade = ?")) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting cidade failed, no rows affected.");
            } else {return "Deleting Artista successful.";}
        } catch (SQLIntegrityConstraintViolationException e ) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "Deleting Artista failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
