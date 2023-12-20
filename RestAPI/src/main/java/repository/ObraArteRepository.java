package repository;

import model.Obra_Arte;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository class for handling database operations related to {@link Obra_Arte} entities.
 */
public class ObraArteRepository {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final String SELECT_ALL_OBRAARTE_QUERY =
            "SELECT obra_arte.id_Obra_Arte, Titulo, Link_Imagem, Ano_Criacao, Preco, altura, Largura, Profundidade, Diametro, " +
                    "IsActive, id_artista, id_Tecnica, id_Estilo, IsArtsy, id_material " +
                    "FROM obra_arte " +
                    "LEFT JOIN obra_materiais ON obra_arte.id_obra_arte = obra_materiais.id_obra_arte";
    private static final String SELECT_OBRAARTE_BY_ID_QUERY =
            "SELECT obra_arte.id_Obra_Arte, Titulo, Link_Imagem, Ano_Criacao, Preco, altura, Largura, Profundidade, Diametro, " +
                    "IsActive, id_artista, id_Tecnica, id_Estilo, IsArtsy, id_material " +
                    "FROM obra_arte " +
                    "JOIN obra_materiais ON obra_arte.id_obra_arte = obra_materiais.id_obra_arte " +
                    "WHERE obra_arte.id_Obra_Arte = ?";
    private static final String INSERT_OBRAARTE_QUERY =
            "INSERT INTO Obra_Arte (Titulo, Link_Imagem, Ano_Criacao, Preco, altura, Largura, Profundidade, Diametro, " +
                    "IsActive, id_artista, id_Tecnica, id_Estilo, IsArtsy) VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_OBRAARTE_QUERY =
            "UPDATE Obra_Arte " +
                    "SET Titulo = ?, " +
                    "Link_Imagem = ?, " +
                    "Ano_Criacao = ?, " +
                    "Preco = ?, " +
                    "altura = ?, " +
                    "Largura = ?, " +
                    "Profundidade = ?, " +
                    "Diametro = ?, " +
                    "IsActive = ?, " +
                    "id_artista = ?, " +
                    "id_Tecnica = ?, " +
                    "id_Estilo = ?, " +
                    "IsArtsy = ? " +
                    "WHERE id_Obra_Arte = ?";
    private static final String INSERT_OBRAMATERIAIS_QUERY =
            "INSERT INTO Obra_Materiais (id_Material, id_Obra_Arte) VALUES (?, ?)";
    private static final String UPDATE_OBRAMATERIAIS_QUERY =
            "UPDATE Obra_Materiais SET id_Material = ? WHERE id_Obra_Arte = ?";
    private static final String DELETE_OBRAARTE_QUERY =
            "DELETE FROM obra_arte WHERE id_obra_arte = ?";

    private final Connection con;

