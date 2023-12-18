package view;


import javafx.geometry.Insets;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;

import presenter.ExplorArtPresenter;

import java.io.IOException;

public class ImportArtsyView extends Parent {

    private ExplorArtPresenter myPresenter;

    private ProgressBar progressBar;
    private Button startButton;
    private Label statusLabel;

    public ImportArtsyView(ExplorArtPresenter myPresenter) {
        this.myPresenter = myPresenter;
        initUI();
    }

    private void initUI() {
        // Initialize your view components, including the progress bar
        progressBar = new ProgressBar();
        progressBar.setStyle("-fx-accent: #FF4081;"); // Custom color accent

        // Create a start button with some styling
        startButton = new Button("Start Import");
        startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt;");

        // Create a label for status information
        statusLabel = new Label("Waiting for start...");
        statusLabel.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14pt;");



        GridPane gridPane = new GridPane();
        gridPane.setHgap(100);
        gridPane.setVgap(100);
        gridPane.setPadding(new Insets(100));

        gridPane.add(startButton,0,0);
        gridPane.add(statusLabel, 0,1);
        gridPane.add(progressBar, 0,2);

        getChildren().add(gridPane);

        startButton.setOnMouseClicked(event -> {
            myPresenter.importDataFromArtsyStart();


        });

    }

    public void updateProgressBar(double progress) {
        // Update the progress bar based on the value obtained from the Model
        progressBar.setProgress(progress);
    }


}
