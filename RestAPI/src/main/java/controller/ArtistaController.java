package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.adapters.LocalDateAdapter;
import model.Artista;
import service.ArtistaService;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.List;

public class ArtistaController {
    private final ArtistaService artistaService;
    private Gson gson;

    public ArtistaController(ArtistaService artistaService, Gson gson) {
        this.artistaService = artistaService;
        this.gson = gson;
    }

    public String getAllArtists (Request request, Response response){
        List<Artista> artistaList = artistaService.getAllArtists();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        response.status(200);
        response.header("Location", "/api/artista/all");
        response.type("text/plain");
        return gson.toJson(artistaList);
    }
}
