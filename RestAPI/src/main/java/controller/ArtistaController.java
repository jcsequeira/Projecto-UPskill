package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Artista;
import service.ArtistaService;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The ArtistaController class handles HTTP requests related to the Artista resource.
 */
public class ArtistaController {
    private final ArtistaService artistaService;
    private final Gson gson;

    /**
     * Constructs a new ArtistaController with the specified services.
     *
     * @param artistaService The service for managing Artista data.
     * @param gson           The Gson library for JSON serialization and deserialization.
     */
    public ArtistaController(ArtistaService artistaService, Gson gson) {
        this.artistaService = artistaService;
        this.gson = gson;
    }

    /**
     * Handles the HTTP GET request to retrieve all Artista resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the list of Artista resources.
     */
    public String getAllArtistas (Request request, Response response){
        List<Artista> artistaList = artistaService.getAllArtists();
        response.status(200);
        response.header("Location", "/api/artistas");
        response.type("text/plain");
        return gson.toJson(artistaList);
    }

    /**
     * Handles the HTTP GET request to retrieve a specific Artista resource by ID.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the Artista resource if found; otherwise, an error message.
     */
    public  String getArtistaById(Request request, Response response) {
        try {
            // Extract the obraArte ID from the request parameters
            int artistaId = Integer.parseInt(request.params(":id"));

            // Retrieve the obraArte from the service
            Artista artista = artistaService.getArtistaById(artistaId);

            if (artista != null) {
                // Convert the Artista object to JSON and return it
                response.status(200);
                response.header("Location", "/api/artistas");
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

    /**
     * Handles the HTTP POST request to add a new Artista resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the added Artista resource.
     */
    public  String addArtista(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Artista newArtista = gson.fromJson(request.body(), Artista.class);

            //envia o objecto para o service
            Artista addedArtista = artistaService.addArtista(newArtista);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/artistas");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedArtista);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing Artista resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the updated Artista resource.
     */
    public String updateArtista (Request request, Response response) {
        try {
            // Extract the obra arte ID from the request parameters
            int artistaId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into Artista object
            Artista updatedArtista = gson.fromJson(request.body(), Artista.class);

            // Call the service to update the pais
            Artista result = artistaService.updateArtista(artistaId, updatedArtista);

            if (result != null) {
                // Convert the updated artista object to JSON and return it
                response.status(201);
                response.header("Location", "/api/artistas");
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

    /**
     * Handles the HTTP DELETE request to delete an existing Artista resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the deletion of the Artista resource.
     */
    public String deleteArtista(Request request, Response response) {
        try {
            // Extract the Artista ID from the request parameters
            int artistaId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the Artista
            String result = artistaService.deleteArtista(artistaId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/artistas");
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

    /**
     * Handles the HTTP POST request to add a list of Artista resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the creation of Artista resources.
     */
    public  String addAllArtistas(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Type listType = new TypeToken<ArrayList<Artista>>(){}.getType();
            List<Artista> artistasList = gson.fromJson(request.body(), listType);
            //envia o objecto para o service
            artistaService.addAllArtistas(artistasList);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/populate/artistas");
            response.type("text/plain");
            return "Resources created successfully.";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resources.";
        }
    }
}
