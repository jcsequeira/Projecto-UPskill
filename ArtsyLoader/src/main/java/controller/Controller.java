package controller;


import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import artsymodel.*;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.*;
import exceptions.ServiceException;
import restapiservice.RestApiService;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static apiserviceartsy.ApiServiceArtsy.getArtsyItem;
import static apiserviceartsy.ApiServiceArtsy.getArtsyPartnerByRef;
/**
 * The {@code Controller} class is responsible for coordinating the population of various data entities
 * in the application. It makes use of REST API calls to retrieve data from external sources and then
 * processes and stores the data in the application's database.
 */

public class Controller {

    /**
     * File path for the CSV file containing city data.
     */
    public static final String CSV_CITYS_FILE_PATH = "ArtsyLoader/src/main/resources/citysPT.csv";

    /**
     * File path for the CSV file containing country nationality data.
     */
    public static final String CSV_COUNTRYS_NACIONALITYS_FILE_PATH = "ArtsyLoader/src/main/resources/countries.csv";

    /**
     * URL for retrieving all artists from the Artsy API.
     */
    public static final String ALL_ARTIST_ARTSY_URL = "https://api.artsy.net/api/artists?artworks=true&sort=-trending&size=1000&page=1";

    /**
     * URL for retrieving all movements (genes) from the Artsy API.
     */
    public static final String ALL_MOVIMENTOS_ARTSY_URL = "https://api.artsy.net/api/genes?size=1000";

    /**
     * URL for retrieving all artworks from the Artsy API.
     */
    public static final String ALL_ARTWORKS_ARTSY_URL = "https://api.artsy.net/api/artworks?size=100&page=1";

    /**
     * URL for retrieving all partners from the Artsy API.
     */
    public static final String ALL_PARTNERS_ARTSY_URL = "https://api.artsy.net/api/partners?size=500";

    /**
     * URL for retrieving all upcoming shows from the Artsy API.
     */
    public static final String ALL_SHOWS_ARTSY_URL = "https://api.artsy.net/api/shows?status=upcoming";

    /**
     * REST API endpoint for populating cities.
     */
    public static final String REST_ENDPOINT_CIDADES_API_URL = "http://localhost:4567/populate/cidades";

    /**
     * REST API endpoint for populating countries.
     */
    public static final String REST_ENDPOINT_PAISES_API_URL = "http://localhost:4567/populate/paises";

    /**
     * REST API endpoint for populating artists.
     */
    public static final String REST_ENDPOINT_ARTISTAS_API_URL = "http://localhost:4567/populate/artistas";

    /**
     * REST API endpoint for populating movements.
     */
    public static final String REST_ENDPOINT_MOVIMENTOS_API_URL = "http://localhost:4567/populate/movimentos";

    /**
     * REST API endpoint for populating techniques.
     */
    public static final String REST_ENDPOINT_TECNICAS_API_URL = "http://localhost:4567/populate/tecnicas";

    /**
     * REST API endpoint for populating materials.
     */
    public static final String REST_ENDPOINT_MATERIAIS_API_URL = "http://localhost:4567/populate/materiais";

    /**
     * REST API endpoint for populating artworks.
     */
    public static final String REST_ENDPOINT_OBRASARTE_API_URL = "http://localhost:4567/populate/obrasarte";

    /**
     * REST API endpoint for populating galleries.
     */
    public static final String REST_ENDPOINT_GALERIAS_API_URL = "http://localhost:4567/populate/galerias";

    /**
     * REST API endpoint for populating events.
     */
    public static final String REST_ENDPOINT_EVENTOS_API_URL = "http://localhost:4567/populate/eventos";


