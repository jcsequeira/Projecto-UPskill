package repository;

import model.Galerista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class GaleristaRepository {
    private Connection con;

    public GaleristaRepository(Connection con) {
        this.con = con;
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Galerista> getAllGaleristas() {
        List<Galerista> galeristasList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM galerista")) {

            while (resultSet.next()) {
                Galerista galerista = new Galerista();
                if (resultSet.getDate("Data_Inicio_Atividade") != null){
                    galerista.setData_Inicio_Atividade(resultSet.getDate("Data_Inicio_Atividade").toLocalDate());}
                else {
                    galerista.setData_Inicio_Atividade(null);
                }
                galerista.setPassword(resultSet.getString("password"));
                galerista.setId_colaborador(resultSet.getInt("id_colaborador"));
                galeristasList.add(galerista);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return galeristasList;
    }

    public Galerista getGaleristaById(int galeristaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM galerista WHERE id_colaborador = ?")) {

            preparedStatement.setInt(1, galeristaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Galerista galerista = new Galerista();
                    if (resultSet.getDate("Data_Inicio_Atividade") != null){
                        galerista.setData_Inicio_Atividade(resultSet.getDate("Data_Inicio_Atividade").toLocalDate());}
                    else {
                        galerista.setData_Inicio_Atividade(null);
                    }
                    galerista.setPassword(resultSet.getString("password"));
                    galerista.setId_colaborador(resultSet.getInt("id_colaborador"));
                    return galerista;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the Galerista is not found or an exception occurs
    }

    public Galerista addGalerista(Galerista newGalerista) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO galerista (Data_Inicio_Atividade, password, id_colaborador) VALUES (?, ?, ?)")) {

            if (newGalerista.getData_Inicio_Atividade() != null) {
                preparedStatement.setString(1, newGalerista.getData_Inicio_Atividade().format(formatter));
            } else {
                preparedStatement.setNull(1, java.sql.Types.DATE); // Set the parameter to NULL in the database
            }
            preparedStatement.setString(2, newGalerista.getPassword());
            preparedStatement.setInt(3, newGalerista.getId_colaborador());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Galerista failed, no rows affected.");
            }

            return newGalerista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Galerista updateGalerista(int id, Galerista updatedGalerista) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE galerista SET Data_Inicio_Atividade = ?, password = ? WHERE id_colaborador = ?")) {

            if (updatedGalerista.getData_Inicio_Atividade() != null) {
                preparedStatement.setString(1, updatedGalerista.getData_Inicio_Atividade().format(formatter));
            } else {
                preparedStatement.setNull(1, java.sql.Types.DATE); // Set the parameter to NULL in the database
            }
            preparedStatement.setString(2, updatedGalerista.getPassword());
            preparedStatement.setInt(3, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getGaleristaById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the update fails or an exception occurs
    }

    public String deleteGalerista(int galeristaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM galerista WHERE id_colaborador = ?")) {

            preparedStatement.setInt(1, galeristaId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Galerista failed, no rows affected.";
            } else {
                return "Deleting Galerista successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return "Deleting Galerista failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
