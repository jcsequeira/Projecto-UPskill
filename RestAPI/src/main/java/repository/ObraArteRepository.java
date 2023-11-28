package repository;

import model.Obra_Arte;



import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ObraArteRepository {
    private Connection con;

    public ObraArteRepository(Connection con) {
        this.con = con;
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Obra_Arte> getAllObraArte (){
        List<Obra_Arte> obraArteList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select obra_arte.id_Obra_Arte, Titulo, Link_Imagem, Ano_Criacao, " +
                     "Preco, Largura, Profundidade, Diametro, IsActive,id_artista, id_Tecnica, id_Estilo, IsArtsy, id_material " +
                     "from obra_arte left join obra_materiais on obra_arte.id_obra_arte = obra_materiais.id_obra_arte;")){
            while (resultSet.next()){
                Obra_Arte obraArte = new Obra_Arte();
                obraArte.setId_Obra_Arte(resultSet.getInt("id_obra_arte"));
                obraArte.setTitulo(resultSet.getString("titulo"));
                obraArte.setLink_Imagem(resultSet.getString("Link_Imagem"));
                if (resultSet.getDate("Ano_Criacao") != null){
                    obraArte.setAno_Criacao(resultSet.getDate("Ano_Criacao").toLocalDate());}
                else {
                    obraArte.setAno_Criacao(null);
                }
                obraArte.setPreco(resultSet.getFloat("Preco"));
                obraArte.setLargura(resultSet.getFloat("Largura"));
                obraArte.setProfundidade(resultSet.getFloat("Profundidade"));
                obraArte.setDiametro(resultSet.getFloat("Diametro"));
                obraArte.setIsActive(resultSet.getInt("IsActive"));
                obraArte.setId_artista(resultSet.getInt("id_artista"));
                obraArte.setId_Tecnica(resultSet.getInt("id_Tecnica"));
                obraArte.setId_Estilo(resultSet.getInt("id_Estilo"));
                obraArte.setIsArtsy(resultSet.getInt("IsArtsy"));
                obraArte.setId_Material(resultSet.getInt("id_Material"));
                obraArteList.add(obraArte);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();}

        return obraArteList;
    }

    public Obra_Arte getObraArteById(int obraArteId){
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT obra_arte.id_Obra_Arte, Titulo, Link_Imagem, Ano_Criacao, Preco, Largura, Profundidade, " +
                        "Diametro, IsActive,id_artista, id_Tecnica, id_Estilo, IsArtsy, id_material " +
                        "FROM obra_arte " +
                        "JOIN obra_materiais ON obra_arte.id_obra_arte = obra_materiais.id_obra_arte " +
                        "WHERE obra_arte.id_Obra_Arte = ?;")) {

            preparedStatement.setInt(1, obraArteId);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Obra_Arte object and return it
                    Obra_Arte obraArte = new Obra_Arte();
                    obraArte.setId_Obra_Arte(resultSet.getInt("id_obra_arte"));
                    obraArte.setTitulo(resultSet.getString("titulo"));
                    obraArte.setLink_Imagem(resultSet.getString("Link_Imagem"));
                    if (resultSet.getDate("Ano_Criacao") != null){
                        obraArte.setAno_Criacao(resultSet.getDate("Ano_Criacao").toLocalDate());}
                    else {
                        obraArte.setAno_Criacao(null);
                    }
                    obraArte.setPreco(resultSet.getFloat("Preco"));
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

        return null; // Return null if the pais is not found or an exception occurs
    }

    public Obra_Arte addObraArte(Obra_Arte obraArte) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO Obra_Arte (Titulo, Link_Imagem, Ano_Criacao, Preco, Largura, Profundidade, Diametro, " +
                        "IsActive, id_artista, id_Tecnica, id_Estilo) VALUES " +
                        "    (?, ?, ?, ?, ?, ?, " +
                        "?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, obraArte.getTitulo());
            preparedStatement.setString(2, obraArte.getLink_Imagem());
            preparedStatement.setString(3, obraArte.getAno_Criacao().format(formatter));
            preparedStatement.setFloat(4, obraArte.getPreco());
            preparedStatement.setFloat(5, obraArte.getLargura());
            preparedStatement.setFloat(6, obraArte.getProfundidade());
            preparedStatement.setFloat(7, obraArte.getDiametro());
            preparedStatement.setInt(8, obraArte.getIsActive());
            preparedStatement.setInt(9, obraArte.getId_artista());
            preparedStatement.setInt(10, obraArte.getId_Tecnica());
            preparedStatement.setInt(11, obraArte.getId_Estilo());


            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object obraArte failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the obraArte object
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
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }

    public Obra_Arte updateObraArte(int obraArteID, Obra_Arte obraArte) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Obra_Arte " +
                        "SET Titulo = ?, " +
                        "Link_Imagem = ?, " +
                        "Ano_Criacao = ?, " +
                        "Preco = ?, " +
                        "Largura = ?, " +
                        "Profundidade = ?, " +
                        "Diametro = ?, " +
                        "IsActive = ?, " +
                        "id_artista = ?, " +
                        "id_Tecnica = ?, " +
                        "id_Estilo = ? " +
                        "WHERE id_Obra_Arte = ?;")) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, obraArte.getTitulo());
            preparedStatement.setString(2, obraArte.getLink_Imagem());
            preparedStatement.setString(3, obraArte.getAno_Criacao().format(formatter));
            preparedStatement.setFloat(4, obraArte.getPreco());
            preparedStatement.setFloat(5, obraArte.getLargura());
            preparedStatement.setFloat(6, obraArte.getProfundidade());
            preparedStatement.setFloat(7, obraArte.getDiametro());
            preparedStatement.setInt(8, obraArte.getIsActive());
            preparedStatement.setInt(9, obraArte.getId_artista());
            preparedStatement.setInt(10, obraArte.getId_Tecnica());
            preparedStatement.setInt(11, obraArte.getId_Estilo());
            preparedStatement.setInt(12, obraArteID);

            // Execute the insert statement
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
}
