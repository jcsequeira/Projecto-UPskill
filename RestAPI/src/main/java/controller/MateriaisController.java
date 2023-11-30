package controller;

import com.google.gson.Gson;
import model.Materiais;
import service.MateriaisService;
import spark.Request;
import spark.Response;

import java.util.List;

public class MateriaisController {
    
    private final MateriaisService materiaisService;
    
    private Gson gson;

    public MateriaisController(MateriaisService materiaisService, Gson gson) {
        this.materiaisService = materiaisService;
        this.gson = gson;
    }

    public String getAllMateriais (Request request, Response response){
        List<Materiais> materiaisList = materiaisService.getAllMateriais();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/materiais/all");
        response.type("text/plain");
        return gson.toJson(materiaisList);
    }

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

    public String updateMaterial(Request request, Response response) {
        try {
            // Extract the Material ID from the request parameters
            int materialId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Material object
            Materiais updatedMaterial = gson.fromJson(request.body(), Materiais.class);

            // Call the service to update the movimento
            Materiais result = materiaisService.upddateMaterial(materialId, updatedMaterial);

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
}