package controller;

import com.google.gson.Gson;
import model.Galerista;
import service.GaleristaService;
import spark.Request;
import spark.Response;

import java.util.List;

public class GaleristaController {
    private final GaleristaService galeristaService;
    private Gson gson;

    public GaleristaController(GaleristaService galeristaService, Gson gson) {
        this.galeristaService = galeristaService;
        this.gson = gson;
    }

    public String getAllGaleristas(Request request, Response response) {
        List<Galerista> galeristasList = galeristaService.getAllGaleristas();
        response.status(200);
        response.header("Location", "/api/galeristas");
        response.type("text/plain");
        return gson.toJson(galeristasList);
    }

    public String getGaleristaById(Request request, Response response) {
        try {
            // Extract the galerista ID from the request parameters
            int galeristaId = Integer.parseInt(request.params(":id"));

            // Retrieve the galerista from the service
            Galerista galerista = galeristaService.getGaleristaById(galeristaId);

            if (galerista != null) {
                // Convert the galerista object to JSON and return it
                response.status(200);
                response.header("Location", "/api/galeristas");
                response.type("text/plain");
                return gson.toJson(galerista);
            } else {
                // Set the response status to 404 Not Found if the galerista is not found
                response.status(404);
                return "Galerista not found";
            }

        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Galerista ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving Galerista";
        }
    }

    public String addGalerista(Request request, Response response) {
        try {
            // Deserialization of JSON to an object
            Galerista newGalerista = gson.fromJson(request.body(), Galerista.class);
            // Send the object to the service
            Galerista addedGalerista = galeristaService.addGalerista(newGalerista);
            // Response and Status 201: success in post
            response.status(201);
            response.header("Location", "/api/galeristas");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedGalerista);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    public String updateGalerista(Request request, Response response) {
        try {
            // Extract the galerista ID from the request parameters
            int galeristaId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into Galerista object
            Galerista updatedGalerista = gson.fromJson(request.body(), Galerista.class);

            // Call the service to update the galerista
            Galerista result = galeristaService.updateGalerista(galeristaId, updatedGalerista);

            if (result != null) {
                // Convert the updated galerista object to JSON and return it
                response.status(201);
                response.header("Location", "/api/galeristas");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the galerista is not found
                response.status(404);
                return "Galerista not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Galerista ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating Galerista";
        }
    }

    public String deleteGalerista(Request request, Response response) {
        try {
            // Extract the galerista ID from the request parameters
            int galeristaId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the galerista
            String result = galeristaService.deleteGalerista(galeristaId);
            // Set the response status to 200 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/galeristas");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Galerista ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Galerista";
        }
    }
}
