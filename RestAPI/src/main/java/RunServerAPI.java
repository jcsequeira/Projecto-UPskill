
import com.google.gson.Gson;
import controller.*;
import repository.*;
import service.*;
import spark.Spark;

import static spark.Spark.*;

public class RunServerAPI {

    public static void main(String[] args) {
        //endpoint de teste
        get("/hello", (req, res) -> "Hello World");

        //endpoints Artista
        ArtistaController artistaInit = artistaInit();
        get("/api/artista/all", (req, res) -> artistaInit.getAllArtists());
        //TBD getbyID






    }

    //carrega recursos para os endpoints do artista
    public static ArtistaController artistaInit() {
        ArtistRepository artistRepository = new ArtistRepository(DBConnection.getConnection());
        ArtistaService artistaService = new ArtistaService(artistRepository);
        Gson gson = new Gson();
        return new ArtistaController(artistaService, gson);}


}
