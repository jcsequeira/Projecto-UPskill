package controller;

import com.google.gson.Gson;
import model.Cidade;
import service.CidadeService;
import spark.Request;
import spark.Response;


import java.util.List;


public class CidadeController {

    private final CidadeService cidadeService;
    private Gson gson;

    public CidadeController(CidadeService cidadeService, Gson gson) {
        this.cidadeService = cidadeService;
        this.gson = gson;
    }

    public String getAllCidade (Request request, Response response){
        List<Cidade> cidadeList = cidadeService.getAllCidade();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/cidades");
        response.type("text/plain");
        return gson.toJson(cidadeList);
    }

    public  String getCidadeById(Request request, Response response) {
        try {
            // Extract the cidade ID from the request parameters
            int cidadeId = Integer.parseInt(request.params(":id"));

            // Retrieve the cidade from the service
            Cidade cidade = cidadeService.getCidadeById(cidadeId);

            if (cidade != null) {
                // Convert the cidade object to JSON and return it
                response.status(200);
                response.header("Location", "/api/cidades");
                response.type("text/plain");
                return gson.toJson(cidade);
            } else {
                // Set the response status to 404 Not Found if the cidade is not found
                response.status(404);
                return "Cidade not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid cidade ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving cidade";
        }

    }

    public  String addCidade(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Cidade newCidade = gson.fromJson(request.body(), Cidade.class);
            //envia o objecto para o service
            Cidade addedCidade = cidadeService.addCidade(newCidade);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/cidades");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedCidade);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Error Adding Resource.";
        }
    }

    public String updateCidade(Request request, Response response) {
        try {
            // Extract the cidade ID from the request parameters
            int cidadeId = Integer.parseInt(request.params(":id"));

            // Parse the JSON data from the request body into a Cidade object
            Cidade updatedCidade = gson.fromJson(request.body(), Cidade.class);

            // Call the service to update the cidade
            Cidade result = cidadeService.updateCidade(cidadeId, updatedCidade);

            if (result != null) {
                // Convert the updated cidade object to JSON and return it
                response.status(201);
                response.header("Location", "/api/cidades");
                response.type("text/plain");
                return "Resource Updated successfully.: \n" + gson.toJson(result);
            } else {
                // Set the response status to 404 Not Found if the cidade is not found
                response.status(404);
                return "Cidade not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid cidade ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error updating cidade";
        }
    }

    public String deleteCidade(Request request, Response response) {
        try {
            // Extract the cidade ID from the request parameters
            int cidadeId = Integer.parseInt(request.params(":id"));
            // Call the service to delete the cidade
            String result = cidadeService.deleteCidade(cidadeId);
            // Set the response status to 204 No Content, indicating a successful deletion
            response.status(200);
            response.header("Location", "/api/cidades");
            response.type("text/plain");
            return result;
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid cidade ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error deleting cidade";
        }
    }


}
