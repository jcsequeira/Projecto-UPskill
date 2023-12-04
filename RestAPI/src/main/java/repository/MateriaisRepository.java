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

    private static final String SELECT_ALL_MATERIAIS_QUERY = "SELECT * FROM materiais";
    private static final String SELECT_MATERIAL_BY_ID_QUERY = "SELECT * FROM Materiais WHERE id_Material = ?";
    private static final String INSERT_MATERIAL_QUERY = "INSERT INTO materiais (Tipo_Material) VALUES (?)";
    private static final String UPDATE_MATERIAL_QUERY = "UPDATE materiais SET Tipo_Material = ? WHERE id_Material = ?";
    private static final String DELETE_MATERIAL_QUERY = "DELETE FROM materiais WHERE id_Material = ?";

    public List<Materiais> getAllMateriais() {
        List<Materiais> materiaisList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_MATERIAIS_QUERY)) {

            while (resultSet.next()) {
                Materiais materiais = mapResultSetToMateriais(resultSet);
                materiaisList.add(materiais);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }

        return materiaisList;
    }

    public Materiais getMaterialById(int materialId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_MATERIAL_BY_ID_QUERY)) {

            preparedStatement.setInt(1, materialId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToMateriais(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public Materiais addMaterial(Materiais material) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_MATERIAL_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, material.getTipo_Material());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Materiais failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    material.setId_Material(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Materiais failed, no ID obtained.");
                }
            }

            return material;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public Materiais updateMaterial(int id, Materiais material) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_MATERIAL_QUERY)) {

            preparedStatement.setString(1, material.getTipo_Material());
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                return getMaterialById(id);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return null;
    }

    public String deleteMaterial(int materialId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_MATERIAL_QUERY)) {

            preparedStatement.setInt(1, materialId);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                return "Deleting Material failed, no rows affected.";
            } else {
                return "Deleting Material successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Material failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Materiais mapResultSetToMateriais(ResultSet resultSet) throws SQLException {
        Materiais materiais = new Materiais();
        materiais.setId_Material(resultSet.getInt("id_Material"));
        materiais.setTipo_Material(resultSet.getString("Tipo_Material"));
        return materiais;
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}
