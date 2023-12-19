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
public class ExplorArtApp extends Application {

    /**
     * The main method to start the ExplorArt application.
     * It launches the JavaFX application and initializes the primary stage.
     *
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        // Start the server API (if needed)
        RunServerAPI.main(args);

        // Launch the JavaFX application
        launch(args);
    }

    /**
     * The start method, part of the JavaFX Application lifecycle.
     * It is called when the application is launched.
     *
     * @param primaryStage The primary stage of the application.
     * @throws Exception If an exception occurs during the start process.
     */
    public void start(Stage primaryStage) throws Exception {
        // Create the View, Model, and Presenter components
        ExplorArtView view = new ExplorArtView();
        ExplorArtModel model = new ExplorArtModel();
        ExplorArtPresenter presenter = new ExplorArtPresenter(view, model);

        // Create the JavaFX scene using the View
        Scene scene = new Scene(view);

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Set the title for the primary stage
        primaryStage.setTitle("ExplorArt");

        // Show the primary stage
        primaryStage.show();

        // Set an event handler for the close request to stop the server API
        primaryStage.setOnCloseRequest(event -> RunServerAPI.stopServer());
    }
}

