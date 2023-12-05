package controller;


import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import artsymodel.ArtsyArtist;
import artsymodel.ArtsyArtwork;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.*;
import restapiservice.RestApiService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class Controller {
    private static final String CSV_CITYS_FILE_PATH = "ArtsyLoader/src/main/resources/citysPT.csv";
    private static final String CSV_COUNTRYS_NACIONALITYS_FILE_PATH = "ArtsyLoader/src/main/resources/countries.csv";
    private static final String ALL_ARTIST_ARTSY_URL = "https://api.artsy.net/api/artists?artworks=true&sort=-trending&size=1500&page=1";
    private static final String ALL_MOVIMENTOS_ARTSY_URL = "https://api.artsy.net/api/genes?size=1000";
    private static final String ALL_ARTWORKS_ARTSY_URL = "https://api.artsy.net/api/artworks?size=1000&page=1";
    private static final String REST_ENDPOINT_CIDADES_API_URL="http://localhost:4567/populate/cidades";
    private static final String REST_ENDPOINT_PAISES_API_URL="http://localhost:4567/populate/paises";
    private static final String REST_ENDPOINT_ARTISTAS_API_URL="http://localhost:4567/populate/artistas";
    private static final String REST_ENDPOINT_MOVIMENTOS_API_URL="http://localhost:4567/populate/movimentos";
    private static final String REST_ENDPOINT_TECNICAS_API_URL="http://localhost:4567/populate/tecnicas";
    private static final String REST_ENDPOINT_MATERIAIS_API_URL="http://localhost:4567/populate/materiais";




    /*metodos para implementar:

    -Populate Materiais (este precisa da Lista de obras de arte carregada do artsy)
    -Populate ObraArte (precsa artista, tecnica,materiais, movimento)
    -Populate Galeria
    -Populate Evento
     */
    public static void populateCidades() throws IOException, CsvException {
        RestApiService.postToRestApi(REST_ENDPOINT_CIDADES_API_URL
                ,Utils.cidadeListGenerator(CSV_CITYS_FILE_PATH));
    }
    public static void populatePaises() throws IOException, CsvException {
        RestApiService.postToRestApi(REST_ENDPOINT_PAISES_API_URL
                ,Utils.paisesListGenerator(CSV_COUNTRYS_NACIONALITYS_FILE_PATH,3,4));
    }
    public static void populateArtistas() throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_ARTISTAS_API_URL
                ,DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyArtists(ALL_ARTIST_ARTSY_URL), ArtistConverter.class));
    }
    public static void populateMovimentos() throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MOVIMENTOS_API_URL
                ,DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyGenes(ALL_MOVIMENTOS_ARTSY_URL), GeneConverter.class));
    }

    public static List<ArtsyArtwork> LoadArtsyArtworksList() throws IOException {
       return ApiServiceArtsy.getAllArtsyArtworks(ALL_ARTWORKS_ARTSY_URL);
    }

    public static void populateTecnicas(List<ArtsyArtwork> allArtsyArtworksList) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_TECNICAS_API_URL
                , Utils.removeNullsAndDuplicatesTecnicas((DataProcessor.listProcessor(allArtsyArtworksList, CategoryConverter.class))));
    }

    public static void populateMateriais(List<ArtsyArtwork> allArtsyArtworksList) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MATERIAIS_API_URL
                , Utils.removeNullsAndDuplicatesMateriais((DataProcessor.listProcessor(allArtsyArtworksList, MediumConverter.class))));
    }








}
