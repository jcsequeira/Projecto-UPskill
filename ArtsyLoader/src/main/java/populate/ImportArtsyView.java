package populate;


import com.opencsv.exceptions.CsvException;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;


import java.io.IOException;

public class ImportArtsyView extends Parent {

    Label statusLabel;
    Label progressLabel;
    private ProgressBar progressBar;


    /**
     * invoca o metodo initUI para carregar GUI/layout
     */
    public ImportArtsyView() {

        initUI();
    }

    private void initUI() {
        // Initialize your view components, including the progress bar
        progressBar = new ProgressBar();
       // progressBar.setStyle("-fx-accent: #FF4081;"); // Custom color accent
        // Apply CSS styles to make the ProgressBar larger
        progressBar.setStyle("-fx-accent: #FF4081; -fx-pref-width: 300px; -fx-pref-height: 30px;");
        progressLabel = new Label("0 %");;
        // Create a start button with some styling
        Button startButton = new Button("Start Import");
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt;");

        // Create a label for status information
        statusLabel = new Label("Waiting for start...");
        statusLabel.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt;");

        progressLabel.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 12pt;");




        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(10));
        gridPane.setPrefSize(300,200);

        gridPane.add(startButton,0,0);
        gridPane.add(statusLabel, 0,1);
        gridPane.add(progressLabel,0,2);
        gridPane.add(progressBar, 0,3);

        getChildren().add(gridPane);

        Presenter myPresenter = new Presenter(this,new YourBackgroundModule());

        startButton.setOnMouseClicked(event -> {
            statusLabel.setText("Importing....Please Wait!");
            try {
                myPresenter.startBackgroundTask();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (CsvException e) {
                throw new RuntimeException(e);
            }


        });

    }

    public void updateProgressBar(double progress) {
        // Update the progress bar based on the value obtained from the Model
        progressBar.setProgress(progress);
        progressLabel.setText(progress*100 + " %");
        if (progress == 1) statusLabel.setText("Importation Complete! ");

    }


}
