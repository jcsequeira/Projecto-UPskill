package controller;


import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.ArtistConverter;
import dataprocessorservice.DataProcessor;
import dataprocessorservice.GeneConverter;
import restapiservice.RestApiService;

import java.io.IOException;


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



    /*metodos para implementar:
    -LoadArtworksJsonList
    -Populate Tecnica (este precisa da Lista de obras de arte carregada do artsy)
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









}
