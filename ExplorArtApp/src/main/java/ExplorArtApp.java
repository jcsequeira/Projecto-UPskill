import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import presenter.ExplorArtPresenter;
import view.ExplorArtView;

public class ExplorArtApp extends Application {
    public static void main(String[] args) {
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
    }
}