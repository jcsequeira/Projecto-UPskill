package repository;


import model.Pais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaisRepository {

    private Connection con;

    public PaisRepository(Connection con) {
        this.con = con;
    }


    public List<Pais> getAllPais (){
        List<Pais> paisList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from Pais")){
            while (resultSet.next()){
                Pais pais = new Pais();
                pais.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
                pais.setNome_Pais(resultSet.getString("Nome_pais"));
                pais.setNacionalidade(resultSet.getString("Nacionalidade"));
                paisList.add(pais);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();}

        return paisList;
    }

    public Pais getPaisById(int paisId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Pais WHERE Codigo_Pais = ?")) {

            preparedStatement.setInt(1, paisId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Pais object and return it
                    Pais pais = new Pais();
                    pais.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
                    pais.setNome_Pais(resultSet.getString("Nome_Pais"));
                    pais.setNacionalidade(resultSet.getString("Nacionalidade"));
                    return pais;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the pais is not found or an exception occurs
    }


    //Versao Assumindo autoIncrement na tabela pais, na PK CodigoPais
    //Nota: o metodo vai inserir a mesma na tabela o pais, desde que o Codigo_Pais ainda nao exista, mas vai retornar null na resposta
    public Pais addPais(Pais pais) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Pais (Nome_Pais, Nacionalidade) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, pais.getNome_Pais());
            preparedStatement.setString(2, pais.getNacionalidade());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object pais failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the pais object
                    pais.setCodigo_Pais(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object pais failed, no ID obtained.");
                }
            }

            return pais;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Pais updatePais(int id, Pais pais) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE pais SET Nome_Pais = ?, Nacionalidade = ? WHERE Codigo_Pais = ?")) {

            preparedStatement.setString(1, pais.getNome_Pais());
            preparedStatement.setString(2, pais.getNacionalidade());
            preparedStatement.setInt(3, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // If the update was successful, return the updated pais
                return getPaisById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the update fails or an exception occurs
    }


    public void deletePais(int id) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM pais WHERE Codigo_Pais = ?")) {
            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting pais failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
}
