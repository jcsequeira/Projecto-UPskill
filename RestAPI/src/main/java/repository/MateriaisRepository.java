package repository;

import model.Materiais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaisRepository {

    private Connection con;

    public MateriaisRepository(Connection con) {
        this.con = con;
    }

    public List<Materiais> getAllMateriais(){
        List<Materiais> materiaisList = new ArrayList<>();

        try (Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from materiais")){
            while (resultSet.next()){
                Materiais materiais = new Materiais();
                materiais.setId_Material(resultSet.getInt("id_Material"));
                materiais.setTipo_Material(resultSet.getString("Tipo_Material"));
                materiaisList.add(materiais);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();}

        return materiaisList;
    }

    public Materiais getMaterialById(int materialId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Materiais WHERE id_Material = ?")) {

            preparedStatement.setInt(1, materialId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Material object and return it
                    Materiais material = new Materiais();
                    material.setId_Material(resultSet.getInt("id_Material"));
                    material.setTipo_Material(resultSet.getString("Tipo_Material"));
                    return material;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the Material is not found or an exception occurs
    }

    public Materiais addMaterial(Materiais material) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO materiais (Tipo_Material) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, material.getTipo_Material());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Materiais failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the Materiais object
                    material.setId_Material(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object materiais failed, no ID obtained.");
                }
            }

            return material;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Materiais updateMaterial (int id, Materiais material) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE materiais SET Tipo_Material = ? WHERE id_Material = ?")) {

            preparedStatement.setString(1, material.getTipo_Material());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // If the update was successful, return the updated material
                return getMaterialById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the update fails or an exception occurs
    }

    public String deleteMaterial (int materialId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM materiais WHERE id_Material = ?")) {
            preparedStatement.setInt(1, materialId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                //throw new SQLException("Deleting Material failed, no rows affected.");
                return "Deleting Material failed, no rows affected.";
            } else {return "Deleting Material successful.";}
        } catch (SQLIntegrityConstraintViolationException e ) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "Deleting Material failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
