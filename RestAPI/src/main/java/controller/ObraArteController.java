package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.adapters.LocalDateAdapter;
import model.Obra_Arte;
import service.ObraArteService;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.List;

public class ObraArteController {
    private final ObraArteService obraArteService;
    private Gson gson;

    public ObraArteController(ObraArteService obraArteService, Gson gson) {
        this.obraArteService = obraArteService;
        this.gson = gson;
    }

    public String getAllObraArte (Request request, Response response){
        List<Obra_Arte> obraArteList = obraArteService.getAllObraArte();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        response.status(200);
        response.header("Location", "/api/obraarte/all");
        response.type("text/plain");
        return gson.toJson(obraArteList);
    }
}
