package controller;

import com.google.gson.Gson;
import model.Administrador;


import service.AdministradorService;
import spark.Request;
import spark.Response;


import java.util.List;

public class AdministradorController {
    private final AdministradorService administradorService;
    private Gson gson;

    public AdministradorController(AdministradorService administradorService, Gson gson) {
        this.administradorService = administradorService;
        this.gson = gson;
    }

    public String getAllAdministrador (Request request, Response response){
        List<Administrador> administradorList = administradorService.getAllAdministrador();
        response.status(200);
        response.header("Location", "/api/administradores");
        response.type("text/plain");
        return gson.toJson(administradorList);
    }

    public  String getAdministradorById(Request request, Response response) {
        try {
            // Extract the obraArte ID from the request parameters
            int administradorId = Integer.parseInt(request.params(":id"));

            // Retrieve the obraArte from the service
            Administrador administrador = administradorService.getAdministradorById(administradorId);

            if (administrador != null) {
                // Convert the Administrador object to JSON and return it
                response.status(200);
                response.header("Location", "/api/administradores");
                response.type("text/plain");
                return gson.toJson(administrador);
            } else {
                // Set the response status to 404 Not Found if the obraArte is not found
                response.status(404);
                return "Administrador not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Administrador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving Administrador";
        }
    }

    public  String addAdministrador(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Administrador newAdministrador = gson.fromJson(request.body(), Administrador.class);

            //envia o objecto para o service
            Administrador addedAdministrador = administradorService.addAdministrador(newAdministrador);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/administradores");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedAdministrador);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    public String updateAdministrador (Request request, Response response) {
        try {
            // Extract the obra arte ID from the request parameters
            int administradorId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into Administrador object
            Administrador updatedAdministrador = gson.fromJson(request.body(), Administrador.class);

            // Call the service to update the pais
            Administrador result = administradorService.updateAdministrador(administradorId, updatedAdministrador);

            if (result != null) {
                // Convert the updated administrador object to JSON and return it
                response.status(201);
                response.header("Location", "/api/administradores");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the pais is not found
                response.status(404);
                return "Administrador not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Administrador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating Administrador";
        }
    }

    public String deleteAdministrador(Request request, Response response) {
        try {
            // Extract the Administrador ID from the request parameters
            int administradorId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the Administrador
            String result = administradorService.deleteAdministrador(administradorId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/administradores");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Administrador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Administrador";
        }
    }
}
