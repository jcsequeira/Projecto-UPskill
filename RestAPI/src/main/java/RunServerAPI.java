
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

        //Artista.java CRUD Operations Endpoints
        Spark.get("/api/artista/all",artistaInit(gsonLocalDate)::getAllArtistas);
        Spark.get("/api/artista/:id",artistaInit(gsonLocalDate)::getArtistaById);
        Spark.post("/api/artista", artistaInit(gsonLocalDate)::addArtista);
        Spark.put("/api/artista/:id", artistaInit(gsonLocalDate)::updateArtista);
        Spark.delete("/api/artista/:id", artistaInit(gsonLocalDate)::deleteArtista);

        //Galeria.java CRUD Operations Endpoints
        Spark.get("/api/galeria/all",galeriaInit()::getAllGaleria);
        Spark.get("/api/galeria/:id",galeriaInit()::getGaleriaById);
        Spark.post("/api/galeria", galeriaInit()::addGaleria);
        Spark.put("/api/galeria/:id", galeriaInit()::updateGaleria);
        Spark.delete("/api/galeria/:id", galeriaInit()::deleteGaleria);

        //Evento.java CRUD Operations Endpoints
        Spark.get("/api/evento/all",eventoInit(gsonLocalDate)::getAllEvento);
        Spark.get("/api/evento/:id",eventoInit(gsonLocalDate)::getEventoById);
        Spark.post("/api/evento", eventoInit(gsonLocalDate)::addEvento);
        Spark.put("/api/evento/:id", eventoInit(gsonLocalDate)::updateEvento);
        Spark.delete("/api/evento/:id", eventoInit(gsonLocalDate)::deleteEvento);

        /// Cidade.java CRUD Operations Endpoints
        Spark.get("/api/cidade/all", cidadeInit()::getAllCidade);
        Spark.get("/api/cidade/:id", cidadeInit()::getCidadeById);
        Spark.post("/api/cidade", cidadeInit()::addCidade);
        Spark.put("/api/cidade/:id", cidadeInit()::updateCidade);
        Spark.delete("/api/cidade/:id", cidadeInit()::deleteCidade);

        //Movimento.java CRUD Operations Endpoints
        Spark.get("/api/movimento/all",movimentoInit()::getAllMovimentos);
        Spark.get("/api/movimento/:id",movimentoInit()::getMovimentoById);
        Spark.post("/api/movimento", movimentoInit()::addMovimento);
        Spark.put("/api/movimento/:id", movimentoInit()::updateMovimento);
        Spark.delete("/api/movimento/:id", movimentoInit()::deleteMovimento);

        // Materiais.java CRUD Operations Endpoints
        Spark.get("/api/materiais/all", materiaisInit()::getAllMateriais);
        Spark.get("/api/materiais/:id", materiaisInit()::getMaterialById);
        Spark.post("/api/materiais", materiaisInit()::addMaterial);
        Spark.put("/api/materiais/:id", materiaisInit()::updateMaterial);
        Spark.delete("/api/materiais/:id", materiaisInit()::deleteMaterial);

        // Colaborador.java CRUD Operations Endpoints
        Spark.get("/api/colaborador/all", colaboradorInit()::getAllColaboradores);
        Spark.get("/api/colaborador/:id", colaboradorInit()::getColaboradorById);
        Spark.post("/api/colaborador", colaboradorInit()::addColaborador);
        Spark.put("/api/colaborador/:id", colaboradorInit()::updateColaborador);
        Spark.delete("/api/colaborador/:id", colaboradorInit()::deleteColaborador);
        /*
        // Administrador.java CRUD Operations Endpoints
        Spark.get("/api/administrador/all", administradorInit()::getAllAdministrador);
        Spark.get("/api/administrador/:id", administradorInit()::getAdministradorById);
        Spark.post("/api/administrador", administradorInit()::addAdministrador);
        Spark.put("/api/administrador/:id", administradorInit()::updateAdministrador);
        Spark.delete("/api/administrador/:id", administradorInit()::deleteAdministrador);
*/
        // Galerista.java CRUD Operations Endpoints
        Spark.get("/api/galerista/all", galeristaInit(gsonLocalDate)::getAllGaleristas);
        Spark.get("/api/galerista/:id", galeristaInit(gsonLocalDate)::getGaleristaById);
        Spark.post("/api/galerista", galeristaInit(gsonLocalDate)::addGalerista);
        Spark.put("/api/galerista/:id", galeristaInit(gsonLocalDate)::updateGalerista);
        Spark.delete("/api/galerista/:id", galeristaInit(gsonLocalDate)::deleteGalerista);


        // Tecnica.java CRUD Operations Endpoints
        Spark.get("/api/tecnica/all", tecnicaInit()::getAllTecnicas);
        Spark.get("/api/tecnica/:id", tecnicaInit()::getTecnicaById);
        Spark.post("/api/tecnica", tecnicaInit()::addTecnica);
        Spark.put("/api/tecnica/:id", tecnicaInit()::updateTecnica);
        Spark.delete("/api/tecnica/:id", tecnicaInit()::deleteTecnica);

        //Pais CRUD Operations Endpoints
        Spark.get("/api/pais/all",paisInit()::getAllPais);
        Spark.get("/api/pais/:id",paisInit()::getPaisById);
        Spark.post("/api/pais", paisInit()::addPais);
        Spark.put("/api/pais/:id", paisInit()::updatePais);
        Spark.delete("/api/pais/:id", paisInit()::deletePais);

        //Obra_Arte CRUD Operations Endpoints
        Spark.get("/api/obraarte/all",obraArteInit(gsonLocalDate)::getAllObraArte);
        Spark.get("/api/obraarte/:id",obraArteInit(gsonLocalDate)::getObraArteById);
        Spark.post("/api/obraarte", obraArteInit(gsonLocalDate)::addObraArte);
        Spark.put("/api/obraarte/:id", obraArteInit(gsonLocalDate)::updateObraArte);
        Spark.delete("/api/obraarte/:id", obraArteInit(gsonLocalDate)::deleteObraArte);


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
   /*
    public static AdministradorController administradorInit() {
        AdministradorRepository administradorRepository = new AdministradorRepository(DBConnection.getConnection());
        AdministradorService administradorService = new AdministradorService(administradorRepository);
        return new AdministradorController(administradorService, new Gson());
    }*/

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



}
