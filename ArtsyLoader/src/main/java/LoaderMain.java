import Utils.Utils;
import apiserviceartsy.ApiServiceArtsy;
import artsymodel.ArtsyArtwork;
import com.google.gson.Gson;
import com.opencsv.exceptions.CsvException;
import dataprocessorservice.DataProcessor;
import model.Pais;

import java.io.IOException;
import java.util.List;

import static controller.Controller.*;



public class LoaderMain {



    public static void main(String[] args) throws IOException, CsvException {

        /*
         populateCidades();
         populatePaises();
         populateMovimentos();*/

        //populateArtistas();
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

        //List<ArtsyArtwork> artsyArtworkList = LoadArtsyArtworksList();
        //populateTecnicas(artsyArtworkList);
        //populateMateriais(artsyArtworkList);


    }



}






