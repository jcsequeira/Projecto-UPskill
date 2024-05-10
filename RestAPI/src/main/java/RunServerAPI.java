import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.*;
import controller.adapters.LocalDateAdapter;
import repository.*;
import service.*;
import spark.Spark;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static spark.Spark.exception;
import static spark.Spark.get;

/**
 * Main class to run the SparkJava API server for CRUD operations on various entities.
 */
public class RunServerAPI {

    /**
     * Gson instance with a custom adapter for handling LocalDate serialization/deserialization.
     */
    private static final Gson gsonLocalDate = new GsonBuilder().registerTypeAdapter(LocalDate .class, new LocalDateAdapter()).create();

    /**
     * Callback interface for server readiness notification.
     */
    public interface ServerReadyCallback {
        /**
         * Method called when the server is ready.
         */
        void onServerReady();
    }

    /**
     * Main method to configure SparkJava routes and start the server.
     *
     * @param args      Command line arguments.
     * @param callback  Callback to be invoked when the server is ready.
     */
    public static void main(String[] args, ServerReadyCallback callback) {
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
        Spark.post("/populate/artistas", artistaInit(gsonLocalDate)::addAllArtistas);
        Spark.post("/populate/cidades", cidadeInit()::addAllCidades);
        Spark.post("/populate/eventos", eventoInit(gsonLocalDate)::addAllEventos);
        Spark.post("/populate/galerias", galeriaInit()::addAllGalerias);
        Spark.post("/populate/materiais", materiaisInit()::addAllMateriais);
        Spark.post("/populate/movimentos", movimentoInit()::addAllMovimentos);
        Spark.post("/populate/obrasarte", obraArteInit(gsonLocalDate)::addAllObrasArte);
        Spark.post("/populate/paises", paisInit()::addAllPaises);
        Spark.post("/populate/tecnicas", tecnicaInit()::addAllTecnicas);

        //Endpoint DeleteAllArtsy
        Spark.post("/api/cleanartsydata", (request, response) -> {
            String scriptName = request.body();
            executeScript(scriptName);
            return "Script triggered successfully for: " + scriptName ;
        });

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

        callback.onServerReady();
    }


    //Methods to Initialize All Services

    /**
     * Initializes and returns ArtistaController with the provided Gson instance.
     *
     * @param gson  Gson instance.
     * @return      Initialized ArtistaController.
     */
    public static ArtistaController artistaInit(Gson gson) {
        ArtistaRepository artistRepository = new ArtistaRepository(DBConnection.getConnection());
        ArtistaService artistaService = new ArtistaService(artistRepository);
        return new ArtistaController(artistaService, gson);}

    /**
     * Initializes and returns PaisController with default Gson instance.
     *
     * @return Initialized PaisController.
     */
    public static PaisController paisInit() {
        PaisRepository paisRepository = new PaisRepository(DBConnection.getConnection());
        PaisService paisService = new PaisService(paisRepository);
        Gson gson = new Gson();
        return new PaisController(paisService, gson);
    }

    /**
     * Initializes and returns ObraArteController with the provided Gson instance.
     *
     * @param gson Gson instance.
     * @return Initialized ObraArteController.
     */
    public static ObraArteController obraArteInit(Gson gson) {
        ObraArteRepository obraArteRepository = new ObraArteRepository(DBConnection.getConnection());
        ObraArteService obraArteService = new ObraArteService(obraArteRepository);
        return new ObraArteController(obraArteService, gson);
    }

    /**
     * Initializes and returns GaleriaController with default Gson instance.
     *
     * @return Initialized GaleriaController.
     */
    public static GaleriaController galeriaInit() {
        GaleriaRepository galeriaRepository = new GaleriaRepository(DBConnection.getConnection());
        GaleriaService galeriaService = new GaleriaService(galeriaRepository);
        Gson gson = new Gson();
        return new GaleriaController(galeriaService, gson);
    }

