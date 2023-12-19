package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CleanConfirmationView extends BorderPane {
    public CleanConfirmationView() {
        // Layout configuration
        setPrefWidth(400);
        setPrefHeight(300);

        // main panel content
        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(10);

        // About program info
        Label titleLabel = new Label("Limpeza dos dados da API Artsy efetuada com sucesso!");


        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeWindow());// This will close the AboutView window;


        // Add components to VBox
        contentBox.getChildren().addAll(titleLabel, closeButton);

        // Set content at center
        setCenter(contentBox);
    }

    //close button method
    private void closeWindow() {
        Stage stage = (Stage) getScene().getWindow();
        stage.hide();
    }
}
