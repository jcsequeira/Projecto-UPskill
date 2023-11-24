
import com.google.gson.Gson;
import controller.ArtistaController;
import repository.ArtistRepository;
import repository.DBConnection;
import service.ArtistaService;
import spark.Spark;

import static spark.Spark.*;

public class RunServerAPI {

    public static void main(String[] args) {

        ArtistRepository artistRepository = new ArtistRepository(DBConnection.getConnection());
        ArtistaService artistaService = new ArtistaService(artistRepository);
        Gson gson = new Gson();
        ArtistaController artistaController = new ArtistaController(artistaService, gson);


        get("/hello", (req, res) -> "Hello World");

        get("/API/artista/all", (req, res) -> artistaController.getAllArtists());

    }


}
