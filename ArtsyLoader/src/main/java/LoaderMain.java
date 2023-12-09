import Utils.*;

import artsymodel.ArtsyArtist;
import artsymodel.ArtsyArtwork;

import artsymodel.ArtsyGene;
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
        Thread.sleep(2000);


        //dessa lista popula materiais e tecnicas
        System.out.println("Tecnicas: ");
        populateTecnicas(artsyArtworkList);
        System.out.println("Materiais: ");
        populateMateriais(artsyArtworkList);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um artista obrigatoriamente
        HashMap<ArtsyArtist,ArtsyArtwork> matchMapArtist = matchMapArtsyArtworkArtist(artsyArtworkList);
        Thread.sleep(2000);
        //a partir da lista anterior cria um mapa que assegura que cada obra tem um genero obrigatoriamente
        HashMap<ArtsyGene,ArtsyArtwork> matchMapGene = matchMapArtsyArtworkGene(artsyArtworkList);
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
        System.out.println("Eventos: ");
        populateEventos();


        System.out.println("Update 1: Match Making IDs ");
        try {
            updateArtworksIDs(DBConnection.getConnection(), matchMapArtist, matchMapGene);
            System.out.println("Atualizado com Sucesso!");
        } catch (Exception e) {
            throw new ServerException("Erro: " +e);
        }










    }



}






