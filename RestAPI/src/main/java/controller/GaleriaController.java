package controller;

import com.google.gson.Gson;

import model.Galeria;
import service.GaleriaService;

import spark.Request;
import spark.Response;


import java.util.List;


public class GaleriaController {

    private final GaleriaService galeriaService;
    private Gson gson;

    public GaleriaController(GaleriaService galeriaService, Gson gson) {
        this.galeriaService = galeriaService;
        this.gson = gson;
    }

    public String getAllGaleria (Request request, Response response){
        List<Galeria> galeriaList = galeriaService.getAllGaleria();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/galeria/all");
        response.type("text/plain");
        return gson.toJson(galeriaList);
    }

    public  String getGaleriaById(Request request, Response response) {
        try {
            // Extract the galeria ID from the request parameters
            int galeriaId = Integer.parseInt(request.params(":id"));

            // Retrieve the galeria from the service
            Galeria galeria = galeriaService.getGaleriaById(galeriaId);

            if (galeria != null) {
                // Convert the galeria object to JSON and return it
                response.status(200);
                response.header("Location", "/api/galeria");
                response.type("text/plain");
                return gson.toJson(galeria);
            } else {
                // Set the response status to 404 Not Found if the galeria is not found
                response.status(404);
                return "Galeria not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid galeria ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving galeria";
        }

    }

    public  String addGaleria(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Galeria newGaleria = gson.fromJson(request.body(), Galeria.class);
            //envia o objecto para o service
            Galeria addedGaleria = galeriaService.addGaleria(newGaleria);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/galeria");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedGaleria);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    public String updateGaleria(Request request, Response response) {
        try {
            // Extract the galeria ID from the request parameters
            int galeriaId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Galeria object
            Galeria updatedGaleria = gson.fromJson(request.body(), Galeria.class);

            // Call the service to update the galeria
            Galeria result = galeriaService.updateGaleria(galeriaId, updatedGaleria);

            if (result != null) {
                // Convert the updated galeria object to JSON and return it
                response.status(201);
                response.header("Location", "/api/galeria");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the galeria is not found
                response.status(404);
                return "Galeria not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid galeria ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating galeria";
        }
    }

    public String deleteGaleria(Request request, Response response) {
        try {
            // Extract the galeria ID from the request parameters
            int galeriaId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the galeria
            String result = galeriaService.deleteGaleria(galeriaId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/galeria");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid galeria ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting galeria";
        }
    }


}
