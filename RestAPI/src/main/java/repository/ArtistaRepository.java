package repository;

import model.Artista;
import model.Obra_Arte;
import model.Pais;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArtistaRepository {
    private Connection con;

    public ArtistaRepository(Connection con) {
        this.con = con;
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public List<Artista> getAllArtists (){
        List<Artista> artistaList = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from artista")){
            while (resultSet.next()){
                Artista artista = new Artista();
                artista.setId_artista(resultSet.getLong("id_artista"));
                artista.setNome_artista(resultSet.getString("nome_artista"));
                artista.setData_Nascimento(resultSet.getDate("Data_Nascimento").toLocalDate());
                artista.setBiografia(resultSet.getString("Biografia"));
                if (resultSet.getDate("Data_Morte") != null){
                artista.setData_Morte(resultSet.getDate("Data_Morte").toLocalDate());}
                else {
                    artista.setData_Morte(null);
                }
                artista.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
                artista.setIsArtsy(resultSet.getInt("IsArtsy"));

                artistaList.add(artista);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();};

        return artistaList;
    }

    public Artista getArtistaById(int artistaId) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM artista WHERE id_artista = ?")) {

            preparedStatement.setInt(1, artistaId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Artista object and return it
                    Artista artista = new Artista();
                    artista.setId_artista(resultSet.getInt("id_artista"));
                    artista.setNome_artista(resultSet.getString("nome_artista"));
                    artista.setData_Nascimento(resultSet.getDate("Data_Nascimento").toLocalDate());
                    artista.setBiografia(resultSet.getString("Biografia"));
                    if (resultSet.getDate("Data_Morte") != null){
                        artista.setData_Morte(resultSet.getDate("Data_Morte").toLocalDate());}
                    else {
                        artista.setData_Morte(null);}
                    artista.setCodigo_Pais(resultSet.getInt("Codigo_Pais"));
                    artista.setIsArtsy(resultSet.getInt("IsArtsy"));

                    return artista;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
        return null; // Return null if the pais is not found or an exception occurs
    }


    public Artista addArtista(Artista artista) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "INSERT INTO artista (nome_artista, Data_Nascimento, Biografia, Data_Morte, Codigo_Pais, IsArtsy) " +
                        "VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {

            // Set the values for the prepared statement
            preparedStatement.setString(1, artista.getNome_artista());
            preparedStatement.setString(2, artista.getData_Nascimento().format(formatter));
            preparedStatement.setString(3, artista.getBiografia());
            preparedStatement.setString(4, artista.getData_Morte().format(formatter));
            preparedStatement.setInt(5, artista.getCodigo_Pais());
            preparedStatement.setInt(6, artista.getIsArtsy());

            // Execute the insert statement
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating object Artista failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Set the generated ID to the obraArte object
                    artista.setId_artista(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating object Artista failed, no ID obtained.");
                }
            }

            return artista;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null; // Return null or throw a custom exception based on your error handling strategy
        }
    }
}
