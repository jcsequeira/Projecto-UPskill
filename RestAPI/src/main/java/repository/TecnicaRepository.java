package repository;

import model.Tecnica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TecnicaRepository {

    private Connection con;

    public TecnicaRepository(Connection con) {
        this.con = con;
    }

    public List<Tecnica> getAllTecnicas() {
        List<Tecnica> tecnicasList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tecnica")) {

            while (resultSet.next()) {
                Tecnica tecnica = new Tecnica();
                tecnica.setId_Tecnica(resultSet.getInt("id_Tecnica")); // Assuming id is the primary key
                tecnica.setTipo_Tecnica(resultSet.getString("Tipo_Tecnica"));
                tecnicasList.add(tecnica);
            }

        } catch (SQLException sqlException) {sqlException.printStackTrace();}

        return tecnicasList;
    }

    public Tecnica getTecnicaById(int tecnicaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Tecnica WHERE id_Tecnica = ?")) {

            preparedStatement.setInt(1, tecnicaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Tecnica tecnica = new Tecnica();
                    tecnica.setId_Tecnica(resultSet.getInt("id_Tecnica"));
                    tecnica.setTipo_Tecnica(resultSet.getString("Tipo_Tecnica"));
                    return tecnica;
                }
            }
        } catch (SQLException e) {e.printStackTrace();}

        return null;
    }

    public Tecnica addTecnica (Tecnica tecnica) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Tecnica (Tipo_Tecnica) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, tecnica.getTipo_Tecnica());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Tecnica failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    tecnica.setId_Tecnica(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Tecnica failed, no ID obtained.");
                }
            }

            return tecnica;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Tecnica updateTecnica(int id, Tecnica tecnica) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Tecnica SET Tipo_Tecnica = ? WHERE id_Tecnica = ?")) {

            preparedStatement.setString(1, tecnica.getTipo_Tecnica());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getTecnicaById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String deleteTecnica(int tecnicaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM Tecnica WHERE id_Tecnica = ?")) {
            preparedStatement.setInt(1, tecnicaId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                return "Deleting Tecnica failed, no rows affected.";
            } else {
                return "Deleting Tecnica successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return "Deleting Tecnica failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
