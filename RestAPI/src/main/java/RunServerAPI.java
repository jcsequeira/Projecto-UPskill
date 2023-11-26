
import com.google.gson.Gson;
import controller.*;
import repository.*;
import service.*;
import spark.Spark;

import static spark.Spark.*;

public class RunServerAPI {

    public static void main(String[] args) {
        //Test EndPoint
        get("/hello", (req, res) -> "Hello World");

        //Artista CRUD Operations Endpoins
        Spark.get("/api/artista/all",artistaInit()::getAllArtists);





    }


    public static ArtistaController artistaInit() {
        ArtistRepository artistRepository = new ArtistRepository(DBConnection.getConnection());
        ArtistaService artistaService = new ArtistaService(artistRepository);
        Gson gson = new Gson();
        return new ArtistaController(artistaService, gson);}


}
