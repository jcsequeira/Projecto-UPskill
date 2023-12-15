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

import java.sql.SQLException;
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

    public static HashMap<ArtsyArtwork,ArtsyArtist> matchMapArtsyArtworkArtist(List<ArtsyArtwork> artsyArtworksList) {
        HashMap<ArtsyArtwork,ArtsyArtist> artistArtworkArtsyMap = new HashMap<>();
        for (ArtsyArtwork artsyArtwork : artsyArtworksList) {
            try {
                artistArtworkArtsyMap.put(artsyArtwork,getArtsyItem(artsyArtwork.getArtistsHref(), ArtsyArtist.class));
            } catch (RuntimeException | IOException ignored) { //ignore and keep iterating
            }
        }
    return artistArtworkArtsyMap;}

    public static HashMap<ArtsyArtwork, ArtsyGene> matchMapArtsyArtworkGene(List<ArtsyArtwork> artsyArtworksList) {
        HashMap<ArtsyArtwork, ArtsyGene> GeneArtworkArtsyMap = new HashMap<>();
        for (ArtsyArtwork artsyArtwork : artsyArtworksList) {
            try {
                GeneArtworkArtsyMap.put(artsyArtwork, getArtsyItem(artsyArtwork.getGenesHref(), ArtsyGene.class));
            } catch (RuntimeException | IOException ignored) { //ignore and keep iterating
            }
        }
        return GeneArtworkArtsyMap;}

    public static void populateMovimentos(HashMap<ArtsyArtwork, ArtsyGene> GeneArtworkArtsyMap ) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_MOVIMENTOS_API_URL
                ,DataProcessor.listProcessor(new ArrayList<>(GeneArtworkArtsyMap.values()), GeneConverter.class));
    }

    public static void populateArtistas(HashMap<ArtsyArtwork,ArtsyArtist> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_ARTISTAS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.values()), ArtistConverter.class));
    }

    public static void populateObrasArte(HashMap<ArtsyArtwork,ArtsyArtist> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_OBRASARTE_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.keySet()), ArtworkConverter.class));
    }

    public static void updateArtworksIDs(Connection con, HashMap<ArtsyArtwork,ArtsyArtist> matchMap,
                                               HashMap<ArtsyArtwork,ArtsyGene> matchMapGene, HashMap<ArtsyShow,ArtsyPartner> matchMapPartner) {


        HashMap<Integer,String> artworkIDs = Utils.getAllIdObrasArte(con);
        try {
            System.out.println("Update Artistas Id na tabela Obras de Arte: ");
            //algoritmo para ArtistasID
            HashMap<Integer, String> artistIDs = Utils.getAllIdArtistas(con);
            HashMap<Integer, Integer> idMatchUP = new HashMap<>();
            for (Map.Entry<ArtsyArtwork,ArtsyArtist> entry : matchMap.entrySet()) {
                idMatchUP.put(getMatchId(entry.getKey().getTitle(), artworkIDs),getMatchId(entry.getValue().getName(), artistIDs));
            }
            for (Map.Entry<Integer, Integer> entry : idMatchUP.entrySet()) {
                Utils.updateObraArteArtistId(entry.getKey(), entry.getValue(), con);
            }
            System.out.println("Update com sucesso!");
        } catch (ServiceException e) {
            System.out.println("Erro ao atualizar Artistas Id na tabela Obras de Arte: " + e.getMessage());
        }

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
            System.out.println(MovimentoObraMap);
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

    public static List<ArtsyShow> LoadArtsyShowsList() throws IOException {
        return ApiServiceArtsy.getAllArtsyItems(ALL_SHOWS_ARTSY_URL, ArtsyShow.class);
    }

    public static HashMap<ArtsyShow,ArtsyPartner> matchMapArtsyShowPartner(List<ArtsyShow> artsyShowsList) {
        HashMap<ArtsyShow,ArtsyPartner> partnerShowArtsyMap = new HashMap<>();
        for (ArtsyShow artsyShow : artsyShowsList) {
            try {
                partnerShowArtsyMap.put(artsyShow,getArtsyPartnerByRef(artsyShow.getPartnerHref()));
            } catch (RuntimeException | IOException ignored) { //ignore and keep iterating
            }
        }
        return partnerShowArtsyMap;}

    public static void populateGalerias(HashMap<ArtsyShow,ArtsyPartner> matchMapShowPartner) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_GALERIAS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMapShowPartner.values()), PartnerConverter.class));
    }

    public static void populateEventos(HashMap<ArtsyShow,ArtsyPartner> matchMap) throws IOException {
        RestApiService.postToRestApi(REST_ENDPOINT_EVENTOS_API_URL,
                DataProcessor.listProcessor(new ArrayList<>(matchMap.keySet()), ShowConverter.class));
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
