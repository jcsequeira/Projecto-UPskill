package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import model.Evento;
import model.ExplorArtModel;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RemoveEventoView extends Parent {
    private ExplorArtPresenter myPresenter;
    public RemoveEventoView(Evento evento) {
        doLayout(evento);
    }

    private void doLayout(Evento evento) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(),new ExplorArtModel());
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Display details using labels
        Label nameLabel = new Label("Nome: " + evento.getNome());
        Label startDateLabel = new Label("Data de inicio: " + formatDate(evento.getData_inicio()));
        Label endDateLabel = new Label("Data de fim: " + formatDate(evento.getData_Fim()));
        Label galleryLabel = new Label("Galeria: " + evento.getId_Galeria());


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
                    myPresenter.removeArtist(evento.getId_Expo());

                    // Se o utilizador confirmar, fecha a janela
                    getScene().getWindow().hide();
                }

            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        // Add labels to the layout
        detailsLayout.getChildren().addAll(nameLabel, startDateLabel, endDateLabel, galleryLabel,
                removeButton);

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
