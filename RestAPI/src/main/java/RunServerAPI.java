
import com.google.gson.Gson;
import controller.*;
import repository.*;
import service.*;
import spark.Spark;

import static spark.Spark.*;

public class RunServerAPI {

    public static void main(String[] args) {
        //Test EndPoint
        get("/hello", (req, res) -> "Hello World");

        //Artista.java CRUD Operations Endpoints
        Spark.get("/api/artista/all",artistaInit()::getAllArtistas);
        Spark.get("/api/artista/:id",artistaInit()::getArtistaById);
        Spark.post("/api/artista", artistaInit()::addArtista);
        Spark.put("/api/artista/:id", artistaInit()::updateArtista);
        Spark.delete("/api/artista/:id", artistaInit()::deleteArtista);


        //Galeria.java CRUD Operations Endpoints
        Spark.get("/api/galeria/all",galeriaInit()::getAllGaleria);
        Spark.get("/api/galeria/:id",galeriaInit()::getGaleriaById);
        Spark.post("/api/galeria", galeriaInit()::addGaleria);
        Spark.put("/api/galeria/:id", galeriaInit()::updateGaleria);
        Spark.delete("/api/galeria/:id", galeriaInit()::deleteGaleria);



        //Evento.java CRUD Operations Endpoints
        Spark.get("/api/evento/all",eventoInit()::getAllEvento);
        Spark.get("/api/evento/:id",eventoInit()::getEventoById);
        Spark.post("/api/evento", eventoInit()::addEvento);
        Spark.put("/api/evento/:id", eventoInit()::updateEvento);
        Spark.delete("/api/evento/:id", eventoInit()::deleteEvento);

        //Cidade.java CRUD Operations Endpoints
        //TBD
        //TBD
        //TBD
        //TBD
        //TBD

        //Movimento.java CRUD Operations Endpoints
        //TBD
        //TBD
        //TBD
        //TBD
        //TBD

        //Materiais.java CRUD Operations Endpoints
        //TBD
        //TBD
        //TBD
        //TBD
        //TBD

        //Colaborador.java CRUD Operations Endpoints
        //TBD
        //TBD
        //TBD
        //TBD
        //TBD

        //Administrador.java CRUD Operations Endpoints
        //TBD
        //TBD
        //TBD
        //TBD
        //TBD

        //Galerista.java CRUD Operations Endpoints
        //TBD
        //TBD
        //TBD
        //TBD
        //TBD




        //Pais CRUD Operations Endpoints
        Spark.get("/api/pais/all",paisInit()::getAllPais);
        Spark.get("/api/pais/:id",paisInit()::getPaisById);
        Spark.post("/api/pais", paisInit()::addPais);
        Spark.put("/api/pais/:id", paisInit()::updatePais);
        Spark.delete("/api/pais/:id", paisInit()::deletePais);


        //Obra_Arte CRUD Operations Endpoints
        Spark.get("/api/obraarte/all",obraArteInit()::getAllObraArte);
        Spark.get("/api/obraarte/:id",obraArteInit()::getObraArteById);
        Spark.post("/api/obraarte", obraArteInit()::addObraArte);
        Spark.put("/api/obraarte/:id", obraArteInit()::updateObraArte);
        Spark.delete("/api/obraarte/:id", obraArteInit()::deleteObraArte);


        //TRATAMENTO DE EXCEPÇOES para melhorar!

        exception(NumberFormatException.class, (e, request, response) -> {
            // logger.error("{} : Got an exception for request : {}  ", e.getLocalizedMessage(), request.url());
            response.status(500);
             response.body("Invalid ID:"+ e.getMessage());
        });

        exception(Exception.class, (e, request, response) -> {
            // logger.error("{} : Got an exception for request : {}  ", e.getLocalizedMessage(), request.url());
            response.status(500);
             response.body("Erro: "+e.getMessage());
        });


    }

    //Methods to Initialize All Services
    public static ArtistaController artistaInit() {
        ArtistaRepository artistRepository = new ArtistaRepository(DBConnection.getConnection());
        ArtistaService artistaService = new ArtistaService(artistRepository);
        Gson gson = new Gson();
        return new ArtistaController(artistaService, gson);}

    public static PaisController paisInit() {
        PaisRepository paisRepository = new PaisRepository(DBConnection.getConnection());
        PaisService paisService = new PaisService(paisRepository);
        Gson gson = new Gson();
        return new PaisController(paisService, gson);}

    public static ObraArteController obraArteInit() {
        ObraArteRepository obraArteRepository = new ObraArteRepository(DBConnection.getConnection());
        ObraArteService obraArteService = new ObraArteService(obraArteRepository);
        Gson gson = new Gson();
        return new ObraArteController(obraArteService, gson);}

    public static GaleriaController galeriaInit() {
        GaleriaRepository galeriaRepository = new GaleriaRepository(DBConnection.getConnection());
        GaleriaService galeriaService = new GaleriaService(galeriaRepository);
        Gson gson = new Gson();
        return new GaleriaController(galeriaService, gson);}

    public static EventoController eventoInit() {
        EventoRepository eventoRepository = new EventoRepository(DBConnection.getConnection());
        EventoService eventoService = new EventoService(eventoRepository);
        Gson gson = new Gson();
        return new EventoController(eventoService, gson);}

/*
    public static CidadeController cidadeInit() {
        // TBD: Implement logic to initialize CidadeController
        CidadeController cidadeController = new CidadeController();
        // Add any necessary initialization or configuration logic
        return cidadeController;
    }

    public static MovimentoController movimentoInit() {
        // TBD: Implement logic to initialize MovimentoController
        MovimentoController movimentoController = new MovimentoController();
        // Add any necessary initialization or configuration logic
        return movimentoController;
    }

    public static MateriaisController materiaisInit() {
        // TBD: Implement logic to initialize MateriaisController
        MateriaisController materiaisController = new MateriaisController();
        // Add any necessary initialization or configuration logic
        return materiaisController;
    }

    public static ColaboradorController colaboradorInit() {
        // TBD: Implement logic to initialize ColaboradorController
        ColaboradorController colaboradorController = new ColaboradorController();
        // Add any necessary initialization or configuration logic
        return colaboradorController;
    }

    public static AdministradorController administradorInit() {
        // TBD: Implement logic to initialize AdministradorController
        AdministradorController administradorController = new AdministradorController();
        // Add any necessary initialization or configuration logic
        return administradorController;
    }

    public static GaleristaController galeristaInit() {
        // TBD: Implement logic to initialize GaleristaController
        GaleristaController galeristaController = new GaleristaController();
        // Add any necessary initialization or configuration logic
        return galeristaController;
    }*/



}
