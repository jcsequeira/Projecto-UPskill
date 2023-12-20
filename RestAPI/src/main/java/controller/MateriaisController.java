package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Materiais;
import service.MateriaisService;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The MateriaisController class handles HTTP requests related to the Materiais resource.
 */
public class MateriaisController {
    private final MateriaisService materiaisService;
    private Gson gson;

    /**
     * Constructs a new MateriaisController with the specified services.
     *
     * @param materiaisService The service for managing Materiais data.
     * @param gson             The Gson library for JSON serialization and deserialization.
     */
    public MateriaisController(MateriaisService materiaisService, Gson gson) {
        this.materiaisService = materiaisService;
        this.gson = gson;
    }

    /**
     * Handles the HTTP GET request to retrieve all Materiais resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the list of Materiais resources.
     */
    public String getAllMateriais (Request request, Response response){
        List<Materiais> materiaisList = materiaisService.getAllMateriais();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/materiais");
        response.type("text/plain");
        return gson.toJson(materiaisList);
    }

    /**
     * Handles the HTTP GET request to retrieve a specific Materiais resource by ID.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the Materiais resource if found; otherwise, an error message.
     */
    public  String getMaterialById(Request request, Response response) {
        try {
            // Extract the material ID from the request parameters
            int materialId = Integer.parseInt(request.params(":id"));

            // Retrieve the material from the service
            Materiais material = materiaisService.getMaterialById(materialId);

            if (material != null) {
                // Convert the material object to JSON and return it
                response.status(200);
                response.header("Location", "/api/materiais");
                response.type("text/plain");
                return gson.toJson(material);
            } else {
                // Set the response status to 404 Not Found if the material is not found
                response.status(404);
                return "Material not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Material ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving Material";
        }
    }

    /**
     * Handles the HTTP POST request to add a new Materiais resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the added Materiais resource.
     */
    public  String addMaterial(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Materiais newMaterial = gson.fromJson(request.body(), Materiais.class);
            //envia o objecto para o service
            Materiais addedMaterial = materiaisService.addMaterial(newMaterial);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/materiais");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedMaterial);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing Materiais resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the updated Materiais resource.
     */
    public String updateMaterial(Request request, Response response) {
        try {
            // Extract the Material ID from the request parameters
            int materialId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Material object
            Materiais updatedMaterial = gson.fromJson(request.body(), Materiais.class);

            // Call the service to update the movimento
            Materiais result = materiaisService.updateMaterial(materialId, updatedMaterial);

            if (result != null) {
                // Convert the updated Material object to JSON and return it
                response.status(201);
                response.header("Location", "/api/materiais");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the Material is not found
                response.status(404);
                return "Material not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Material ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating Material";
        }
    }

    /**
     * Handles the HTTP DELETE request to delete an existing Materiais resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the deletion of the Materiais resource.
     */
    public String deleteMaterial(Request request, Response response) {
        try {
            // Extract the Material ID from the request parameters
            int materialId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the Material
            String result = materiaisService.deleteMaterial(materialId);
            // Set the response status to 200 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/materiais");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Material ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Material";
        }
    }

    /**
     * Handles the HTTP POST request to add multiple Materiais resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the creation of multiple Materiais resources.
     */
    public  String addAllMateriais(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Type listType = new TypeToken<ArrayList<Materiais>>(){}.getType();
            List<Materiais> materiaisList = gson.fromJson(request.body(), listType);
            //envia o objecto para o service
            materiaisService.addAllMateriais(materiaisList);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/populate/materiais");
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
