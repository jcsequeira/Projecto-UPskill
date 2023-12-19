/**
 * The ArtistDetailsView class represents a view for displaying details about an artist.
 * It includes information such as the artist's name, birth and death dates, nationality, and biography.
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import model.Artista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ArtistDetailsView extends Parent {

    /**
     * Constructs an instance of ArtistDetailsView with the specified artist details.
     *
     * @param artista The artist whose details will be displayed.
     */
    public ArtistDetailsView(Artista artista) {
        doLayout(artista);
    }

    /**
     * Configures the layout to display details about the artist.
     *
     * @param artista The artist whose details will be displayed.
     */
    private void doLayout(Artista artista) {
        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Display details using labels
        Label nameLabel = new Label("Nome: " + artista.getNome_artista());
        Label birthDateLabel = new Label("Data de Nascimento: " + formatDate(artista.getData_Nascimento()));
        Label deathDateLabel = new Label("Data de Morte: " + formatDate(artista.getData_Morte()));
        Label nationalityLabel = new Label("Nacionalidade: " + artista.getNacionalidade());
        Label bioLabel = new Label("Biografia: ");

        // Create a TextFlow for the biography
        TextFlow bioTextFlow = new TextFlow();
        Text bioText = new Text(artista.getBiografia());
        bioText.setTextAlignment(TextAlignment.JUSTIFY);
        bioTextFlow.getChildren().add(bioText);

        // Set preferred width for proper text wrapping
        bioTextFlow.setPrefWidth(400); // Adjust the width as needed

        // Add labels to the layout
        detailsLayout.getChildren().addAll(nameLabel, birthDateLabel, deathDateLabel, nationalityLabel, bioLabel, bioTextFlow);

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }

    /**
     * Formats the given LocalDate using a specific pattern.
     *
     * @param date The date to be formatted.
     * @return A formatted string representation of the date.
     */
    private String formatDate(LocalDate date) {
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
