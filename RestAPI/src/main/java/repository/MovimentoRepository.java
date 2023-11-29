package repository;

import model.Movimento;
import model.Movimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovimentoRepository {

    private Connection con;

    public MovimentoRepository(Connection con) {this.con = con; }

    public List<Movimento> getAllMovimentos (){
        List<Movimento> movimentosList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from movimento")){
            while (resultSet.next()){
                Movimento movimento = new Movimento();
                movimento.setId_Estilo(resultSet.getInt("id_Estilo"));
                movimento.setNome_Movimento(resultSet.getString("Nome_Movimento"));
                movimentosList.add(movimento);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();}

        return movimentosList;
    }

    public Movimento getMovimentoById(int movimentoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Movimento WHERE Id_Estilo = ?")) {

            preparedStatement.setInt(1, movimentoId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Movimento object and return it
                    Movimento movimento = new Movimento();
                    movimento.setId_Estilo(resultSet.getInt("Id_Estilo"));
                    movimento.setNome_Movimento(resultSet.getString("Nome_Movimento"));
                    return movimento;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the Movimento is not found or an exception occurs
    }
    
    public Movimento addMovimento(Movimento movimento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Movimento (Nome_Movimento) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, movimento.getNome_Movimento());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Movimento failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the Movimento object
                    movimento.setId_Estilo(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object movimento failed, no ID obtained.");
                }
            }

            return movimento;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Movimento updateMovimento(int id, Movimento movimento) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE movimento SET Nome_Movimento = ? WHERE Id_Estilo = ?")) {

            preparedStatement.setString(1, movimento.getNome_Movimento());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                // If the update was successful, return the updated movimento
                return getMovimentoById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the update fails or an exception occurs
    }


    public String deleteMovimento(int movimentoId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM movimento WHERE Id_Estilo = ?")) {
            preparedStatement.setInt(1, movimentoId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                //throw new SQLException("Deleting Movimento failed, no rows affected.");
                return "Deleting Movimento failed, no rows affected.";
            } else {return "Deleting Movimento successful.";}
        } catch (SQLIntegrityConstraintViolationException e ) {
            e.printStackTrace();
            // Handle the exception appropriately
            return "Deleting Movimento failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
