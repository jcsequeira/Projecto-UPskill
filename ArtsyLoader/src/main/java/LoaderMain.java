import Utils.*;

import artsymodel.*;

import com.opencsv.exceptions.CsvException;


import java.io.IOException;
import java.rmi.ServerException;
import java.util.*;


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
        artsyArtworkList = Utils.removeDuplicatesArtworksArtsy(artsyArtworkList);
        Thread.sleep(2000);


        //dessa lista popula materiais e tecnicas
        System.out.println("Tecnicas: ");
        populateTecnicas(artsyArtworkList);
        System.out.println("Materiais: ");
        populateMateriais(artsyArtworkList);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um artista obrigatoriamente
        HashMap<ArtsyArtwork, ArtsyArtist> matchMapArtist = matchMapArtsyArtworkArtist(artsyArtworkList);
        Thread.sleep(2000);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um genero obrigatoriamente
        HashMap<ArtsyArtwork, ArtsyGene> matchMapGene = matchMapArtsyArtworkGene(matchMapArtist);
        Thread.sleep(2000);

        System.out.println("Artistas: ");
        populateArtistas(matchMapArtist);
        Thread.sleep(2000);

        System.out.println("Movimentos 2: ");
        populateMovimentos(matchMapGene);
        Thread.sleep(2000);


        System.out.println("ObrasArte: ");
        populateObrasArte(matchMapArtist);

        System.out.println("Galerias: ");
        populateGalerias();

        List<ArtsyShow> artsyShowsList = LoadArtsyShowsList();
        Thread.sleep(2000);

        HashMap<ArtsyShow, ArtsyPartner> matchMapShowPartner = matchMapArtsyShowPartner(artsyShowsList);
        Thread.sleep(2000);

        System.out.println("Galerias 2: ");
        populateGalerias(matchMapShowPartner);

        System.out.println("Eventos: ");
        populateEventos(matchMapShowPartner);




        System.out.println("Update 1: Match Making IDs ");
        try {
            updateArtworksIDs(DBConnection.getConnection(), matchMapArtist, matchMapGene,matchMapShowPartner);
            System.out.println("Atualizado com Sucesso!");
        } catch (Exception e) {
            throw new ServerException("Erro: " +e);
        }





    }




}






