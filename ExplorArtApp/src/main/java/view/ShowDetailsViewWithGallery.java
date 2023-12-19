/**
 * The ShowDetailsViewWithGallery class represents a view for displaying details of an event along with associated gallery information.
 * It displays details such as event name, start and end dates, gallery name, and event description.
 */
package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import model.Evento;
import model.Galeria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ShowDetailsViewWithGallery extends Parent {

    private Evento evento;
    private Galeria galeria;

    /**
     * Constructs an instance of ShowDetailsViewWithGallery with the specified event and gallery.
     *
     * @param evento  The event for which details are displayed.
     * @param galeria The associated gallery.
     */
    public ShowDetailsViewWithGallery(Evento evento, Galeria galeria) {
        this.evento = evento;
        this.galeria = galeria;

        doLayout(evento);
    }

    /**
     * Configures the layout to display details of the specified event along with associated gallery information.
     *
     * @param evento The event for which details are displayed.
     */
    private void doLayout(Evento evento) {
        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Display details using labels
        Label nameLabel = new Label("Evento: " + evento.getNome());
        Label startDateLabel = new Label("Data de ínicio: " + formatDate(evento.getData_inicio()));
        Label endDateLabel = new Label("Data de fim: " + formatDate(evento.getData_Fim()));
        Label galleryLabel = new Label("@ " + (galeria != null ? galeria.getNome_Galeria() : "<erro!>"));
        Label descriptionLabel = new Label("Descrição: ");
        Label descriptionLabelText = new Label();
        Text descriptionText = new Text(evento.getDescricao());
        descriptionText.setTextAlignment(TextAlignment.LEFT);

        // Add bioText to the label
        descriptionLabelText.setGraphic(descriptionText);

        // Add labels to the layout
        detailsLayout.getChildren().addAll(nameLabel, startDateLabel, endDateLabel, galleryLabel,
                descriptionLabel, descriptionLabelText);

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }

    /**
     * Formats the given date using a DateTimeFormatter.
     *
     * @param date The date to be formatted.
     * @return A formatted date string or "N/A" if the date is null.
     */
    private String formatDate(LocalDate date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        } else {
            return "N/A";
        }
    }
}
