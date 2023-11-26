package repository;

import model.Pais;

import java.sql.*;

public class PaisRepository {

    private Connection con;

    public PaisRepository(Connection con) {
        this.con = con;
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
                throw new SQLException("Creating pais failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the pais object
                    pais.setCodigo_Pais(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating pais failed, no ID obtained.");
                }
            }

            return pais;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }




}
