package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Obra_Arte;
import service.ObraArteService;
import spark.Request;
import spark.Response;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ObraArteController class handles HTTP requests related to the Obra Arte resource.
 */
public class ObraArteController {
    private final ObraArteService obraArteService;
    private final Gson gson;

    /**
     * Constructs a new ObraArteController with the specified services.
     *
     * @param obraArteService The service for managing Obra Arte data.
     * @param gson            The Gson library for JSON serialization and deserialization.
     */
    public ObraArteController(ObraArteService obraArteService, Gson gson) {
        this.obraArteService = obraArteService;
        this.gson = gson;
    }

    /**
     * Handles the HTTP GET request to retrieve all Obra Arte resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the list of Obra Arte resources.
     */
    public String getAllObraArte (Request request, Response response){
        List<Obra_Arte> obraArteList = obraArteService.getAllObraArte();
        response.status(200);
        response.header("Location", "/api/obrasarte");
        response.type("text/plain");
        return gson.toJson(obraArteList);
    }

    /**
     * Handles the HTTP GET request to retrieve a specific Obra Arte resource by ID.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the Obra Arte resource if found; otherwise, an error message.
     * @throws SQLException If a SQL exception occurs.
     */
    public  String getObraArteById(Request request, Response response) throws SQLException {
       // try {
            // Extract the obraArte ID from the request parameters
            int obraArteId = Integer.parseInt(request.params(":id"));

            // Retrieve the obraArte from the service
            Obra_Arte obraArte = obraArteService.getObraArteById(obraArteId);

            if (obraArte != null) {
                // Convert the obraArte object to JSON and return it
                response.status(200);
                response.header("Location", "/api/obrasarte");
                response.type("text/plain");
                return gson.toJson(obraArte);
            } else {
                // Set the response status to 404 Not Found if the obraArte is not found
                response.status(404);
                return "ObraArte not found";
            }
    }

    /**
     * Handles the HTTP POST request to add a new Obra Arte resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the added Obra Arte resource.
     */
    public  String addObraArte(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Obra_Arte newObra_Arte = gson.fromJson(request.body(), Obra_Arte.class);
            //envia o objecto para o service
            Obra_Arte addedObra_Arte = obraArteService.addObraArte(newObra_Arte);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/obrasarte");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedObra_Arte);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing Obra Arte resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the updated Obra Arte resource.
     */
    public String updateObraArte(Request request, Response response) {
        try {
            // Extract the obra arte ID from the request parameters
            int obraArteId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into Obra Arte object
            Obra_Arte updatedObraArte = gson.fromJson(request.body(), Obra_Arte.class);

            // Call the service to update the pais
            Obra_Arte result = obraArteService.updateObraArte(obraArteId, updatedObraArte);

            if (result != null) {
                // Convert the updated obra arte object to JSON and return it
                response.status(201);
                response.header("Location", "/api/obrasarte");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the pais is not found
                response.status(404);
                return "Obra Arte not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Obra Arte ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating Obra Arte";
        }
    }

    /**
     * Handles the HTTP DELETE request to delete an existing Obra Arte resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the deletion of the Obra Arte resource.
     */
    public String deleteObraArte(Request request, Response response) {
        try {
            // Extract the obra arte ID from the request parameters
            int obraArteId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the obra arte
            String result = obraArteService.deleteObraArte(obraArteId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/obrasarte");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Obra Arte ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Obra Arte";
        }
    }

    /**
     * Handles the HTTP POST request to add multiple Obra Arte resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the creation of multiple Obra Arte resources.
     */
    public  String addAllObrasArte(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Type listType = new TypeToken<ArrayList<Obra_Arte>>(){}.getType();
            List<Obra_Arte> obrasArteList = gson.fromJson(request.body(), listType);
            //envia o objecto para o service
            obraArteService.addAllObrasArte(obrasArteList);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/populate/obrasarte");
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
