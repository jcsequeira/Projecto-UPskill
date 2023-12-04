package controller;


import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.ArtistConverter;
import dataprocessorservice.DataProcessor;
import restapiservice.RestApiService;

import java.io.IOException;


public class Controller {

    private static final String ALL_ARTIST_API_URL = "https://api.artsy.net/api/artists?artworks=true&sort=-trending&size=1500&page=1";

    private static final String CSV_CITYS_FILE_PATH = "ArtsyLoader/src/main/resources/citysPT.csv";




    /*metodos para implementar:
    -Populate Cidade
    -Populate Pais->
    -Populate Movimento (da pa tirar pelo gene)
    -LoadArtworksJsonList
    -Populate Tecnica (este precisa da Lista de obras de arte carregada do artsy)
    -Populate Materiais (este precisa da Lista de obras de arte carregada do artsy)
    -Populate Artista (precisa do Pais)--Converter esta com placeholder po pais
    -Populate ObraArte (precsa artista, tecnica,materiais, movimento)
    -Populate Galeria
    -Populate Evento
     */
    public static void populateCidades() throws IOException, CsvException {
        RestApiService.postAllCidadesToRestApi(DataProcessor.cidadeListGenerator(Utils.createCityMap(Utils.readCityNamesFromCsv(CSV_CITYS_FILE_PATH))));
    }

    public static void populateArtistas() throws IOException {
        RestApiService.postAllArtistasToRestApi(DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyArtists(ALL_ARTIST_API_URL), ArtistConverter.class));
    }


}
