package controller;

import com.google.gson.Gson;
import model.Pais;
import service.PaisService;
import spark.Request;
import spark.Response;


public class PaisController {

    private final PaisService paisService;
    private Gson gson;

    public PaisController(PaisService paisService, Gson gson) {
        this.paisService = paisService;
        this.gson = gson;
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
