package controller;

import com.google.gson.Gson;
import model.Pais;
import service.PaisService;
import spark.Request;
import spark.Response;


import java.util.List;


public class PaisController {

    private final PaisService paisService;
    private Gson gson;

    public PaisController(PaisService paisService, Gson gson) {
        this.paisService = paisService;
        this.gson = gson;
    }

    public String getAllPais (Request request, Response response){
        List<Pais> paisList = paisService.getAllPais();
        gson = new Gson();
        response.status(200);
        response.header("Location", "/api/pais/all");
        response.type("text/plain");
        return gson.toJson(paisList);
    }

    public  String getPaisById(Request request, Response response) {
        try {
            // Extract the pais ID from the request parameters
            int paisId = Integer.parseInt(request.params(":id"));

            // Retrieve the pais from the service
            Pais pais = paisService.getPaisById(paisId);

            if (pais != null) {
                // Convert the pais object to JSON and return it
                response.status(200);
                response.header("Location", "/api/pais");
                response.type("text/plain");
                return gson.toJson(pais);
            } else {
                // Set the response status to 404 Not Found if the pais is not found
                response.status(404);
                return "Pais not found";
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID parameter is not a valid number
            response.status(400);
            return "Invalid pais ID format";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
            response.status(500);
            return "Error retrieving pais";
        }

    }

    public  String addPais(Request request, Response response) {
        try {
            // DesSerialização do Json para um objecto
            Pais newPais = gson.fromJson(request.body(), Pais.class);
            //envia o objecto para o service
            Pais addedPais = paisService.addPais(newPais);
            // Resposta e Status 201:sucesso no post
            response.status(201);
            response.header("Location", "/api/pais");
            response.type("text/plain");
            return "Resource created successfully.: \n" + gson.toJson(addedPais);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception appropriately and set the response status to 500 Internal Server Error
            response.status(500);
            return "Erro ao Adicionar o País.";
        }
    }


}
