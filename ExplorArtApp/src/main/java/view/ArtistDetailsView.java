package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Artista;

import java.time.format.DateTimeFormatter;

public class ArtistDetailsView extends Parent {

    public ArtistDetailsView(Artista artista) {
        doLayout(artista);
    }

    private void doLayout(Artista artista) {
        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Display details using labels
        Label nameLabel = new Label("Name: " + artista.getNome_artista());
        Label birthDateLabel = new Label("Birth Date: " + formatDate(artista.getData_Nascimento()));
        Label deathDateLabel = new Label("Death Date: " + formatDate(artista.getData_Morte()));
        Label nationalityLabel = new Label("Nationality: " + artista.getNacionalidade());
        Label bioLabel = new Label("Biography: ");
        Label biolabelText = new Label();
        Text bioText = new Text(artista.getBiografia());
        bioText.setTextAlignment(TextAlignment.LEFT);
        // Add bioText to the label
        biolabelText.setGraphic(bioText);

        // Add labels to the layout
        detailsLayout.getChildren().addAll(nameLabel, birthDateLabel, deathDateLabel, nationalityLabel, bioLabel,biolabelText);

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);

    }

    private String formatDate(java.time.LocalDate date) {
        // Format the date using a DateTimeFormatter
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        } else {
            return "N/A";
        }
    }

    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }
}
