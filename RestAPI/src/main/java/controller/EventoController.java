package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Evento;
import service.EventoService;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The EventoController class handles HTTP requests related to the Evento resource.
 */
public class EventoController {

    private final EventoService eventoService;
    private final Gson gson;

    /**
     * Constructs a new EventoController with the specified services.
     *
     * @param eventoService The service for managing Evento data.
     * @param gson          The Gson library for JSON serialization and deserialization.
     */
    public EventoController(EventoService eventoService, Gson gson) {
        this.eventoService = eventoService;
        this.gson = gson;
    }

    /**
     * Handles the HTTP GET request to retrieve all Evento resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the list of Evento resources.
     */
    public String getAllEvento (Request request, Response response){
        List<Evento> eventoList = eventoService.getAllEvento();

        response.status(200);
        response.header("Location", "/api/eventos");
        response.type("text/plain");
        return gson.toJson(eventoList);
    }

    /**
     * Handles the HTTP GET request to retrieve a specific Evento resource by ID.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the Evento resource if found; otherwise, an error message.
     */
    public  String getEventoById(Request request, Response response) {
        try {
            // Extract the evento ID from the request parameters
            int eventoId = Integer.parseInt(request.params(":id"));

            // Retrieve the evento from the service
            Evento evento = eventoService.getEventoById(eventoId);

            if (evento != null) {
                // Convert the evento object to JSON and return it
                response.status(200);
                response.header("Location", "/api/eventos");
                response.type("text/plain");
                return gson.toJson(evento);
            } else {
                // Set the response status to 404 Not Found if the evento is not found
                response.status(404);
                return "Evento not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid evento ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving evento";
        }
    }

    /**
     * Handles the HTTP POST request to add a new Evento resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the added Evento resource.
     */
    public  String addEvento(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Evento newEvento = gson.fromJson(request.body(), Evento.class);
            //envia o objecto para o service
            Evento addedEvento = eventoService.addEvento(newEvento);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/eventos");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedEvento);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing Evento resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the updated Evento resource.
     */
    public String updateEvento(Request request, Response response) {
        try {
            // Extract the evento ID from the request parameters
            int eventoId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Evento object
            Evento updatedEvento = gson.fromJson(request.body(), Evento.class);

            // Call the service to update the evento
            Evento result = eventoService.updateEvento(eventoId, updatedEvento);

            if (result != null) {
                // Convert the updated evento object to JSON and return it
                response.status(201);
                response.header("Location", "/api/eventos");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the evento is not found
                response.status(404);
                return "Evento not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid evento ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating evento";
        }
    }

    /**
     * Handles the HTTP DELETE request to delete an existing Evento resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the deletion of the Evento resource.
     */
    public String deleteEvento(Request request, Response response) {
        try {
            // Extract the evento ID from the request parameters
            int eventoId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the evento
            String result = eventoService.deleteEvento(eventoId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/eventos");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid evento ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting evento";
        }
    }

    /**
     * Handles the HTTP POST request to add multiple Evento resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the creation of multiple Evento resources.
     */
    public  String addAllEventos(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Type listType = new TypeToken<ArrayList<Evento>>(){}.getType();
            List<Evento> eventosList = gson.fromJson(request.body(), listType);
            //envia o objecto para o service
            eventoService.addAllEventos(eventosList);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/populate/eventos");
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
