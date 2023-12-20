package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The AboutView class represents the view for the "About" information in the ExplorArtApp.
 * It extends BorderPane and provides a simple layout to display information about the application.
 */
public class AboutView extends BorderPane {

    /**
     * Constructs an instance of AboutView. It configures the layout and initializes the UI components.
     * The about information includes the program title, description, version, authors, and a close button.
     */
    public AboutView() {
        // Layout configuration
        setPrefWidth(400);
        setPrefHeight(300);

        // Main panel content
        VBox contentBox = new VBox();
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setSpacing(10);

        // About program info
        Label titleLabel = new Label("Welcome to ExplorArtApp!");
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-alignment: center;");
        titleLabel.setAlignment(Pos.CENTER);

        Label descriptionLabel = new Label("Explore the world of art with our JavaFX-based app,\n" +
                "crafted with passion.\n" +
                "Launched in December 2023.");
        descriptionLabel.setStyle("-fx-font-size: 14; -fx-alignment: center;");
        descriptionLabel.setAlignment(Pos.CENTER);

        Label versionLabel = new Label("Version: 1.0");
        versionLabel.setStyle("-fx-font-size: 14; -fx-alignment: center;");
        versionLabel.setAlignment(Pos.CENTER);

        Label authorLabel = new Label("Developed by João Sequeira & Luís Rossa");
        authorLabel.setStyle("-fx-font-size: 14; -fx-alignment: center;");
        authorLabel.setAlignment(Pos.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> closeWindow());
        closeButton.setStyle("-fx-font-size: 14;");

        // Add components to VBox
        contentBox.getChildren().addAll(titleLabel, descriptionLabel, versionLabel, authorLabel, closeButton);

        // Set content at center
        setCenter(contentBox);
    }

    /**
     * Closes the AboutView window when the close button is clicked.
     */
    private void closeWindow() {
        Stage stage = (Stage) getScene().getWindow();
        stage.hide();
    }
}