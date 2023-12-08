import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import artsymodel.ArtsyArtist;
import artsymodel.ArtsyArtwork;
import artsymodel.ArtsyShow;
import com.google.gson.Gson;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.ArtistConverter;
import dataprocessorservice.DataProcessor;
import dataprocessorservice.ShowConverter;
import model.Artista;
import model.Evento;
import model.Pais;
import restapiservice.RestApiService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static apiserviceartsy.ApiServiceArtsy.getAllArtsyItems;
import static apiserviceartsy.ApiServiceArtsy.getArtsyItem;
import static controller.Controller.*;



public class LoaderMain {



    public static void main(String[] args) throws IOException, CsvException, InterruptedException {


        System.out.println("Cidades: ");
         populateCidades();
        System.out.println("Paises: ");
         populatePaises();

        System.out.println("Movimentos: ");
        populateMovimentos();
        Thread.sleep(5000);

        //Carrega uma lista de Obras de arte do artsy
        List<ArtsyArtwork> artsyArtworkList = LoadArtsyArtworksList();
        Thread.sleep(2000);


        //dessa lista popula materiais e tecnicas
        System.out.println("Tecnicas: ");
        populateTecnicas(artsyArtworkList);
        System.out.println("Materiais: ");
        populateMateriais(artsyArtworkList);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um artista obrigatoriamente
        HashMap<ArtsyArtist,ArtsyArtwork> matchMap = matchMapArtsyArtworkArtist(artsyArtworkList);
        Thread.sleep(2000);

        System.out.println("Artistas: ");
        populateArtistas(matchMap);
        Thread.sleep(5000);

        System.out.println("ObrasArte: ");
        populateObrasArte(matchMap);

        System.out.println("Galerias: ");
        populateGalerias();
        System.out.println("Eventos: ");
        populateEventos();




        /*
        String ALL_ARTWORKS_ARTSY_URL = "https://api.artsy.net/api/artworks?size=1000&page=1";

        List<ArtsyArtwork> allArtsyArtworks= ApiServiceArtsy.getAllArtsyArtworks(ALL_ARTWORKS_ARTSY_URL);

        for (ArtsyArtwork artwork : allArtsyArtworks) {
            System.out.println("index: " + allArtsyArtworks.indexOf(artwork));
            System.out.println("Title: " + artwork.getTitle());
            System.out.println("Category: " + artwork.getCategory());
            System.out.println("Medium: " + artwork.getMedium());
            System.out.println("Date: " + artwork.getDate());
            System.out.println("Height: " + artwork.getHeight());
            System.out.println("Width: " + artwork.getWidth());
            System.out.println("Depth: " + artwork.getDepth());
            System.out.println("Diameter: " + artwork.getDiameter());
            System.out.println("Thumbnail Href: " + artwork.getThumbnailHref());
            System.out.println("Genes Href: " + artwork.getGenesHref());
            System.out.println("Artists Href: " + artwork.getArtistsHref());
            System.out.println();
        }*/




    }



}






