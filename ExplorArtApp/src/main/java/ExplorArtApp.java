import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ExplorArtModel;
import presenter.ExplorArtPresenter;
import view.ExplorArtView;
/**
 * The main class for the ExplorArt application. It extends JavaFX's Application class.
 * This class is responsible for launching the application, initializing the primary stage,
 * and setting up the MVC components (Model, View, Presenter).
 */
public class ExplorArtApp extends Application implements RunServerAPI.ServerReadyCallback {

    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        // Start the server API (if needed)
        RunServerAPI.main(args, new ExplorArtApp());
    }

    /**
     * Start method called by JavaFX to initialize the application.
     *
     * @param primaryStage The primary stage for the application.
     * @throws Exception If an error occurs during initialization.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ExplorArtView view = new ExplorArtView();
        ExplorArtModel model = new ExplorArtModel();
        ExplorArtPresenter presenter = new ExplorArtPresenter(view, model);

        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ExplorArt");
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> RunServerAPI.stopServer());
    }

    /**
     * Callback method invoked when the server is ready.
     * It launches the JavaFX application.
     */
    @Override
    public void onServerReady() {
        launch();
    }
}