    /**
     * Constructs a new {@code ObraArteRepository} with the given database connection.
     *
     * @param con The database connection.
     */
    public ObraArteRepository(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves a list of all {@link Obra_Arte} entities from the database.
     *
     * @return A list of {@link Obra_Arte} entities.
     */
    public List<Obra_Arte> getAllObraArte() {
        List<Obra_Arte> obraArteList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_OBRAARTE_QUERY)) {

            while (resultSet.next()) {
                Obra_Arte obraArte = mapResultSetToObraArte(resultSet);
                obraArteList.add(obraArte);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return obraArteList;
    }

    /**
     * Retrieves a {@link Obra_Arte} entity by its ID from the database.
     *
     * @param obraArteId The ID of the {@link Obra_Arte} entity to retrieve.
     * @return The {@link Obra_Arte} entity, or {@code null} if not found.
     */
    public Obra_Arte getObraArteById(int obraArteId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(SELECT_OBRAARTE_BY_ID_QUERY)) {
            preparedStatement.setInt(1, obraArteId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetToObraArte(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return null;
    }

    /**
     * Adds a new {@link Obra_Arte} entity to the database.
     *
     * @param obraArte The {@link Obra_Arte} entity to add.
     * @return The added {@link Obra_Arte} entity with updated ID.
     */
    public Obra_Arte addObraArte(Obra_Arte obraArte) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                INSERT_OBRAARTE_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            setObraArteParameters(preparedStatement, obraArte);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object obraArte failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obraArte.setId_Obra_Arte(generatedKeys.getInt(1));
                    try (PreparedStatement preparedStatementObraMateriais = con.prepareStatement(
                            "INSERT INTO Obra_Materiais (id_Material, id_Obra_Arte) values " +
                                    "(?, ?)")) {

                        // Set the values for the prepared statement
                        preparedStatementObraMateriais.setInt(1, obraArte.getId_Material());
                        preparedStatementObraMateriais.setInt(2,obraArte.getId_Obra_Arte());

                        // Execute the insert statement
                        int affectedRowsObraMateriais = preparedStatementObraMateriais.executeUpdate();

                        if (affectedRowsObraMateriais == 0) {
                            throw new SQLException("Creating object Obra Arte failed, no rows affected.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        // Handle the exception appropriately
                        return null; // Return null or throw a custom exception based on your error handling strategy
                    }

                } else {
                    throw new SQLException("Creating object obraArte failed, no ID obtained.");
                }
            }

            return obraArte;
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    /**
     * Updates an existing {@link Obra_Arte} entity in the database.
     *
     * @param obraArteID The ID of the {@link Obra_Arte} entity to update.
     * @param obraArte   The updated {@link Obra_Arte} entity.
     * @return The updated {@link Obra_Arte} entity.
     */
    public Obra_Arte updateObraArte(int obraArteID, Obra_Arte obraArte) {
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_OBRAARTE_QUERY)) {

            setObraArteParameters(preparedStatement, obraArte);
            preparedStatement.setInt(14, obraArteID);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating object obraArte failed, no rows affected.");
            }

            try (PreparedStatement preparedStatementObraMateriais = con.prepareStatement(
                    "UPDATE Obra_Materiais SET id_Material = ? " +
                            "WHERE id_Obra_Arte = ?;")) {

                // Set the values for the prepared statement
                preparedStatementObraMateriais.setInt(1, obraArte.getId_Material());
                preparedStatementObraMateriais.setInt(2, obraArteID);

                // Execute the update statement
                int affectedRowsObraMateriais = preparedStatementObraMateriais.executeUpdate();

                if (affectedRowsObraMateriais == 0) {
                    throw new SQLException("Updating object Obra Arte failed, no rows affected.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle the exception appropriately
                return null; // Return null or throw a custom exception based on your error handling strategy
            }

            return obraArte;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    /**
     * Deletes a {@link Obra_Arte} entity from the database.
     *
     * @param obraArteId The ID of the {@link Obra_Arte} entity to delete.
     * @return A message indicating the success or failure of the deletion.
     */
    public String deleteObraArte(int obraArteId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_OBRAARTE_QUERY)) {
            preparedStatement.setInt(1, obraArteId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                return "Deleting Obra Arte failed, no rows affected.";
            } else {
                return "Deleting Obra Arte successful.";
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            handleSQLException(e);
            return "Deleting Obra Arte failed, no rows affected.";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Maps the result set to a {@link Obra_Arte} entity.
     *
     * @param resultSet The result set containing data from the database.
     * @return The mapped {@link Obra_Arte} entity.
     * @throws SQLException If a SQL exception occurs.
     */
    private Obra_Arte mapResultSetToObraArte(ResultSet resultSet) throws SQLException {
        Obra_Arte obraArte = new Obra_Arte();
        obraArte.setId_Obra_Arte(resultSet.getInt("id_Obra_Arte"));
        obraArte.setTitulo(resultSet.getString("Titulo"));
        obraArte.setLink_Imagem(resultSet.getString("Link_Imagem"));
        obraArte.setAno_Criacao(mapToLocalDate(resultSet.getString("Ano_Criacao")));
        obraArte.setPreco(resultSet.getFloat("Preco"));
        obraArte.setAltura(resultSet.getFloat("altura"));
        obraArte.setLargura(resultSet.getFloat("Largura"));
        obraArte.setProfundidade(resultSet.getFloat("Profundidade"));
        obraArte.setDiametro(resultSet.getFloat("Diametro"));
        obraArte.setIsActive(resultSet.getInt("IsActive"));
        obraArte.setId_artista(resultSet.getInt("id_artista"));
        obraArte.setId_Tecnica(resultSet.getInt("id_Tecnica"));
        obraArte.setId_Estilo(resultSet.getInt("id_Estilo"));
        obraArte.setIsArtsy(resultSet.getInt("IsArtsy"));
        obraArte.setId_Material(resultSet.getInt("id_Material"));
        return obraArte;
    }

    /**
     * Maps a date string to a {@link LocalDate} object using the specified date formatter.
     *
     * @param dateString The date string to map.
     * @return The mapped {@link LocalDate} object.
     */
    private LocalDate mapToLocalDate(String dateString) {
        return dateString != null ? LocalDate.parse(dateString, formatter) : null;
    }

    /**
     * Sets the parameters of a prepared statement with the values of a {@link Obra_Arte} entity.
     *
     * @param preparedStatement The prepared statement to set the parameters for.
     * @param obraArte          The {@link Obra_Arte} entity containing the values.
     * @throws SQLException If a SQL exception occurs.
     */
    private void setObraArteParameters(PreparedStatement preparedStatement, Obra_Arte obraArte)
            throws SQLException {
        preparedStatement.setString(1, obraArte.getTitulo());
        preparedStatement.setString(2, obraArte.getLink_Imagem());
        preparedStatement.setObject(3, obraArte.getAno_Criacao(), java.sql.Types.DATE);
        preparedStatement.setFloat(4, obraArte.getPreco());
        preparedStatement.setFloat(5, obraArte.getPreco());
        preparedStatement.setFloat(6, obraArte.getLargura());
        preparedStatement.setFloat(7, obraArte.getProfundidade());
        preparedStatement.setFloat(8, obraArte.getDiametro());
        preparedStatement.setInt(9, obraArte.getIsActive());
        preparedStatement.setInt(10, obraArte.getId_artista());
        preparedStatement.setInt(11, obraArte.getId_Tecnica());
        preparedStatement.setInt(12, obraArte.getId_Estilo());
        preparedStatement.setInt(13, obraArte.getIsArtsy());
    }

    /**
     * Handles a SQLException by printing the stack trace.
     *
     * @param e The SQLException to handle.
     */
    private void handleSQLException(SQLException e) {
        e.printStackTrace();
    }
}

