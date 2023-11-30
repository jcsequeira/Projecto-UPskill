package controller;

import com.google.gson.Gson;
import model.Colaborador;


import service.ColaboradorService;
import spark.Request;
import spark.Response;


import java.util.List;

public class ColaboradorController {
    private final ColaboradorService colaboradorService;
    private Gson gson;

    public ColaboradorController(ColaboradorService colaboradorService, Gson gson) {
        this.colaboradorService = colaboradorService;
        this.gson = gson;
    }

    public String getAllColaboradores (Request request, Response response){
        List<Colaborador> colaboradorList = colaboradorService.getAllColaboradores();
        response.status(200);
        response.header("Location", "/api/colaborador/all");
        response.type("text/plain");
        return gson.toJson(colaboradorList);
    }

    public  String getColaboradorById(Request request, Response response) {
        try {
            // Extract the obraArte ID from the request parameters
            int colaboradorId = Integer.parseInt(request.params(":id"));

            // Retrieve the obraArte from the service
            Colaborador colaborador = colaboradorService.getColaboradorById(colaboradorId);

            if (colaborador != null) {
                // Convert the Colaborador object to JSON and return it
                response.status(200);
                response.header("Location", "/api/colaborador");
                response.type("text/plain");
                return gson.toJson(colaborador);
            } else {
                // Set the response status to 404 Not Found if the obraArte is not found
                response.status(404);
                return "Colaborador not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Colaborador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving Colaborador";
        }
    }

    public  String addColaborador(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Colaborador newColaborador = gson.fromJson(request.body(), Colaborador.class);

            //envia o objecto para o service
            Colaborador addedColaborador = colaboradorService.addColaborador(newColaborador);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/colaborador");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedColaborador);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    public String updateColaborador (Request request, Response response) {
        try {
            // Extract the obra arte ID from the request parameters
            int colaboradorId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into Colaborador object
            Colaborador updatedColaborador = gson.fromJson(request.body(), Colaborador.class);

            // Call the service to update the pais
            Colaborador result = colaboradorService.updateColaborador(colaboradorId, updatedColaborador);

            if (result != null) {
                // Convert the updated colaborador object to JSON and return it
                response.status(201);
                response.header("Location", "/api/colaborador");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the pais is not found
                response.status(404);
                return "Colaborador not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Colaborador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating Colaborador";
        }
    }

    public String deleteColaborador(Request request, Response response) {
        try {
            // Extract the Colaborador ID from the request parameters
            int colaboradorId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the Colaborador
            String result = colaboradorService.deleteColaborador(colaboradorId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/colaborador");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid Colaborador ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting Colaborador";
        }
    }
}
