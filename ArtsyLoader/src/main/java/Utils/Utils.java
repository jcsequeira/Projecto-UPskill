package Utils;


import artsymodel.ArtsyArtist;
import artsymodel.ArtsyArtwork;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import exceptions.ServiceException;
import model.Cidade;
import model.Materiais;
import model.Pais;
import model.Tecnica;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * The {@code Utils} class provides utility methods for various tasks, including CSV parsing,
 * data processing, and database operations.
 */

public class Utils {
    /**
     * Reads a CSV file containing country data and generates a list of {@code Pais} objects.
     *
     * @param csvFilePath           The path to the CSV file.
     * @param enShortNameColumnIndex The column index for English short names.
     * @param nationalityColumnIndex The column index for nationality.
     * @return A list of {@code Pais} objects representing countries.
     */
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

    /**
     * Reads a CSV file containing city names and generates a list of {@code Cidade} objects.
     *
     * @param csvFilePath The path to the CSV file.
     * @return A list of {@code Cidade} objects representing cities.
     * @throws IOException If an I/O error occurs.
     */

    public static List<Cidade> cidadeListGenerator(String csvFilePath) throws IOException {
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            String[] cityNames = csvReader.readAll().stream().flatMap(Arrays::stream).toArray(String[]::new);
            return createCidadeList(cityNames);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Creates a list of {@code Cidade} objects from an array of city names.
     *
     * @param cityNames The array of city names.
     * @return A list of {@code Cidade} objects representing cities.
     */
    public static List<Cidade> createCidadeList(String[] cityNames) {
        return IntStream.range(0, cityNames.length)
                .boxed()
                .map(i -> new Cidade(i + 1, cityNames[i]))
                .collect(Collectors.toList());
    }

    /**
     * Removes nulls and duplicates from the list of {@code Tecnica} objects.
     *
     * @param tecnicas The list of {@code Tecnica} objects.
     * @return A filtered list without nulls and duplicates.
     */
    public static List<Tecnica> removeNullsAndDuplicatesTecnicas(List<Tecnica> tecnicas) {
        Set<String> uniqueTipos = new HashSet<>();
        return tecnicas.stream()
                .filter(tecnica -> {
                    String tipo = tecnica.getTipo_Tecnica();
                    return tipo != null && uniqueTipos.add(tipo);
                })
                .collect(Collectors.toList());
    }
    /**
     * Removes nulls and duplicates from the list of {@code Materiais} objects.
     *
     * @param materiais The list of {@code Materiais} objects.
     * @return A filtered list without nulls and duplicates.
     */
    public static List<Materiais> removeNullsAndDuplicatesMateriais(List<Materiais> materiais) {
        Set<String> uniqueTipos = new HashSet<>();
        return materiais.stream()
                .filter(material -> {
                    String tipo = material.getTipo_Material();
                    return tipo != null && uniqueTipos.add(tipo);
                })
                .collect(Collectors.toList());
    }
    /**
     * Removes duplicates from the list of {@code ArtsyArtwork} objects based on artwork title.
     *
     * @param artsyArtworkList The list of {@code ArtsyArtwork} objects.
     * @return A filtered list without duplicate artwork titles.
     */
    public static List<ArtsyArtwork> removeDuplicatesArtworksArtsy (List<ArtsyArtwork> artsyArtworkList) {
        Set<String> uniqueArtworksTitle = new HashSet<>();
        return artsyArtworkList.stream()
                .filter(artwork -> {
                    String title = artwork.getTitle();
                    return title !=null && uniqueArtworksTitle.add(title);
                })
                .collect(Collectors.toList());
    }
    /**
     * Retrieves all artist IDs and names from the database.
     *
     * @param con The database connection.
     * @return A map containing artist IDs and names.
     */
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
        artistIDs.remove(1,"System_Artist_User");
        return artistIDs;
    }
    /**
     * Retrieves all artwork IDs and titles from the database.
     *
     * @param con The database connection.
     * @return A map containing artwork IDs and titles.
     */
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

    /**
     * Updates the artist ID of a specific artwork in the database.
     *
     * @param obraArteId The ID of the artwork.
     * @param artistaId  The ID of the artist.
     * @param con        The database connection.
     */
    public static void updateObraArteArtistId(int obraArteId,int artistaId, Connection con) {
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

    /**
     * Updates the material ID of a specific artwork in the database.
     *
     * @param key  The ID of the artwork.
     * @param value The ID of the material.
     * @param con   The database connection.
     */
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
    /**
     * Retrieves all material IDs and types from the database.
     *
     * @param con The database connection.
     * @return A map containing material IDs and types.
     */
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

    /**
     * Handles SQLExceptions by throwing a custom ServiceException
     * @param sqlException
     */
    private static void handleSQLException(SQLException sqlException) {
        throw new ServiceException("Erro ao atualizar: " + sqlException);
    }

    /**
     * Retrieves all technique IDs and types from the database.
     *
     * @param con The database connection.
     * @return A map containing technique IDs and types.
     */
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

    /**
     * Updates the technique ID of a specific artwork in the database.
     *
     * @param key  The ID of the artwork.
     * @param value The ID of the technique.
     * @param con   The database connection.
     */
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
    /**
     * Retrieves all movement IDs and names from the database.
     *
     * @param con The database connection.
     * @return A map containing movement IDs and names.
     */
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
    /**
     * Updates the movement ID of a specific artwork in the database.
     *
     * @param key  The ID of the artwork.
     * @param value The ID of the movement.
     * @param con   The database connection.
     */
    public static void updateObraArteMovimentoId(Integer key, Integer value, Connection con) {
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
    /**
     * Retrieves all event IDs and names from the database.
     *
     * @param con The database connection.
     * @return A map containing event IDs and names.
     */
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
    /**
     * Retrieves all gallery IDs and names from the database.
     *
     * @param con The database connection.
     * @return A map containing gallery IDs and names.
     */
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

    /**
     * Updates the gallery ID of a specific event in the database.
     *
     * @param key  The ID of the event.
     * @param value The ID of the gallery.
     * @param con   The database connection.
     */
    public static void updateEventoGaleriaId(Integer key, Integer value, Connection con) {
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
