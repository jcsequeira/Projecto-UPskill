package populate;

import Utils.*;
import artsymodel.*;
import com.opencsv.exceptions.CsvException;
import model.ExplorArtModel;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.HashMap;
import java.util.List;

import static controller.Controller.*;

public class PopulateRun {

    public static void doPopulate(ExplorArtModel model) throws IOException, CsvException, InterruptedException {


        populateCidades();
        model.updateProgress(0.15);

        populatePaises();
        model.updateProgress(0.20);

        populateMovimentos();
        Thread.sleep(5000);
        model.updateProgress(0.25);

        //Carrega uma lista de Obras de arte do artsy
        List<ArtsyArtwork> artsyArtworkList = LoadArtsyArtworksList();
        model.updateProgress(0.30);
        artsyArtworkList = Utils.removeDuplicatesArtworksArtsy(artsyArtworkList);
        model.updateProgress(0.35);
        Thread.sleep(2000);


        //dessa lista popula materiais e tecnicas

        populateTecnicas(artsyArtworkList);
        model.updateProgress(0.40);
        populateMateriais(artsyArtworkList);
        model.updateProgress(0.45);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um artista obrigatoriamente
        HashMap<ArtsyArtwork, ArtsyArtist> matchMapArtist = matchMapArtsyArtworkArtist(artsyArtworkList);
        model.updateProgress(0.50);
        Thread.sleep(2000);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um genero obrigatoriamente
        HashMap<ArtsyArtwork, ArtsyGene> matchMapGene = matchMapArtsyArtworkGene(matchMapArtist);
        model.updateProgress(0.55);
        Thread.sleep(2000);


        populateArtistas(matchMapArtist);
        Thread.sleep(2000);
        model.updateProgress(0.60);


        populateMovimentos(matchMapGene);
        Thread.sleep(2000);
        model.updateProgress(0.65);



        populateObrasArte(matchMapArtist);
        model.updateProgress(0.70);

        populateGalerias();
        model.updateProgress(0.75);

        List<ArtsyShow> artsyShowsList = LoadArtsyShowsList();
        model.updateProgress(0.80);
        Thread.sleep(2000);

        HashMap<ArtsyShow, ArtsyPartner> matchMapShowPartner = matchMapArtsyShowPartner(artsyShowsList);
        model.updateProgress(0.85);
        Thread.sleep(2000);


        populateGalerias(matchMapShowPartner);
        model.updateProgress(0.90);

        populateEventos(matchMapShowPartner);
        model.updateProgress(0.95);





        try {
            updateArtworksIDs(DBConnection.getConnection(), matchMapArtist, matchMapGene,matchMapShowPartner);
            model.updateProgress(1);
        } catch (Exception e) {
            throw new ServerException("Erro: " +e);
        }


    }


}


