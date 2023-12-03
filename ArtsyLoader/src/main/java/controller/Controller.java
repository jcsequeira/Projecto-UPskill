package controller;


import apiserviceartsy.ApiServiceArtsy;
import dataprocessorservice.ArtistConverter;
import dataprocessorservice.DataProcessor;
import restapiservice.RestApiService;

import java.io.IOException;


public class Controller {

    private static final String ALL_ARTIST_API_URL = "https://api.artsy.net/api/artists?artworks=true&sort=-trending&size=1500&page=1";




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

    public static void populateArtistas() throws IOException {
        RestApiService.postAllArtistasToRestApi(DataProcessor.listProcessor(ApiServiceArtsy.getAllArtsyArtists(ALL_ARTIST_API_URL), ArtistConverter.class));
    }
}
