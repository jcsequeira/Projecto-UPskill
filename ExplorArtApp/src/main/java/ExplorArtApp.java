import apiservice.ApiService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import presenter.ExplorArtPresenter;
import view.ExplorArtView;

import java.time.LocalDate;
import java.util.List;

public class ExplorArtApp extends Application {
    public static void main(String[] args) throws InterruptedException {
        RunServerAPI.main(args);
        Thread.sleep(1000);
        launch (args);




    }

    public void start(Stage primaryStage) throws Exception{

        ExplorArtView view = new ExplorArtView();
        ExplorArtModel model = new ExplorArtModel();
        ExplorArtPresenter presenter = new ExplorArtPresenter(view, model);

        Scene scene = new Scene(view);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ExplorArt");
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> RunServerAPI.stopServer());




    }
}
