
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.*;
import controller.adapters.LocalDateAdapter;
import repository.*;
import service.*;
import spark.Spark;

import java.time.LocalDate;


import static spark.Spark.*;

public class RunServerAPI {

    private static Gson gsonLocalDate = new GsonBuilder().registerTypeAdapter(LocalDate .class, new LocalDateAdapter()).create();


    public static void main(String[] args) {
        //Test EndPoint
        get("/hello", (req, res) -> "Hello World");

        //Artistas.java CRUD Operations Endpoints
        Spark.get("/api/artistas",artistaInit(gsonLocalDate)::getAllArtistas);
        Spark.get("/api/artistas/:id",artistaInit(gsonLocalDate)::getArtistaById);
        Spark.post("/api/artistas", artistaInit(gsonLocalDate)::addArtista);
        Spark.put("/api/artistas/:id", artistaInit(gsonLocalDate)::updateArtista);
        Spark.delete("/api/artistas/:id", artistaInit(gsonLocalDate)::deleteArtista);

        //Galeria.java CRUD Operations Endpoints
        Spark.get("/api/galerias",galeriaInit()::getAllGaleria);
        Spark.get("/api/galerias/:id",galeriaInit()::getGaleriaById);
        Spark.post("/api/galerias", galeriaInit()::addGaleria);
        Spark.put("/api/galerias/:id", galeriaInit()::updateGaleria);
        Spark.delete("/api/galerias/:id", galeriaInit()::deleteGaleria);

        //Evento.java CRUD Operations Endpoints
        Spark.get("/api/eventos",eventoInit(gsonLocalDate)::getAllEvento);
        Spark.get("/api/eventos/:id",eventoInit(gsonLocalDate)::getEventoById);
        Spark.post("/api/eventos", eventoInit(gsonLocalDate)::addEvento);
        Spark.put("/api/eventos/:id", eventoInit(gsonLocalDate)::updateEvento);
        Spark.delete("/api/eventos/:id", eventoInit(gsonLocalDate)::deleteEvento);

        /// Cidade.java CRUD Operations Endpoints
        Spark.get("/api/cidades", cidadeInit()::getAllCidade);
        Spark.get("/api/cidades/:id", cidadeInit()::getCidadeById);
        Spark.post("/api/cidades", cidadeInit()::addCidade);
        Spark.put("/api/cidades/:id", cidadeInit()::updateCidade);
        Spark.delete("/api/cidades/:id", cidadeInit()::deleteCidade);

        //Movimento.java CRUD Operations Endpoints
        Spark.get("/api/movimentos",movimentoInit()::getAllMovimentos);
        Spark.get("/api/movimentos/:id",movimentoInit()::getMovimentoById);
        Spark.post("/api/movimentos", movimentoInit()::addMovimento);
        Spark.put("/api/movimentos/:id", movimentoInit()::updateMovimento);
        Spark.delete("/api/movimentos/:id", movimentoInit()::deleteMovimento);

        // Materiais.java CRUD Operations Endpoints
        Spark.get("/api/materiais", materiaisInit()::getAllMateriais);
        Spark.get("/api/materiais/:id", materiaisInit()::getMaterialById);
        Spark.post("/api/materiais", materiaisInit()::addMaterial);
        Spark.put("/api/materiais/:id", materiaisInit()::updateMaterial);
        Spark.delete("/api/materiais/:id", materiaisInit()::deleteMaterial);

        // Colaborador.java CRUD Operations Endpoints
        Spark.get("/api/colaboradores", colaboradorInit()::getAllColaboradores);
        Spark.get("/api/colaboradores/:id", colaboradorInit()::getColaboradorById);
        Spark.post("/api/colaboradores", colaboradorInit()::addColaborador);
        Spark.put("/api/colaboradores/:id", colaboradorInit()::updateColaborador);
        Spark.delete("/api/colaboradores/:id", colaboradorInit()::deleteColaborador);

        // Administrador.java CRUD Operations Endpoints
        Spark.get("/api/administradores", administradorInit()::getAllAdministrador);
        Spark.get("/api/administradores/:id", administradorInit()::getAdministradorById);
        Spark.post("/api/administradores", administradorInit()::addAdministrador);
        Spark.put("/api/administradores/:id", administradorInit()::updateAdministrador);
        Spark.delete("/api/administradores/:id", administradorInit()::deleteAdministrador);

        // Galerista.java CRUD Operations Endpoints
        Spark.get("/api/galeristas", galeristaInit(gsonLocalDate)::getAllGaleristas);
        Spark.get("/api/galeristas/:id", galeristaInit(gsonLocalDate)::getGaleristaById);
        Spark.post("/api/galeristas", galeristaInit(gsonLocalDate)::addGalerista);
        Spark.put("/api/galeristas/:id", galeristaInit(gsonLocalDate)::updateGalerista);
        Spark.delete("/api/galeristas/:id", galeristaInit(gsonLocalDate)::deleteGalerista);


        // Tecnica.java CRUD Operations Endpoints
        Spark.get("/api/tecnicas", tecnicaInit()::getAllTecnicas);
        Spark.get("/api/tecnicas/:id", tecnicaInit()::getTecnicaById);
        Spark.post("/api/tecnicas", tecnicaInit()::addTecnica);
        Spark.put("/api/tecnicas/:id", tecnicaInit()::updateTecnica);
        Spark.delete("/api/tecnicas/:id", tecnicaInit()::deleteTecnica);

        //Pais CRUD Operations Endpoints
        Spark.get("/api/paises",paisInit()::getAllPais);
        Spark.get("/api/paises/:id",paisInit()::getPaisById);
        Spark.post("/api/paises", paisInit()::addPais);
        Spark.put("/api/paises/:id", paisInit()::updatePais);
        Spark.delete("/api/paises/:id", paisInit()::deletePais);

        //Obra_Arte CRUD Operations Endpoints
        Spark.get("/api/obrasarte",obraArteInit(gsonLocalDate)::getAllObraArte);
        Spark.get("/api/obrasarte/:id",obraArteInit(gsonLocalDate)::getObraArteById);
        Spark.post("/api/obrasarte", obraArteInit(gsonLocalDate)::addObraArte);
        Spark.put("/api/obrasarte/:id", obraArteInit(gsonLocalDate)::updateObraArte);
        Spark.delete("/api/obrasarte/:id", obraArteInit(gsonLocalDate)::deleteObraArte);

        //Populate endpoints
        Spark.post("/populate/artista", artistaInit(gsonLocalDate)::addAllArtistas);
        Spark.post("/populate/cidades", cidadeInit()::addAllCidades);
        Spark.post("/populate/eventos", eventoInit(gsonLocalDate)::addAllEventos);
        Spark.post("/populate/galerias", galeriaInit()::addAllGalerias);
        Spark.post("/populate/materiais", materiaisInit()::addAllMateriais);
        Spark.post("/populate/movimentos", movimentoInit()::addAllMovimentos);
        Spark.post("/populate/obrasarte", obraArteInit(gsonLocalDate)::addAllObrasArte);
        Spark.post("/populate/paises", paisInit()::addAllPaises);
        Spark.post("/populate/tecnicas", tecnicaInit()::addAllTecnicas);




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

    public static ArtistaController artistaInit(Gson gson) {
        ArtistaRepository artistRepository = new ArtistaRepository(DBConnection.getConnection());
        ArtistaService artistaService = new ArtistaService(artistRepository);
        return new ArtistaController(artistaService, gson);}

    public static PaisController paisInit() {
        PaisRepository paisRepository = new PaisRepository(DBConnection.getConnection());
        PaisService paisService = new PaisService(paisRepository);
        Gson gson = new Gson();
        return new PaisController(paisService, gson);}

    public static ObraArteController obraArteInit(Gson gson) {
        ObraArteRepository obraArteRepository = new ObraArteRepository(DBConnection.getConnection());
        ObraArteService obraArteService = new ObraArteService(obraArteRepository);
        return new ObraArteController(obraArteService, gson);}

    public static GaleriaController galeriaInit() {
        GaleriaRepository galeriaRepository = new GaleriaRepository(DBConnection.getConnection());
        GaleriaService galeriaService = new GaleriaService(galeriaRepository);
        Gson gson = new Gson();
        return new GaleriaController(galeriaService, gson);}

    public static EventoController eventoInit(Gson gson) {
        EventoRepository eventoRepository = new EventoRepository(DBConnection.getConnection());
        EventoService eventoService = new EventoService(eventoRepository);
        return new EventoController(eventoService, gson);}

    public static CidadeController cidadeInit() {
    CidadeRepository cidadeRepository = new CidadeRepository(DBConnection.getConnection());
    CidadeService cidadeService = new CidadeService(cidadeRepository);
    return new CidadeController(cidadeService, new Gson());
    }

    public static MovimentoController movimentoInit() {
        MovimentoRepository movimentoRepository = new MovimentoRepository(DBConnection.getConnection());
        MovimentoService movimentoService = new MovimentoService(movimentoRepository);
        return new MovimentoController(movimentoService, new Gson());
    }

    public static MateriaisController materiaisInit() {
        MateriaisRepository materiaisRepository = new MateriaisRepository(DBConnection.getConnection());
        MateriaisService materiaisService = new MateriaisService(materiaisRepository);
        return new MateriaisController(materiaisService, new Gson());
    }


    public static ColaboradorController colaboradorInit() {
        ColaboradorRepository colaboradorRepository = new ColaboradorRepository(DBConnection.getConnection());
        ColaboradorService colaboradorService = new ColaboradorService(colaboradorRepository);
        return new ColaboradorController(colaboradorService, new Gson());
    }

    public static AdministradorController administradorInit() {
        AdministradorRepository administradorRepository = new AdministradorRepository(DBConnection.getConnection());
        AdministradorService administradorService = new AdministradorService(administradorRepository);
        return new AdministradorController(administradorService, new Gson());
    }

    public static GaleristaController galeristaInit(Gson gson) {
        GaleristaRepository galeristaRepository = new GaleristaRepository(DBConnection.getConnection());
        GaleristaService galeristaService = new GaleristaService(galeristaRepository);
        return new GaleristaController(galeristaService, gson);
    }

    public static TecnicaController tecnicaInit() {
        TecnicaRepository tecnicaRepository = new TecnicaRepository(DBConnection.getConnection());
        TecnicaService tecnicaService = new TecnicaService(tecnicaRepository);
        return new TecnicaController(tecnicaService, new Gson());
    }

    public static void stopServer() {
        Spark.stop();
    }



}
