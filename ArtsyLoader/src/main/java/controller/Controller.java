package controller;


import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import artsymodel.*;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.*;

import restapiservice.RestApiService;

import java.io.IOException;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static apiserviceartsy.ApiServiceArtsy.getArtsyItem;
import static apiserviceartsy.ApiServiceArtsy.getArtsyPartnerByRef;


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

    public static HashMap<ArtsyGene,ArtsyArtwork> matchMapArtsyArtworkGene(List<ArtsyArtwork> artsyArtworksList) {
        HashMap<ArtsyGene,ArtsyArtwork>GeneArtworkArtsyMap = new HashMap<>();
        for (ArtsyArtwork artsyArtwork : artsyArtworksList) {
            try {
                GeneArtworkArtsyMap.put(getArtsyItem(artsyArtwork.getGenesHref(), ArtsyGene.class),artsyArtwork);
            } catch (RuntimeException | IOException ignored) { //ignore and keep iterating
            }
        }
        return GeneArtworkArtsyMap;}

    public static void populateMovimentos(HashMap<ArtsyGene,ArtsyArtwork> GeneArtworkArtsyMap ) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MOVIMENTOS_API_URL
                ,DataProcessor.listProcessor(new ArrayList<>(GeneArtworkArtsyMap.keySet()), GeneConverter.class));
    }

    public static void populateArtistas(HashMap<ArtsyArtist,ArtsyArtwork> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_ARTISTAS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.keySet()), ArtistConverter.class));
    }

    public static void populateObrasArte(HashMap<ArtsyArtist,ArtsyArtwork> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_OBRASARTE_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.values()), ArtworkConverter.class));
    }

    public static void updateArtworksIDs(Connection con, HashMap<ArtsyArtist,ArtsyArtwork> matchMap,
                                               HashMap<ArtsyGene,ArtsyArtwork> matchMapGene, HashMap<ArtsyPartner,ArtsyShow> matchMapPartner) {

        HashMap<Integer,String> artworkIDs = Utils.getAllIdObrasArte(con);

        //algoritmo para ArtistasID
        HashMap<Integer,String> artistIDs = Utils.getAllIdArtistas(con);
        HashMap<Integer,Integer> idMatchUP = new HashMap<>();
        for ( Map.Entry<ArtsyArtist,ArtsyArtwork> entry : matchMap.entrySet()) {
            idMatchUP.put(getMatchId(entry.getKey().getName(),artistIDs),getMatchId(entry.getValue().getTitle(),artworkIDs));
        }
        for ( Map.Entry<Integer,Integer> entry : idMatchUP.entrySet()) {
           Utils.updateObraArteArtistId(entry.getKey(),entry.getValue(),con);
        }
        //algoritmo para Materiais
        HashMap<Integer,Integer> obraMaterialMapString = new HashMap<>();
        HashMap<Integer, String> MateriaisIDs = Utils.getAllIdMateriais(con);
        for ( Map.Entry<ArtsyArtist,ArtsyArtwork> entry : matchMap.entrySet()) {
            obraMaterialMapString.put(getMatchId(entry.getValue().getTitle(),artworkIDs),getMatchId(entry.getValue().getMedium(),MateriaisIDs));
        }
        for ( Map.Entry<Integer,Integer> entry : obraMaterialMapString.entrySet()) {
            Utils.updateObraArteMaterialId(entry.getKey(),entry.getValue(),con);
        }
        //algoritmo para Tecnicas
        HashMap<Integer, String> TecnicasIDs = Utils.getAllIdTecnicas(con);
        HashMap<Integer,Integer> obraTecnicaMapString = new HashMap<>();
        for ( Map.Entry<ArtsyArtist,ArtsyArtwork> entry : matchMap.entrySet()) {
            obraTecnicaMapString.put(getMatchId(entry.getValue().getTitle(),artworkIDs),getMatchId(entry.getValue().getCategory(),TecnicasIDs));
        }
        for ( Map.Entry<Integer,Integer> entry : obraTecnicaMapString.entrySet()) {
            Utils.updateObraArteTecnicaId(entry.getKey(),entry.getValue(),con);
        }
        //algoritmo para Estilo
        HashMap<Integer, String> MovimentosIDs = Utils.getAllIdMovimentos(con);
        HashMap<Integer,Integer> MovimentoObraMap = new HashMap<>();
        for ( Map.Entry<ArtsyGene,ArtsyArtwork> entry : matchMapGene.entrySet()) {
            MovimentoObraMap.put(getMatchId(entry.getKey().getName(),MovimentosIDs),getMatchId(entry.getValue().getTitle(),artworkIDs));
        }
        for ( Map.Entry<Integer,Integer> entry : MovimentoObraMap.entrySet()) {
            Utils.updateObraArteMovimentoId(entry.getValue(),entry.getKey(),con);
        }

        //algoritmo para GaleriaID
        HashMap<Integer,String> EventosIDs = Utils.getAllIdEventos(con);
        HashMap<Integer,String> GaleriasIDs = Utils.getAllIdGalerias(con);
        HashMap<Integer,Integer> idMatchGaleriaEvento = new HashMap<>();

        for ( Map.Entry<ArtsyPartner,ArtsyShow> entry : matchMapPartner.entrySet()) {
            idMatchGaleriaEvento.put(getMatchId(entry.getKey().getName(),GaleriasIDs),getMatchId(entry.getValue().getName(),EventosIDs));
        }
        for ( Map.Entry<Integer,Integer> entry : idMatchGaleriaEvento.entrySet()) {
            Utils.updateEventoGaleriaId(entry.getKey(),entry.getValue(),con);
        }


    }

    public static List<ArtsyShow> LoadArtsyShowsList() throws IOException {
        return ApiServiceArtsy.getAllArtsyItems(ALL_SHOWS_ARTSY_URL, ArtsyShow.class);
    }

    public static HashMap<ArtsyPartner,ArtsyShow> matchMapArtsyShowPartner(List<ArtsyShow> artsyShowsList) {
        HashMap<ArtsyPartner,ArtsyShow> partnerShowArtsyMap = new HashMap<>();
        for (ArtsyShow artsyShow : artsyShowsList) {
            try {
                partnerShowArtsyMap.put(getArtsyPartnerByRef(artsyShow.getPartnerHref()),artsyShow);
            } catch (RuntimeException | IOException ignored) { //ignore and keep iterating
            }
        }
        return partnerShowArtsyMap;}

    public static void populateGalerias(HashMap<ArtsyPartner, ArtsyShow> matchMapShowPartner) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_GALERIAS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMapShowPartner.keySet()), PartnerConverter.class));
    }

    public static void populateEventos(HashMap<ArtsyPartner,ArtsyShow> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_EVENTOS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.values()), ShowConverter.class));
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
