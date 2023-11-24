package repository;

import model.Artista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository {
    private Connection con;

    public ArtistRepository(Connection con) {
        this.con = con;
    }

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

                artistaList.add(artista);
            }
        }
        catch (SQLException sqlException){sqlException.printStackTrace();};

        return artistaList;
    }

    public Artista getArtistaById(){
        // Por fazer
        return null;
    }
}
