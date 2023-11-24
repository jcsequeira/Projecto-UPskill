package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.adapters.LocalDateAdapter;
import model.Artista;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import service.ArtistaService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArtistaController {
    private final ArtistaService artistaService;
    private Gson gson;

    public ArtistaController(ArtistaService artistaService, Gson gson) {
        this.artistaService = artistaService;
        this.gson = gson;
    }

    public String getAllArtists (){
        List<Artista> artistaList = artistaService.getAllArtists();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        return gson.toJson(artistaList);
    }
}