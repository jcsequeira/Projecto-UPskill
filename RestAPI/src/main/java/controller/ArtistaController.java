package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.adapters.LocalDateAdapter;
import model.Artista;

import model.Obra_Arte;
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

    public String getAllArtistas (Request request, Response response){
        List<Artista> artistaList = artistaService.getAllArtists();
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        response.status(200);
        response.header("Location", "/api/artista/all");
        response.type("text/plain");
        return gson.toJson(artistaList);
    }

    public  String getArtistaById(Request request, Response response) {
        try {
            // Extract the obraArte ID from the request parameters
            int artistaId = Integer.parseInt(request.params(":id"));

            // Retrieve the obraArte from the service
            Artista artista = artistaService.getArtistaById(artistaId);
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            if (artista != null) {
                // Convert the Artista object to JSON and return it
                response.status(200);
                response.header("Location", "/api/artista");
                response.type("text/plain");
                return gson.toJson(artista);
            } else {
                // Set the response status to 404 Not Found if the obraArte is not found
                response.status(404);
                return "Artista not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Artista ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving Artista";
        }
    }

    public  String addArtista(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            Artista newArtista = gson.fromJson(request.body(), Artista.class);

            //envia o objecto para o service
            Artista addedArtista = artistaService.addArtista(newArtista);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/artista");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedArtista);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    public String updateArtista (Request request, Response response) {
        try {
            // Extract the obra arte ID from the request parameters
            int artistaId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into Artista object
            gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();
            Artista updatedArtista = gson.fromJson(request.body(), Artista.class);

            // Call the service to update the pais
            Artista result = artistaService.updateArtista(artistaId, updatedArtista);

            if (result != null) {
                // Convert the updated artista object to JSON and return it
                response.status(201);
                response.header("Location", "/api/artista");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the pais is not found
                response.status(404);
                return "Artista not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Artista ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating Artista";
        }
    }

    public String deleteArtista(Request request, Response response) {
        try {
            // Extract the Artista ID from the request parameters
            int artistaId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the Artista
            String result = artistaService.deleteArtista(artistaId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/artista");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Artista ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Artista";
        }
    }
}
