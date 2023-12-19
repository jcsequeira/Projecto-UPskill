package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Tecnica;
import service.TecnicaService;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TecnicaController {
    private final TecnicaService tecnicaService;
    private Gson gson;

    public TecnicaController(TecnicaService tecnicaService, Gson gson) {
        this.tecnicaService = tecnicaService;
        this.gson = gson;
    }

    public String getAllTecnicas(Request request, Response response) {
        List<Tecnica> tecnicasList = tecnicaService.getAllTecnicas();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/tecnicas");
        response.type("text/plain");
        return gson.toJson(tecnicasList);
    }

    public String getTecnicaById(Request request, Response response) {
        try {
            // Extract the tecnica ID from the request parameters
            int tecnicaId = Integer.parseInt(request.params(":id"));

            // Retrieve the tecnica from the service
            Tecnica tecnica = tecnicaService.getTecnicaById(tecnicaId);

            if (tecnica != null) {
                // Convert the tecnica object to JSON and return it
                response.status(200);
                response.header("Location", "/api/tecnicas");
                response.type("text/plain");
                return gson.toJson(tecnica);
            } else {
                // Set the response status to 404 Not Found if the tecnica is not found
                response.status(404);
                return "Tecnica not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Tecnica ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving Tecnica";
        }
    }

    public String addTecnica(Request request, Response response) {
        try {
            // Deserialization of the JSON to an object
            Tecnica newTecnica = gson.fromJson(request.body(), Tecnica.class);

            // Send the object to the service
            Tecnica addedTecnica = tecnicaService.addTecnica(newTecnica);

            // Response and Status 201: success in the post
            response.status(201);
            response.header("Location", "/api/tecnicas");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedTecnica);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    public String updateTecnica(Request request, Response response) {
        try {
            // Extract the tecnica ID from the request parameters
            int tecnicaId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Tecnica object
            Tecnica updatedTecnica = gson.fromJson(request.body(), Tecnica.class);

            // Call the service to update the tecnica
            Tecnica result = tecnicaService.updateTecnica(tecnicaId, updatedTecnica);

            if (result != null) {
                // Convert the updated tecnica object to JSON and return it
                response.status(201);
                response.header("Location", "/api/tecnicas");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the tecnica is not found
                response.status(404);
                return "Tecnica not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid tecnica ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating tecnica";
        }
    }

    public String deleteTecnica(Request request, Response response) {
        try {
            // Extract the tecnica ID from the request parameters
            int tecnicaId = Integer.parseInt(request.params(":id"));

            // Call the service to delete the tecnica
            String result = tecnicaService.deleteTecnica(tecnicaId);

            // Set the response status to 200 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/tecnicas");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Tecnica ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Tecnica";
        }
    }

    public  String addAllTecnicas(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Type listType = new TypeToken<ArrayList<Tecnica>>(){}.getType();
            List<Tecnica> tecnicasList = gson.fromJson(request.body(), listType);
            //envia o objecto para o service
            tecnicaService.addAllTecnicas(tecnicasList);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/populate/tecnicas");
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
