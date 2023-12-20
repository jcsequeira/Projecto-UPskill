package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Movimento;
import service.MovimentoService;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The MovimentoController class handles HTTP requests related to the Movimento resource.
 */
public class MovimentoController {
    private final MovimentoService movimentoService;
    private Gson gson;

    /**
     * Constructs a new MovimentoController with the specified services.
     *
     * @param movimentoService The service for managing Movimento data.
     * @param gson             The Gson library for JSON serialization and deserialization.
     */
    public MovimentoController(MovimentoService movimentoService, Gson gson) {
        this.movimentoService = movimentoService;
        this.gson = gson;
    }

    /**
     * Handles the HTTP GET request to retrieve all Movimento resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the list of Movimento resources.
     */
    public String getAllMovimentos (Request request, Response response){
        List<Movimento> movimentosList = movimentoService.getAllMovimentos();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/movimentos");
        response.type("text/plain");
        return gson.toJson(movimentosList);
    }

    /**
     * Handles the HTTP GET request to retrieve a specific Movimento resource by ID.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the Movimento resource if found; otherwise, an error message.
     */
    public  String getMovimentoById(Request request, Response response) {
        try {
            // Extract the movimento ID from the request parameters
            int movimentoId = Integer.parseInt(request.params(":id"));

            // Retrieve the movimento from the service
            Movimento movimento = movimentoService.getMovimentoById(movimentoId);

            if (movimento != null) {
                // Convert the movimento object to JSON and return it
                response.status(200);
                response.header("Location", "/api/movimentos");
                response.type("text/plain");
                return gson.toJson(movimento);
            } else {
                // Set the response status to 404 Not Found if the movimento is not found
                response.status(404);
                return "Movimento not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid movimento ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving movimento";
        }
    }

    /**
     * Handles the HTTP POST request to add a new Movimento resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the added Movimento resource.
     */
    public  String addMovimento(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Movimento newMovimento = gson.fromJson(request.body(), Movimento.class);
            //envia o objecto para o service
            Movimento addedMovimento = movimentoService.addMovimento(newMovimento);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/movimentos");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedMovimento);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing Movimento resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the updated Movimento resource.
     */
    public String updateMovimento(Request request, Response response) {
        try {
            // Extract the movimento ID from the request parameters
            int movimentoId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Movimento object
            Movimento updatedMovimento = gson.fromJson(request.body(), Movimento.class);

            // Call the service to update the movimento
            Movimento result = movimentoService.updateMovimento(movimentoId, updatedMovimento);

            if (result != null) {
                // Convert the updated movimento object to JSON and return it
                response.status(201);
                response.header("Location", "/api/movimentos");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the movimento is not found
                response.status(404);
                return "Movimento not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid movimento ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating movimento";
        }
    }

    /**
     * Handles the HTTP DELETE request to delete an existing Movimento resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the deletion of the Movimento resource.
     */
    public String deleteMovimento(Request request, Response response) {
        try {
            // Extract the movimento ID from the request parameters
            int movimentoId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the movimento
            String result = movimentoService.deleteMovimento(movimentoId);
            // Set the response status to 200 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/movimentos");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Movimento ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Movimento";
        }
    }

    /**
     * Handles the HTTP POST request to add multiple Movimento resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the creation of multiple Movimento resources.
     */
    public  String addAllMovimentos(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Type listType = new TypeToken<ArrayList<Movimento>>(){}.getType();
            List<Movimento> movimentosList = gson.fromJson(request.body(), listType);
            //envia o objecto para o service
            movimentoService.addAllMovimentos(movimentosList);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/populate/movimentos");
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
