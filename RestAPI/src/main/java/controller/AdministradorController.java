package controller;

import com.google.gson.Gson;
import model.Administrador;
import service.AdministradorService;
import spark.Request;
import spark.Response;

import java.util.List;

/**
 * The AdministradorController class handles HTTP requests related to the Administrador resource.
 */
public class AdministradorController {
    private final AdministradorService administradorService;
    private final Gson gson;

    /**
     * Constructs a new AdministradorController with the specified services.
     *
     * @param administradorService The service for managing Administrador data.
     * @param gson                The Gson library for JSON serialization and deserialization.
     */
    public AdministradorController(AdministradorService administradorService, Gson gson) {
        this.administradorService = administradorService;
        this.gson = gson;
    }

    /**
     * Handles the HTTP GET request to retrieve all Administrador resources.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the list of Administrador resources.
     */
    public String getAllAdministrador(Request request, Response response) {
        List<Administrador> administradorList = administradorService.getAllAdministrador();
        response.status(200);
        response.header("Location", "/api/administradores");
        response.type("text/plain");
        return gson.toJson(administradorList);
    }

    /**
     * Handles the HTTP GET request to retrieve a specific Administrador resource by ID.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A JSON representation of the Administrador resource if found; otherwise, an error message.
     */
    public String getAdministradorById(Request request, Response response) {
        try {
            int administradorId = Integer.parseInt(request.params(":id"));
            Administrador administrador = administradorService.getAdministradorById(administradorId);

            if (administrador != null) {
                response.status(200);
                response.header("Location", "/api/administradores");
                response.type("text/plain");
                return gson.toJson(administrador);
            } else {
                response.status(404);
                return "Administrador not found";
            }
        } catch (NumberFormatException e) {
            response.status(400);
            return "Invalid Administrador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500);
            return "Error retrieving Administrador";
        }
    }

    /**
     * Handles the HTTP POST request to add a new Administrador resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the added Administrador resource.
     */
    public String addAdministrador(Request request, Response response) {
        try {
            Administrador newAdministrador = gson.fromJson(request.body(), Administrador.class);
            Administrador addedAdministrador = administradorService.addAdministrador(newAdministrador);

            response.status(201);
            response.header("Location", "/api/administradores");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedAdministrador);
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    /**
     * Handles the HTTP PUT request to update an existing Administrador resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message with the JSON representation of the updated Administrador resource.
     */
    public String updateAdministrador(Request request, Response response) {
        try {
            int administradorId = Integer.parseInt(request.params(":id"));
            Administrador updatedAdministrador = gson.fromJson(request.body(), Administrador.class);
            Administrador result = administradorService.updateAdministrador(administradorId, updatedAdministrador);

            if (result != null) {
                response.status(201);
                response.header("Location", "/api/administradores");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                response.status(404);
                return "Administrador not found";
            }
        } catch (NumberFormatException e) {
            response.status(400);
            return "Invalid Administrador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500);
            return "Error updating Administrador";
        }
    }

    /**
     * Handles the HTTP DELETE request to delete an existing Administrador resource.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @return A success message indicating the deletion of the Administrador resource.
     */
    public String deleteAdministrador(Request request, Response response) {
        try {
            int administradorId = Integer.parseInt(request.params(":id"));
            String result = administradorService.deleteAdministrador(administradorId);

            response.status(200);
            response.header("Location", "/api/administradores");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            response.status(400);
            return "Invalid Administrador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            response.status(500);
            return "Error deleting Administrador";
        }
    }
}
