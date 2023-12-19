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

    public ShowDetailsViewWithGallery(Evento evento, Galeria galeria) {
        this.evento = evento;
        this.galeria = galeria;

        doLayout(evento);
    }

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


    private String formatDate(LocalDate date) {
        // Format the date using a DateTimeFormatter
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        } else {
            return "N/A";
        }
    }
}
