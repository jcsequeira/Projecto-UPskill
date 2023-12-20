package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The CleanConfirmationView class represents a view for displaying a confirmation message after successfully
 * cleaning the data from the Artsy API.
 */
public class CleanConfirmationView extends BorderPane {

    /**
     * Constructs an instance of CleanConfirmationView.
     * It displays a confirmation message about the successful data cleaning from the Artsy API.
     */
    public CleanConfirmationView() {
        // Layout configuration
        setPrefWidth(400);
        setPrefHeight(300);

        // Main panel content
        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(10);

        // About program info
        Label titleLabel = new Label("Limpeza dos dados da API Artsy efetuada com sucesso!");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeWindow()); // This will close the CleanConfirmationView window;

        // Add components to VBox
        contentBox.getChildren().addAll(titleLabel, closeButton);

        // Set content at center
        setCenter(contentBox);
    }

    /**
     * Closes the current window when the "Close" button is clicked.
     */
    private void closeWindow() {
        Stage stage = (Stage) getScene().getWindow();
        stage.hide();
    }
}
