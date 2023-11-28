package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.adapters.LocalDateAdapter;
import model.Obra_Arte;
import model.Pais;
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

    public  String getObraArteById(Request request, Response response) {
        try {
            // Extract the obraArte ID from the request parameters
            int obraArteId = Integer.parseInt(request.params(":id"));

            // Retrieve the obraArte from the service
            Obra_Arte obraArte = obraArteService.getObraArteById(obraArteId);
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            if (obraArte != null) {
                // Convert the obraArte object to JSON and return it
                response.status(200);
                response.header("Location", "/api/obraarte");
                response.type("text/plain");
                return gson.toJson(obraArte);
            } else {
                // Set the response status to 404 Not Found if the obraArte is not found
                response.status(404);
                return "ObraArte not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid ObraArte ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving ObraArte";
        }
    }
}