    /**
     * Populates city data using the REST API.
     *
     * This method sends a POST request to the specified REST endpoint {@value #REST_ENDPOINT_CIDADES_API_URL}
     * with city data generated from the CSV file located at {@value #CSV_CITYS_FILE_PATH}.
     *
     * @throws IOException   If an I/O error occurs while processing the city data or making the API request.
     * @throws CsvException  If an error occurs while parsing the CSV file containing city data.
     */
    public static void populateCidades() throws IOException, CsvException {
        RestApiService.postToRestApi(REST_ENDPOINT_CIDADES_API_URL
                ,Utils.cidadeListGenerator(CSV_CITYS_FILE_PATH));
    }
    /**
     * Populates country data using the REST API.
     *
     * This method sends a POST request to the specified REST endpoint {@value #REST_ENDPOINT_PAISES_API_URL}
     * with country data generated from the CSV file located at {@value #CSV_COUNTRYS_NACIONALITYS_FILE_PATH}.
     *
     * @throws IOException   If an I/O error occurs while processing the country data or making the API request.
     * @throws CsvException  If an error occurs while parsing the CSV file containing country data.
     */
    public static void populatePaises() throws IOException, CsvException {
        RestApiService.postToRestApi(REST_ENDPOINT_PAISES_API_URL
                ,Utils.paisesListGenerator(CSV_COUNTRYS_NACIONALITYS_FILE_PATH,3,4));
    }
    /**
     * Populates movement data using the REST API.
     *
     * This method sends a POST request to the specified REST endpoint {@value #REST_ENDPOINT_MOVIMENTOS_API_URL}
     * with movement data generated from the Artsy API.
     *
     * @throws IOException If an I/O error occurs while processing the movement data or making the API request.
     */
    public static void populateMovimentos() throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MOVIMENTOS_API_URL
                ,DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyItems(ALL_MOVIMENTOS_ARTSY_URL, ArtsyGene.class), GeneConverter.class));
    }
    /**
     * Populates gallery data using the REST API.
     *
     * This method sends a POST request to the specified REST endpoint {@value #REST_ENDPOINT_GALERIAS_API_URL}
     * with gallery data generated from the Artsy API.
     *
     * @throws IOException If an I/O error occurs while processing the gallery data or making the API request.
     */
    public static void populateGalerias() throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_GALERIAS_API_URL
                ,DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyItems(ALL_PARTNERS_ARTSY_URL, ArtsyPartner.class), PartnerConverter.class));
    }
    /**
     * Loads a list of Artsy artworks from the Artsy API.
     *
     * This method makes a GET request to the Artsy API endpoint {@value #ALL_ARTWORKS_ARTSY_URL}
     * to retrieve a list of artworks and returns it.
     *
     * @return A list of {@link ArtsyArtwork} objects representing artworks retrieved from the Artsy API.
     * @throws IOException If an I/O error occurs while making the API request or processing the response.
     */
    public static List<ArtsyArtwork> LoadArtsyArtworksList() throws IOException {
        return ApiServiceArtsy.getAllArtsyItems(ALL_ARTWORKS_ARTSY_URL, ArtsyArtwork.class);
    }

    /**
     * Populates the database with techniques from the provided list of Artsy artworks.
     *
     * This method processes the list of artworks using {@link CategoryConverter} to obtain a list of techniques.
     * It then removes null values and duplicates before sending the processed list to the server endpoint {@value #REST_ENDPOINT_TECNICAS_API_URL}
     * using the {@link RestApiService#postToRestApi(String, List)} method.
     *
     * @param allArtsyArtworksList The list of Artsy artworks used to extract techniques.
     * @throws IOException If an I/O error occurs while making the API request or processing the response.
     */
    public static void populateTecnicas(List<ArtsyArtwork> allArtsyArtworksList) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_TECNICAS_API_URL,
                Utils.removeNullsAndDuplicatesTecnicas(DataProcessor.listProcessor(allArtsyArtworksList, CategoryConverter.class)));
    }

    /**
     * Populates the database with materials from the provided list of Artsy artworks.
     *
     * This method processes the list of artworks using {@link MediumConverter} to obtain a list of materials.
     * It then removes null values and duplicates before sending the processed list to the server endpoint {@value #REST_ENDPOINT_MATERIAIS_API_URL}
     * using the {@link RestApiService#postToRestApi(String, List)} method.
     *
     * @param allArtsyArtworksList The list of Artsy artworks used to extract materials.
     * @throws IOException If an I/O error occurs while making the API request or processing the response.
     */
    public static void populateMateriais(List<ArtsyArtwork> allArtsyArtworksList) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MATERIAIS_API_URL,
                Utils.removeNullsAndDuplicatesMateriais(DataProcessor.listProcessor(allArtsyArtworksList, MediumConverter.class)));
    }

    /**
     * Creates a mapping between Artsy artworks and their corresponding artists by fetching artist information
     * using the artists' href links and the {@code ArtsyArtist} class.
     *
     * @param artsyArtworksList The list of Artsy artworks for which the mapping is created.
     * @return A {@code HashMap} containing Artsy artworks as keys and their corresponding artists as values.
     */
    public static HashMap<ArtsyArtwork, ArtsyArtist> matchMapArtsyArtworkArtist(List<ArtsyArtwork> artsyArtworksList) {
        HashMap<ArtsyArtwork, ArtsyArtist> artistArtworkArtsyMap = new HashMap<>();
        for (ArtsyArtwork artsyArtwork : artsyArtworksList) {
            try {
                artistArtworkArtsyMap.put(artsyArtwork, getArtsyItem(artsyArtwork.getArtistsHref(), ArtsyArtist.class));
            } catch (RuntimeException | IOException ignored) {
                // Ignore and continue iterating
            }
        }
        return artistArtworkArtsyMap;
    }

    /**
     * Creates a mapping between Artsy artworks and their corresponding genes (movements) by fetching gene information
     * using the genes' href links and the {@code ArtsyGene} class.
     *
     * @param artistArtworkArtsyMap The mapping between Artsy artworks and their corresponding artists.
     * @return A {@code HashMap} containing Artsy artworks as keys and their corresponding genes as values.
     */
    public static HashMap<ArtsyArtwork, ArtsyGene> matchMapArtsyArtworkGene(HashMap<ArtsyArtwork, ArtsyArtist> artistArtworkArtsyMap) {
        HashMap<ArtsyArtwork, ArtsyGene> geneArtworkArtsyMap = new HashMap<>();
        for (Map.Entry<ArtsyArtwork, ArtsyArtist> entry : artistArtworkArtsyMap.entrySet()) {
            try {
                geneArtworkArtsyMap.put(entry.getKey(), getArtsyItem(entry.getKey().getGenesHref(), ArtsyGene.class));
            } catch (RuntimeException | IOException ignored) {
                // Ignore and continue iterating
            }
        }
        return geneArtworkArtsyMap;
    }


    /**
     * Populates the database with movements using the provided mapping between Artsy artworks and their corresponding genes.
     *
     * @param GeneArtworkArtsyMap The mapping between Artsy artworks and their corresponding genes.
     * @throws IOException If an I/O exception occurs.
     */
    public static void populateMovimentos(HashMap<ArtsyArtwork, ArtsyGene> GeneArtworkArtsyMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MOVIMENTOS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(GeneArtworkArtsyMap.values()), GeneConverter.class));
    }

    /**
     * Populates the database with artists using the provided mapping between Artsy artworks and their corresponding artists.
     *
     * @param matchMap The mapping between Artsy artworks and their corresponding artists.
     * @throws IOException If an I/O exception occurs.
     */
    public static void populateArtistas(HashMap<ArtsyArtwork, ArtsyArtist> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_ARTISTAS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.values()), ArtistConverter.class));
    }

    /**
     * Populates the database with artworks using the provided mapping between Artsy artworks and their corresponding artists.
     *
     * @param matchMap The mapping between Artsy artworks and their corresponding artists.
     * @throws IOException If an I/O exception occurs.
     */
    public static void populateObrasArte(HashMap<ArtsyArtwork, ArtsyArtist> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_OBRASARTE_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.keySet()), ArtworkConverter.class));
    }

    /**
     * Updates the IDs in the database for artworks, artists, materials, techniques, styles, and galleries based on the provided mappings.
     *
     * @param con              The database connection.
     * @param matchMap         The mapping between Artsy artworks and their corresponding artists.
     * @param matchMapGene     The mapping between Artsy artworks and their corresponding genes.
     * @param matchMapPartner  The mapping between Artsy shows and their corresponding partners.
     */
    public static void updateArtworksIDs(Connection con, HashMap<ArtsyArtwork,ArtsyArtist> matchMap,
                                               HashMap<ArtsyArtwork,ArtsyGene> matchMapGene, HashMap<ArtsyShow,ArtsyPartner> matchMapPartner) {

        HashMap<Integer,String> artworkIDs = Utils.getAllIdObrasArte(con);
        try {
            System.out.println("Update Artistas Id na tabela Obras de Arte: ");
            //algoritmo para ArtistasID
            HashMap<Integer, String> artistIDs = Utils.getAllIdArtistas(con);
            HashMap<Integer, Integer> idMatchUP = new HashMap<>();
            for (Map.Entry<ArtsyArtwork,ArtsyArtist> entry : matchMap.entrySet()) {
                idMatchUP.put(getMatchId(entry.getKey().toString(), artworkIDs),getMatchId(entry.getValue().toString(), artistIDs));
            }
            for (Map.Entry<Integer, Integer> entry : idMatchUP.entrySet()) {
                Utils.updateObraArteArtistId(entry.getKey(), entry.getValue(), con);
            }
            System.out.println("Update com sucesso!");
        } catch (ServiceException e) {
            System.out.println("Erro ao atualizar Artistas Id na tabela Obras de Arte: " + e.getMessage());        }
// Similar try-catch blocks for other operations
        try {
            System.out.println("Update Materiais Id na tabela Obras de Arte: ");
            //algoritmo para Materiais
            HashMap<Integer, Integer> obraMaterialMapIds = new HashMap<>();
            HashMap<Integer, String> MateriaisIDs = Utils.getAllIdMateriais(con);
            for (Map.Entry<ArtsyArtwork,ArtsyArtist> entry : matchMap.entrySet()) {
                obraMaterialMapIds.put(getMatchId(entry.getKey().getTitle(), artworkIDs), getMatchId(entry.getKey().getMedium(), MateriaisIDs));
            }
            for (Map.Entry<Integer, Integer> entry : obraMaterialMapIds.entrySet()) {
                Utils.updateObraArteMaterialId(entry.getKey(), entry.getValue(), con);
            }
            System.out.println("Update com sucesso!");
        } catch (ServiceException e) {
            System.out.println("Erro ao atualizar Materiais Id na tabela Obras de Arte: " + e.getMessage());
        }
        try {
            System.out.println("Update Tecnicas Id na tabela Obras de Arte: ");
            //algoritmo para Tecnicas
            HashMap<Integer, String> TecnicasIDs = Utils.getAllIdTecnicas(con);
            HashMap<Integer, Integer> obraTecnicaMapString = new HashMap<>();
            for (Map.Entry<ArtsyArtwork,ArtsyArtist> entry : matchMap.entrySet()) {
                obraTecnicaMapString.put(getMatchId(entry.getKey().getTitle(), artworkIDs), getMatchId(entry.getKey().getCategory(), TecnicasIDs));
            }
            for (Map.Entry<Integer, Integer> entry : obraTecnicaMapString.entrySet()) {
                Utils.updateObraArteTecnicaId(entry.getKey(), entry.getValue(), con);
            }
            System.out.println("Update com sucesso!");
        } catch (ServiceException e) {
            System.out.println("Erro ao atualizar Tecnicas Id na tabela Obras de Arte: " + e.getMessage());
        }
// Similar try-catch blocks for Estilo
        try {
            System.out.println("Update Estilo Id na tabela Obras de Arte: ");
            //algoritmo para Estilo
            HashMap<Integer, String> MovimentosIDs = Utils.getAllIdMovimentos(con);
            HashMap<Integer, Integer> MovimentoObraMap = new HashMap<>();
            for (Map.Entry<ArtsyArtwork,ArtsyGene> entry : matchMapGene.entrySet()) {
                MovimentoObraMap.put(getMatchId(entry.getKey().getTitle(), artworkIDs), getMatchId(entry.getValue().getName(), MovimentosIDs));
            }
            for (Map.Entry<Integer, Integer> entry : MovimentoObraMap.entrySet()) {
                Utils.updateObraArteMovimentoId(entry.getKey(), entry.getValue(), con);
            }
            System.out.println("Update com sucesso!");
        } catch (ServiceException e) {
            System.out.println("Erro ao atualizar Estilo Id na tabela Obras de Arte: " + e.getMessage());
        }
        try {
            System.out.println("Update GaleriaID na tabela Eventos: ");
            //algoritmo para GaleriaID
            HashMap<Integer, String> EventosIDs = Utils.getAllIdEventos(con);
            HashMap<Integer, String> GaleriasIDs = Utils.getAllIdGalerias(con);
            HashMap<Integer, Integer> idMatchGaleriaEvento = new HashMap<>();

            for (Map.Entry<ArtsyShow,ArtsyPartner> entry : matchMapPartner.entrySet()) {
                idMatchGaleriaEvento.put(getMatchId(entry.getKey().getName(), EventosIDs),getMatchId(entry.getValue().getName(), GaleriasIDs));
            }
            for (Map.Entry<Integer, Integer> entry : idMatchGaleriaEvento.entrySet()) {
                Utils.updateEventoGaleriaId(entry.getKey(), entry.getValue(), con);
            }
            System.out.println("Update com sucesso!");
        } catch (ServiceException e) {
            System.out.println("Erro ao atualizar GaleriaID na tabela Eventos: " + e.getMessage());
        }
    }

    /**
     * Loads a list of Artsy shows from the Artsy API.
     *
     * @return A list of Artsy shows.
     * @throws IOException If an I/O exception occurs.
     */
    public static List<ArtsyShow> loadArtsyShowsList() throws IOException {
        return ApiServiceArtsy.getAllArtsyItems(ALL_SHOWS_ARTSY_URL, ArtsyShow.class);
    }

    /**
     * Creates a mapping between Artsy shows and their corresponding partners.
     *
     * @param artsyShowsList The list of Artsy shows to create the mapping from.
     * @return A mapping between Artsy shows and their corresponding partners.
     */
    public static HashMap<ArtsyShow, ArtsyPartner> matchMapArtsyShowPartner(List<ArtsyShow> artsyShowsList) {
        HashMap<ArtsyShow, ArtsyPartner> partnerShowArtsyMap = new HashMap<>();
        for (ArtsyShow artsyShow : artsyShowsList) {
            try {
                partnerShowArtsyMap.put(artsyShow, getArtsyPartnerByRef(artsyShow.getPartnerHref()));
            } catch (RuntimeException | IOException ignored) { // Ignore and keep iterating
            }
        }
        return partnerShowArtsyMap;
    }

    /**
     * Populates galleries in the database based on the provided mapping between Artsy shows and partners.
     *
     * @param matchMapShowPartner The mapping between Artsy shows and their corresponding partners.
     * @throws IOException If an I/O exception occurs.
     */
    public static void populateGalerias(HashMap<ArtsyShow, ArtsyPartner> matchMapShowPartner) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_GALERIAS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMapShowPartner.values()), PartnerConverter.class));
    }

    /**
     * Populates events in the database based on the provided mapping between Artsy shows and partners.
     *
     * @param matchMap The mapping between Artsy shows and their corresponding partners.
     * @throws IOException If an I/O exception occurs.
     */
    public static void populateEventos(HashMap<ArtsyShow, ArtsyPartner> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_EVENTOS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.keySet()), ShowConverter.class));
    }

    /**
     * Gets the ID matching the specified artist name from the provided map of IDs.
     *
     * @param artistName The artist name to find the ID for.
     * @param mapIDs     The map containing artist IDs.
     * @return The matched artist ID, or -1 if not found.
     */
    public static int getMatchId(String artistName, HashMap<Integer, String> mapIDs) {
        for (Map.Entry<Integer, String> entry : mapIDs.entrySet()) {
            if (entry.getValue().equals(artistName)) {
                return entry.getKey();
            }
        }
        // Return a special value (e.g., -1) to indicate that the artistName was not found
        return -1;
    }




}
