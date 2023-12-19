package populate;

import Utils.DBConnection;
import Utils.Utils;
import artsymodel.*;
import com.opencsv.exceptions.CsvException;
import exceptions.ServiceException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static controller.Controller.*;
/**
 * The PopulateModel class represents the background task logic for populating the database with data from Artsy.
 * It includes methods to perform various steps of the data population process, such as fetching data from Artsy,
 * matching and processing the data, and updating the database. The class also manages the progress of the background task.
 */
public class PopulateModel {
    /** The current progress of the background task, ranging from 0.0 to 1.0. */
    public double progress;
    /**
     * Runs the background task to populate the database with data from Artsy. The task includes multiple steps,
     * such as populating cities, countries, movements, artworks, materials, techniques, artists, genres, galleries,
     * shows, and updating artwork-related IDs in the database.
     *
     * @throws InterruptedException If the background task is interrupted.
     * @throws IOException          If an I/O error occurs during the background task.
     * @throws CsvException         If an exception related to CSV processing occurs.
     */
    public void runBackgroundTask() throws InterruptedException, IOException, CsvException {
        populateCidades();
        updateProgress(0.15);

        populatePaises();
        updateProgress(0.20);

        populateMovimentos();
        Thread.sleep(5000);
        updateProgress(0.25);

        //Carrega uma lista de Obras de arte do artsy
        List<ArtsyArtwork> artsyArtworkList = LoadArtsyArtworksList();
        updateProgress(0.30);
        artsyArtworkList = Utils.removeDuplicatesArtworksArtsy(artsyArtworkList);
        updateProgress(0.35);
        Thread.sleep(2000);


        //dessa lista popula materiais e tecnicas

        populateTecnicas(artsyArtworkList);
        updateProgress(0.40);
        populateMateriais(artsyArtworkList);
        updateProgress(0.45);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um artista obrigatoriamente
        HashMap<ArtsyArtwork, ArtsyArtist> matchMapArtist = matchMapArtsyArtworkArtist(artsyArtworkList);
        updateProgress(0.50);
        Thread.sleep(2000);

        //a partir da lista anterior cria um mapa que assegura que cada obra tem um genero obrigatoriamente
        HashMap<ArtsyArtwork, ArtsyGene> matchMapGene = matchMapArtsyArtworkGene(matchMapArtist);
        updateProgress(0.55);
        Thread.sleep(2000);


        populateArtistas(matchMapArtist);
        Thread.sleep(2000);
        updateProgress(0.60);


        populateMovimentos(matchMapGene);
        Thread.sleep(2000);
        updateProgress(0.65);



        populateObrasArte(matchMapArtist);
        updateProgress(0.70);

        populateGalerias();
        updateProgress(0.75);

        List<ArtsyShow> artsyShowsList = loadArtsyShowsList();
        updateProgress(0.80);
        Thread.sleep(2000);

        HashMap<ArtsyShow, ArtsyPartner> matchMapShowPartner = matchMapArtsyShowPartner(artsyShowsList);
        updateProgress(0.85);
        Thread.sleep(2000);


        populateGalerias(matchMapShowPartner);
        updateProgress(0.90);

        populateEventos(matchMapShowPartner);
        updateProgress(0.95);


        try {
            updateArtworksIDs(DBConnection.getConnection(), matchMapArtist, matchMapGene,matchMapShowPartner);
            updateProgress(1);
            System.out.println("Update Finalizado!");

        } catch (Exception e) {
            throw new ServiceException("Erro: " +e);
        }
    }
    /**
     * Sets the progress value for the background task.
     *
     * @param progress The progress value, ranging from 0.0 to 1.0.
     */
    public void setProgress(double progress) {
        this.progress = progress;
    }
    /**
     * Updates the progress of the background task, clamping the value between 0.0 and 1.0.
     * If the progress is very close to 1.0, it is set to exactly 1.0.
     *
     * @param v The progress value to be updated.
     */
    public void updateProgress(double v) {
        // Clamp the progress value between 0.0 and 1.0
        double clampedProgress = Math.max(0.0, Math.min(v, 1.0));

        // If the progress is very close to 1.0, set it to exactly 1.0
        if (clampedProgress >= 0.95) {
            clampedProgress = 1.0;
        }

        setProgress(clampedProgress);
    }

    /**
     * Retrieves the current progress of the background task.
     *
     * @return The current progress value, ranging from 0.0 to 1.0.
     */
    public double getProgress() {
        return progress;
    }
}

