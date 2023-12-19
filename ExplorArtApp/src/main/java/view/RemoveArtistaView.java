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
import model.Artista;
import model.ExplorArtModel;
import presenter.ExplorArtPresenter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RemoveArtistaView extends Parent {

    private ExplorArtPresenter myPresenter;

    public RemoveArtistaView(Artista artista) {
        doLayout(artista);
    }

    private void doLayout(Artista artista) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
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


        // Botão "Remover" e respetiva lógica
        Button removeButton = new Button("Remover artista");
        removeButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Remover artista");
                confirmationAlert.setHeaderText("Tem a certeza que deseja remover o artista da Base de Dados?");
                confirmationAlert.setContentText("Ao confirmar, o artista será removido permanentemente da Base de Dados.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    myPresenter.removeArtist((int) artista.getId_artista());

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
        detailsLayout.getChildren().addAll(nameLabel, birthDateLabel, deathDateLabel, nationalityLabel, bioLabel, bioTextFlow,
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
