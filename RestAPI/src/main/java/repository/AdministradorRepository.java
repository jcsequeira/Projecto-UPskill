package repository;

import model.Administrador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdministradorRepository {
    private Connection con;

    public AdministradorRepository(Connection con) {
        this.con = con;
    }


    public List<Administrador> getAllAdministrador() {
        List<Administrador> administradorsList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Administrador")) {

            while (resultSet.next()) {
                Administrador administrador = new Administrador();
                administrador.setPassword(resultSet.getString("password"));
                administrador.setId_colaborador(resultSet.getInt("id_colaborador"));
                administradorsList.add(administrador);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return administradorsList;
    }

    public Administrador getAdministradorById(int administradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM Administrador WHERE id_colaborador = ?")) {

            preparedStatement.setInt(1, administradorId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Administrador administrador = new Administrador();
                    administrador.setPassword(resultSet.getString("password"));
                    administrador.setId_colaborador(resultSet.getInt("id_colaborador"));
                    return administrador;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the Administrador is not found or an exception occurs
    }

    public Administrador addAdministrador(Administrador newAdministrador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO administrador (password, id_colaborador) VALUES (?, ?)")) {

            preparedStatement.setString(1, newAdministrador.getPassword());
            preparedStatement.setInt(2, newAdministrador.getId_colaborador());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Administrador failed, no rows affected.");
            }

            return newAdministrador;
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Administrador updateAdministrador(int id, Administrador updatedAdministrador) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Administrador SET password = ? WHERE id_colaborador = ?")) {


            preparedStatement.setString(1, updatedAdministrador.getPassword());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getAdministradorById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the update fails or an exception occurs
    }

    public String deleteAdministrador(int administradorId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "DELETE FROM Administrador WHERE id_colaborador = ?")) {

            preparedStatement.setInt(1, administradorId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Administrador failed, no rows affected.";
            } else {
                return "Deleting Administrador successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            return "Deleting Administrador failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
