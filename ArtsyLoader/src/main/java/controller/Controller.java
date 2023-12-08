package controller;


import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import artsymodel.*;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.*;
import model.Artista;
import restapiservice.RestApiService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static apiserviceartsy.ApiServiceArtsy.getArtsyItem;


public class Controller {
    private static final String CSV_CITYS_FILE_PATH = "ArtsyLoader/src/main/resources/citysPT.csv";
    private static final String CSV_COUNTRYS_NACIONALITYS_FILE_PATH = "ArtsyLoader/src/main/resources/countries.csv";
    private static final String ALL_ARTIST_ARTSY_URL = "https://api.artsy.net/api/artists?artworks=true&sort=-trending&size=1000&page=1";
    private static final String ALL_MOVIMENTOS_ARTSY_URL = "https://api.artsy.net/api/genes?size=1000";
    private static final String ALL_ARTWORKS_ARTSY_URL = "https://api.artsy.net/api/artworks?size=100&page=1";
    private static final String ALL_PARTNERS_ARTSY_URL = "https://api.artsy.net/api/partners?size=500";
    private static final String ALL_SHOWS_ARTSY_URL =  "https://api.artsy.net/api/shows?status=upcoming";
    private static final String REST_ENDPOINT_CIDADES_API_URL="http://localhost:4567/populate/cidades";
    private static final String REST_ENDPOINT_PAISES_API_URL="http://localhost:4567/populate/paises";
    public static final String REST_ENDPOINT_ARTISTAS_API_URL="http://localhost:4567/populate/artistas";
    private static final String REST_ENDPOINT_MOVIMENTOS_API_URL="http://localhost:4567/populate/movimentos";
    private static final String REST_ENDPOINT_TECNICAS_API_URL="http://localhost:4567/populate/tecnicas";
    private static final String REST_ENDPOINT_MATERIAIS_API_URL="http://localhost:4567/populate/materiais";
    private static final String REST_ENDPOINT_OBRASARTE_API_URL="http://localhost:4567/populate/obrasarte";
    private static final String REST_ENDPOINT_GALERIAS_API_URL = "http://localhost:4567/populate/galerias";
    private static final String REST_ENDPOINT_EVENTOS_API_URL = "http://localhost:4567/populate/eventos";





    public static void populateCidades() throws IOException, CsvException {
        RestApiService.postToRestApi(REST_ENDPOINT_CIDADES_API_URL
                ,Utils.cidadeListGenerator(CSV_CITYS_FILE_PATH));
    }
    public static void populatePaises() throws IOException, CsvException {
        RestApiService.postToRestApi(REST_ENDPOINT_PAISES_API_URL
                ,Utils.paisesListGenerator(CSV_COUNTRYS_NACIONALITYS_FILE_PATH,3,4));
    }

    public static void populateMovimentos() throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MOVIMENTOS_API_URL
                ,DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyItems(ALL_MOVIMENTOS_ARTSY_URL, ArtsyGene.class), GeneConverter.class));
    }

    public static void populateGalerias() throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_GALERIAS_API_URL
                ,DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyItems(ALL_PARTNERS_ARTSY_URL, ArtsyPartner.class), PartnerConverter.class));
    }

    public static void populateEventos() throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_EVENTOS_API_URL
                ,DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyItems(ALL_SHOWS_ARTSY_URL, ArtsyShow.class), ShowConverter.class));
    }

    public static List<ArtsyArtwork> LoadArtsyArtworksList() throws IOException {
        return ApiServiceArtsy.getAllArtsyItems(ALL_ARTWORKS_ARTSY_URL, ArtsyArtwork.class);
    }

    public static void populateTecnicas(List<ArtsyArtwork> allArtsyArtworksList) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_TECNICAS_API_URL
                , Utils.removeNullsAndDuplicatesTecnicas((DataProcessor.listProcessor(allArtsyArtworksList, CategoryConverter.class))));
    }

    public static void populateMateriais(List<ArtsyArtwork> allArtsyArtworksList) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MATERIAIS_API_URL
                , Utils.removeNullsAndDuplicatesMateriais((DataProcessor.listProcessor(allArtsyArtworksList, MediumConverter.class))));
    }

    public static HashMap<ArtsyArtist,ArtsyArtwork> matchMapArtsyArtworkArtist(List<ArtsyArtwork> artsyArtworksList) {
        HashMap<ArtsyArtist,ArtsyArtwork> artistArtworkArtsyMap = new HashMap<>();
        for (ArtsyArtwork artsyArtwork : artsyArtworksList) {
            try {
                artistArtworkArtsyMap.put(getArtsyItem(artsyArtwork.getArtistsHref(), ArtsyArtist.class),artsyArtwork);
            } catch (RuntimeException | IOException ignored) { //ignore and keep iterating
            }
        }
    return artistArtworkArtsyMap;}

    public static void populateArtistas(HashMap<ArtsyArtist,ArtsyArtwork> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_ARTISTAS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.keySet()), ArtistConverter.class));
    }

    public static void populateObrasArte(HashMap<ArtsyArtist,ArtsyArtwork> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_OBRASARTE_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.values()), ArtworkConverter.class));
    }

    public static void updateArtworksMagicNumbers(Connection con, HashMap<ArtsyArtist,ArtsyArtwork> matchMap) {
        HashMap<Integer,String> artistIDs = Utils.getAllIdArtistas(con);
        HashMap<Integer,String> artworkIDs = Utils.getAllIdObrasArte(con);
        HashMap<Integer,Integer> idMatchUP = new HashMap<>();

        for ( Map.Entry<ArtsyArtist,ArtsyArtwork> entry : matchMap.entrySet()) {
            idMatchUP.put(getMatchId(entry.getKey().getName(),artistIDs),getMatchId(entry.getValue().getTitle(),artworkIDs));
        }
        for ( Map.Entry<Integer,Integer> entry : idMatchUP.entrySet()) {
           Utils.updateObraArteArtistId(entry.getKey(),entry.getValue(),con);
        }

    }


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
