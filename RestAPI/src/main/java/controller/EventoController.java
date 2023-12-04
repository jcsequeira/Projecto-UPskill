package controller;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import controller.adapters.LocalDateAdapter;
import model.Artista;
import model.Evento;
import service.EventoService;

import spark.Request;
import spark.Response;


import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class EventoController {

    private final EventoService eventoService;
    private Gson gson;

    public EventoController(EventoService eventoService, Gson gson) {
        this.eventoService = eventoService;
        this.gson = gson;
    }

    public String getAllEvento (Request request, Response response){
        List<Evento> eventoList = eventoService.getAllEvento();

        response.status(200);
        response.header("Location", "/api/eventos");
        response.type("text/plain");
        return gson.toJson(eventoList);
    }

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
