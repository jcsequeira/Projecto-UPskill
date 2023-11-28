package repository;


import model.Galeria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GaleriaRepository {

    private Connection con;

    public GaleriaRepository(Connection con) {
        this.con = con;
    }


    public List<Galeria> getAllGaleria (){
        List<Galeria> galeriaList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from Galeria")){
            while (resultSet.next()){
                Galeria galeria = new Galeria();
                galeria.setId_Galeria(resultSet.getInt("id_Galeria"));
                galeria.setNome_Galeria(resultSet.getString("Nome_Galeria"));
                galeria.setMorada(resultSet.getString("Morada"));
                galeria.setWebsite(resultSet.getString("Website"));
                galeria.setEmail(resultSet.getString("Email"));
                galeria.setTelefone(resultSet.getString("Telefone"));
                galeria.setId_Cidade(resultSet.getInt("id_Cidade"));
                galeria.setId_colaborador(resultSet.getInt("id_colaborador"));
                galeria.setIsArtsy(resultSet.getInt("IsArtsy"));
                galeriaList.add(galeria);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();}

        return galeriaList;
    }

    public Galeria getGaleriaById(int galeriaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Galeria WHERE id_Galeria = ?")) {

            preparedStatement.setInt(1, galeriaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Galeria object and return it
                    Galeria galeria = new Galeria();
                    galeria.setId_Galeria(resultSet.getInt("id_Galeria"));
                    galeria.setNome_Galeria(resultSet.getString("Nome_Galeria"));
                    galeria.setMorada(resultSet.getString("Morada"));
                    galeria.setWebsite(resultSet.getString("Website"));
                    galeria.setEmail(resultSet.getString("Email"));
                    galeria.setTelefone(resultSet.getString("Telefone"));
                    galeria.setId_Cidade(resultSet.getInt("id_Cidade"));
                    galeria.setId_colaborador(resultSet.getInt("id_colaborador"));
                    galeria.setIsArtsy(resultSet.getInt("IsArtsy"));
                    return galeria;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the galeria is not found or an exception occurs
    }


    //Versao Assumindo autoIncrement na tabela galeria, na PK CodigoGaleria
    //Nota: o metodo vai inserir a mesma na tabela o galeria, desde que o Codigo_Galeria ainda nao exista, mas vai retornar null na resposta
    public Galeria addGaleria(Galeria galeria) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Galeria (Nome_Galeria, Morada, Website, Email, Telefone, id_Cidade, id_colaborador, IsArtsy) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, galeria.getNome_Galeria());
            preparedStatement.setString(2, galeria.getMorada());
            preparedStatement.setString(3, galeria.getWebsite());
            preparedStatement.setString(4, galeria.getEmail());
            preparedStatement.setString(5, galeria.getTelefone());
            preparedStatement.setInt(6, galeria.getId_Cidade());
            preparedStatement.setInt(7, galeria.getId_colaborador());
            preparedStatement.setInt(8, galeria.getIsArtsy());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object galeria failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the galeria object
                    galeria.setId_Galeria(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object galeria failed, no ID obtained.");
                }
            }

            return galeria;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Galeria updateGaleria(int id, Galeria galeria) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Galeria SET Nome_Galeria = ?, Morada = ?, Website = ?, Email = ?, Telefone = ?, " +
                        "id_Cidade = ?, id_colaborador = ?, IsArtsy = ? WHERE id_Galeria = ?")) {

            preparedStatement.setString(1, galeria.getNome_Galeria());
            preparedStatement.setString(2, galeria.getMorada());
            preparedStatement.setString(3, galeria.getWebsite());
            preparedStatement.setString(4, galeria.getEmail());
            preparedStatement.setString(5, galeria.getTelefone());
            preparedStatement.setInt(6, galeria.getId_Cidade());
            preparedStatement.setInt(7, galeria.getId_colaborador());
            preparedStatement.setInt(8, galeria.getIsArtsy());
            preparedStatement.setInt(9, id); // assuming 'id' is the id_Galeria to update

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // If the update was successful, return the updated galeria
                return getGaleriaById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the update fails or an exception occurs
    }


    public String deleteGaleria(int galeriaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM galeria WHERE id_Galeria = ?")) {
            preparedStatement.setInt(1, galeriaId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                //throw new SQLException("Deleting Galeria  failed, no rows affected.");
                return "Deleting Galeria failed, no rows affected.";
            } else {return "Deleting Galeria  successful.";}
        } catch (SQLIntegrityConstraintViolationException e ) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "Deleting Galeria  failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
