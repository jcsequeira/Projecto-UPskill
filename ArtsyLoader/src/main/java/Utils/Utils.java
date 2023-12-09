package Utils;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;




import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import artsymodel.ArtsyArtist;
import model.*;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {




    public static List<Pais> paisesListGenerator(String csvFilePath, int enShortNameColumnIndex, int nationalityColumnIndex) {
        List<Pais> paisesList = new ArrayList<>();
        Map<Integer, String> countryMap = new HashMap<>();
        Map<Integer, String> nacionalityMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> records = reader.readAll();
            for (int i = 0; i < records.size(); i++) {
                String[] record = records.get(i);

                String enShortName = record[enShortNameColumnIndex];
                String nationality = record[nationalityColumnIndex];

                countryMap.put(i + 1, enShortName);
                nacionalityMap.put(i + 1, nationality);

                Pais pais = new Pais();
                pais.setNome_Pais(enShortName);
                pais.setNacionalidade(nationality);
                paisesList.add(pais);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return paisesList;   }





    public static List<Cidade> cidadeListGenerator(String csvFilePath) throws IOException {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            String[] cityNames = csvReader.readAll().stream().flatMap(Arrays::stream).toArray(String[]::new);
            return createCidadeList(cityNames);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Cidade> createCidadeList(String[] cityNames) {
        return IntStream.range(0, cityNames.length)
                .boxed()
                .map(i -> new Cidade(i + 1, cityNames[i]))
                .collect(Collectors.toList());
    }




    public static long countArtistsWithNullBirthday(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() == null)
                .count();
    }

    public static List<Tecnica> removeNullsAndDuplicatesTecnicas(List<Tecnica> tecnicas) {
        Set<String> uniqueTipos = new HashSet<>();
        return tecnicas.stream()
                .filter(tecnica -> {
                    String tipo = tecnica.getTipo_Tecnica();
                    return tipo != null && uniqueTipos.add(tipo);
                })
                .collect(Collectors.toList());
    }

    public static List<Materiais> removeNullsAndDuplicatesMateriais(List<Materiais> materiais) {
        Set<String> uniqueTipos = new HashSet<>();
        return materiais.stream()
                .filter(material -> {
                    String tipo = material.getTipo_Material();
                    return tipo != null && uniqueTipos.add(tipo);
                })
                .collect(Collectors.toList());
    }

    public static List<ArtsyArtist> removeArtistsWithNullBirthdayAndEmptyNationality(List<ArtsyArtist> artists) {
        return artists.stream()
                .filter(artist -> artist.getBirthday() != null && !Objects.equals(artist.getNationality(), ""))
                .collect(Collectors.toList());
    }

    public static HashMap<Integer, String> getAllIdArtistas(Connection con) {
        HashMap<Integer, String> artistIDs = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_artista, nome_artista FROM explorart.artista;")) {

            while (resultSet.next()) {
                int idArtista = resultSet.getInt("id_artista");
                String nomeArtista = resultSet.getString("nome_artista");

                artistIDs.put(idArtista, nomeArtista);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }
        return artistIDs;
    }

    public static HashMap<Integer, String> getAllIdObrasArte(Connection con) {
        HashMap<Integer, String> artworkIDs = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_Obra_Arte, Titulo FROM explorart.obra_arte;")) {

            while (resultSet.next()) {
                int idObraArte = resultSet.getInt("id_Obra_Arte");
                String nomeObraArte = resultSet.getString("Titulo");

                artworkIDs.put(idObraArte, nomeObraArte);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }
        return artworkIDs;
    }
    public static void updateObraArteArtistId(int artistaId, int obraArteId, Connection con) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Obra_Arte " +
                        "SET id_artista = ? " +
                        "WHERE id_Obra_Arte = ?;")) {

            // Set the values for the prepared statement
            preparedStatement.setInt(1, artistaId);
            preparedStatement.setInt(2, obraArteId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating failed, no rows affected.");
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static void updateObraArteMaterialId(Integer key, Integer value, Connection con) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE explorart.obra_materiais SET id_Material = ? WHERE id_Obra_Arte = ? AND id_Material = 1;")) {

            // Set the values for the prepared statement
            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, key);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating failed, no rows affected.");
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static HashMap<Integer, String> getAllIdMateriais(Connection con) {
        HashMap<Integer, String> materiaisIDs = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_Material,Tipo_Material FROM explorart.materiais;")) {

            while (resultSet.next()) {
                int id_Material = resultSet.getInt("id_Material");
                String Tipo_Material = resultSet.getString("Tipo_Material");

                materiaisIDs.put(id_Material, Tipo_Material);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }
        return materiaisIDs;
    }


    private static void handleSQLException(SQLException sqlException) {
    }


    public static HashMap<Integer, String> getAllIdTecnicas(Connection con) {
        HashMap<Integer, String> tecnicasIDs = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_Tecnica,Tipo_Tecnica FROM explorart.Tecnica;")) {

            while (resultSet.next()) {
                int id_Tecnica = resultSet.getInt("id_Tecnica");
                String Tipo_Tecnica = resultSet.getString("Tipo_Tecnica");

                tecnicasIDs.put(id_Tecnica, Tipo_Tecnica);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }
        return tecnicasIDs;        
    }

    public static void updateObraArteTecnicaId(Integer key, Integer value, Connection con) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE Obra_Arte " +
                        "SET id_Tecnica  = ? " +
                        "WHERE id_Obra_Arte = ?;")) {

            // Set the values for the prepared statement
            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, key);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating failed, no rows affected.");
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static HashMap<Integer, String> getAllIdMovimentos(Connection con) {
        HashMap<Integer, String> MovimentosIDs = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_Estilo,Nome_Movimento FROM explorart.Movimento;")) {

            while (resultSet.next()) {
                int id_Estilo = resultSet.getInt("id_Estilo");
                String Nome_Movimento = resultSet.getString("Nome_Movimento");

                MovimentosIDs.put(id_Estilo, Nome_Movimento );
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }
        return MovimentosIDs;

    }

    public static void updateObraArteMovimentoId(Integer value, Integer key, Connection con) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE explorart.obra_arte " +
                        "SET id_Estilo  = ? " +
                        "WHERE id_Obra_Arte = ?;")) {

            // Set the values for the prepared statement
            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, key);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating failed, no rows affected.");
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static HashMap<Integer, String> getAllIdEventos(Connection con) {
        HashMap<Integer, String> EventosIDs = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_Expo,Nome FROM explorart.evento;")) {

            while (resultSet.next()) {
                int id_Expo = resultSet.getInt("id_Expo");
                String Nome = resultSet.getString("Nome");

                EventosIDs.put(id_Expo, Nome);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }
        return EventosIDs;
    }

    public static HashMap<Integer, String> getAllIdGalerias(Connection con) {
        HashMap<Integer, String> GaleriasIDs = new HashMap<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id_Galeria,Nome_Galeria FROM explorart.galeria;")) {

            while (resultSet.next()) {
                int id_Galeria = resultSet.getInt("id_Galeria");
                String Nome_Galeria = resultSet.getString("Nome_Galeria");

                GaleriasIDs.put(id_Galeria, Nome_Galeria);
            }
        } catch (SQLException sqlException) {
            handleSQLException(sqlException);
        }
        return GaleriasIDs;
    }

    public static void updateEventoGaleriaId(Integer value, Integer key, Connection con) {
        try (PreparedStatement preparedStatement = con.prepareStatement(
                "UPDATE explorart.evento SET id_Galeria = ? WHERE id_Expo = ?;")) {

            // Set the values for the prepared statement
            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, key);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating failed, no rows affected.");
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
}
