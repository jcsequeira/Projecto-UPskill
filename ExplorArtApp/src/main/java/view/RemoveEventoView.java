package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Evento;
import model.ExplorArtModel;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The RemoveEventoView class represents a view for removing an event.
 * It displays details of the event and provides a button to remove the event from the database.
 */
public class RemoveEventoView extends Parent {
    private ExplorArtPresenter myPresenter;
    private ExplorArtModel model;

    /**
     * Constructs an instance of RemoveEventoView with the specified event.
     *
     * @param evento The event to be removed.
     * @throws IOException If an I/O error occurs.
     */
    public RemoveEventoView(Evento evento) throws IOException {
        doLayout(evento);
    }

    /**
     * Configures the layout to display details of the specified event and a button to remove the event.
     *
     * @param evento The event for which details are displayed.
     * @throws IOException If an I/O error occurs.
     */
    private void doLayout(Evento evento) throws IOException {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
        model = new ExplorArtModel();
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Display details using labels
        Label nameLabel = new Label("Nome: " + evento.getNome());
        Label startDateLabel = new Label("Data de inicio: " + formatDate(evento.getData_inicio()));
        Label endDateLabel = new Label("Data de fim: " + formatDate(evento.getData_Fim()));
        Label galleryLabel = new Label("Galeria: " + model.getGalleryById(evento.getId_Galeria()));

        // Botão "Remover" e respetiva lógica
        Button removeButton = new Button("Remover evento");
        removeButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Remover evento");
                confirmationAlert.setHeaderText("Tem a certeza que deseja remover o evento da Base de Dados?");
                confirmationAlert.setContentText("Ao confirmar, o evento será removido permanentemente da Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    myPresenter.removeShow(evento.getId_Expo());

                    // Se o utilizador confirmar, fecha a janela
                    getScene().getWindow().hide();
                }

            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Add labels and button to the layout
        detailsLayout.getChildren().addAll(nameLabel, startDateLabel, endDateLabel, galleryLabel, removeButton);

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }

    /**
     * Formats the date using a DateTimeFormatter.
     *
     * @param date The date to be formatted.
     * @return The formatted date as a string.
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
