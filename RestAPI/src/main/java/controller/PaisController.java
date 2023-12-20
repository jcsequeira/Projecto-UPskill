package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Pais;
import service.PaisService;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The PaisController class handles HTTP requests related to the Pais resource.
 */
public class PaisController {
    private final PaisService paisService;
    private Gson gson;

    /**
     * Constructs a new PaisController with the specified services.
     *
     * @param paisService The service for managing Pais data.
     * @param gson        The Gson library for JSON serialization and deserialization.
     */
    public PaisController(PaisService paisService, Gson gson) {
        this.paisService = paisService;
        this.gson = gson;
    }

    /**
     * Handles the HTTP GET request to retrieve all Pais resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the list of Pais resources.
     */
    public String getAllPais (Request request, Response response){
        List<Pais> paisList = paisService.getAllPais();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/paises");
        response.type("text/plain");
        return gson.toJson(paisList);
    }

    /**
     * Handles the HTTP GET request to retrieve a specific Pais resource by ID.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the Pais resource if found; otherwise, an error message.
     */
    public  String getPaisById(Request request, Response response) {
        try {
            // Extract the pais ID from the request parameters
            int paisId = Integer.parseInt(request.params(":id"));

            // Retrieve the pais from the service
            Pais pais = paisService.getPaisById(paisId);

            if (pais != null) {
                // Convert the pais object to JSON and return it
                response.status(200);
                response.header("Location", "/api/paises");
                response.type("text/plain");
                return gson.toJson(pais);
            } else {
                // Set the response status to 404 Not Found if the pais is not found
                response.status(404);
                return "Pais not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid pais ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving pais";
        }
    }

    /**
     * Handles the HTTP POST request to add a new Pais resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the added Pais resource.
     */
    public  String addPais(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Pais newPais = gson.fromJson(request.body(), Pais.class);
            //envia o objecto para o service
            Pais addedPais = paisService.addPais(newPais);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/paises");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedPais);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing Pais resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the updated Pais resource.
     */
    public String updatePais(Request request, Response response) {
        try {
            // Extract the pais ID from the request parameters
            int paisId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Pais object
            Pais updatedPais = gson.fromJson(request.body(), Pais.class);

            // Call the service to update the pais
            Pais result = paisService.updatePais(paisId, updatedPais);

            if (result != null) {
                // Convert the updated pais object to JSON and return it
                response.status(201);
                response.header("Location", "/api/paises");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the pais is not found
                response.status(404);
                return "Pais not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid pais ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating pais";
        }
    }

    /**
     * Handles the HTTP DELETE request to delete an existing Pais resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the deletion of the Pais resource.
     */
    public String deletePais(Request request, Response response) {
        try {
            // Extract the pais ID from the request parameters
            int paisId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the pais
            paisService.deletePais(paisId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(204);
            response.header("Location", "/api/paises");
            response.type("text/plain");
            return "";
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid pais ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting pais";
        }
    }

    /**
     * Handles the HTTP POST request to add multiple Pais resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the creation of multiple Pais resources.
     */
    public  String addAllPaises(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Type listType = new TypeToken<ArrayList<Pais>>(){}.getType();
            List<Pais> paisesList = gson.fromJson(request.body(), listType);
            //envia o objecto para o service
            paisService.addAllPaises(paisesList);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/populate/paiss");
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