    /**
     * Initializes and returns EventoController with the provided Gson instance.
     *
     * @param gson Gson instance.
     * @return Initialized EventoController.
     */
    public static EventoController eventoInit(Gson gson) {
        EventoRepository eventoRepository = new EventoRepository(DBConnection.getConnection());
        EventoService eventoService = new EventoService(eventoRepository);
        return new EventoController(eventoService, gson);
    }

    /**
     * Initializes and returns CidadeController with default Gson instance.
     *
     * @return Initialized CidadeController.
     */
    public static CidadeController cidadeInit() {
        CidadeRepository cidadeRepository = new CidadeRepository(DBConnection.getConnection());
        CidadeService cidadeService = new CidadeService(cidadeRepository);
        return new CidadeController(cidadeService, new Gson());
    }

    /**
     * Initializes and returns MovimentoController with default Gson instance.
     *
     * @return Initialized MovimentoController.
     */
    public static MovimentoController movimentoInit() {
        MovimentoRepository movimentoRepository = new MovimentoRepository(DBConnection.getConnection());
        MovimentoService movimentoService = new MovimentoService(movimentoRepository);
        return new MovimentoController(movimentoService, new Gson());
    }

    /**
     * Initializes and returns MateriaisController with default Gson instance.
     *
     * @return Initialized MateriaisController.
     */
    public static MateriaisController materiaisInit() {
        MateriaisRepository materiaisRepository = new MateriaisRepository(DBConnection.getConnection());
        MateriaisService materiaisService = new MateriaisService(materiaisRepository);
        return new MateriaisController(materiaisService, new Gson());
    }

    /**
     * Initializes and returns ColaboradorController with default Gson instance.
     *
     * @return Initialized ColaboradorController.
     */
    public static ColaboradorController colaboradorInit() {
        ColaboradorRepository colaboradorRepository = new ColaboradorRepository(DBConnection.getConnection());
        ColaboradorService colaboradorService = new ColaboradorService(colaboradorRepository);
        return new ColaboradorController(colaboradorService, new Gson());
    }

    /**
     * Initializes and returns AdministradorController with default Gson instance.
     *
     * @return Initialized AdministradorController.
     */
    public static AdministradorController administradorInit() {
        AdministradorRepository administradorRepository = new AdministradorRepository(DBConnection.getConnection());
        AdministradorService administradorService = new AdministradorService(administradorRepository);
        return new AdministradorController(administradorService, new Gson());
    }

    /**
     * Initializes and returns GaleristaController with the provided Gson instance.
     *
     * @param gson Gson instance.
     * @return Initialized GaleristaController.
     */
    public static GaleristaController galeristaInit(Gson gson) {
        GaleristaRepository galeristaRepository = new GaleristaRepository(DBConnection.getConnection());
        GaleristaService galeristaService = new GaleristaService(galeristaRepository);
        return new GaleristaController(galeristaService, gson);
    }

    /**
     * Initializes and returns TecnicaController with default Gson instance.
     *
     * @return Initialized TecnicaController.
     */
    public static TecnicaController tecnicaInit() {
        TecnicaRepository tecnicaRepository = new TecnicaRepository(DBConnection.getConnection());
        TecnicaService tecnicaService = new TecnicaService(tecnicaRepository);
        return new TecnicaController(tecnicaService, new Gson());
    }

    /**
     * Executes a database script with the provided script name.
     *
     * @param scriptName Name of the script to be executed.
     */
    private static void executeScript(String scriptName) {
        // Add your script execution logic here
        Connection connection = DBConnection.getConnection();
        System.out.println("Executing script: " + scriptName);

        // Implement the logic to execute the script in your database
        try {
            // Define the SQL statement to call the stored procedure
            String sql = "{CALL DeleteIsArtsyEntries()}";

            // Prepare the statement
            try (CallableStatement statement = connection.prepareCall(sql)) {
                // No parameters to set since the stored procedure doesn't take any

                // Execute the stored procedure
                statement.execute();
                System.out.println("Script Executed with Success!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Stops the Spark server.
     */
    public static void stopServer() {
        Spark.stop();
    }
}
