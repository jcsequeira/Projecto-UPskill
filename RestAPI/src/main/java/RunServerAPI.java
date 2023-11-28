
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

        //Artista.java CRUD Operations Endpoins
        Spark.get("/api/artista/all",artistaInit()::getAllArtists);




        //Pais CRUD Operations Endpoins
        Spark.get("/api/pais/all",paisInit()::getAllPais);
        Spark.get("/api/pais/:id",paisInit()::getPaisById);
        Spark.post("/api/pais", paisInit()::addPais);
        Spark.put("/api/pais/:id", paisInit()::updatePais);
        Spark.delete("/api/pais/:id", paisInit()::deletePais);


        //Obra_Arte CRUD Operations Endpoins
        Spark.get("/api/obraarte/all",obraArteInit()::getAllObraArte);
        Spark.get("/api/obraarte/:id",obraArteInit()::getObraArteById);
        //Spark.post("/api/obraarte", obraArteInit()::addObraArte);
        //Spark.put("/api/obraarte/:id", obraArteInit()::updateObraArte);
        //Spark.delete("/api/obraarte/:id", obraArteInit()::deleteObraArte);


    }

    //Methods to Initialize All Services
    public static ArtistaController artistaInit() {
        ArtistRepository artistRepository = new ArtistRepository(DBConnection.getConnection());
        ArtistaService artistaService = new ArtistaService(artistRepository);
        Gson gson = new Gson();
        return new ArtistaController(artistaService, gson);}

    public static PaisController paisInit() {
        PaisRepository paisRepository = new PaisRepository(DBConnection.getConnection());
        PaisService paisService = new PaisService(paisRepository);
        Gson gson = new Gson();
        return new PaisController(paisService, gson);}

    public static ObraArteController obraArteInit() {
        ObraArteRepository obraArteRepository = new ObraArteRepository(DBConnection.getConnection());
        ObraArteService obraArteService = new ObraArteService(obraArteRepository);
        Gson gson = new Gson();
        return new ObraArteController(obraArteService, gson);}


}
