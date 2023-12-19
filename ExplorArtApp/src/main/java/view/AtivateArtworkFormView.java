package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.ExplorArtModel;
import model.Obra_Arte;
import presenter.ExplorArtPresenter;
import java.io.IOException;

/**
 * The ActivateArtworkFormView class represents a view for activating an artwork.
 * It allows the user to confirm the activation of the artwork through a confirmation alert.
 */
public class AtivateArtworkFormView extends Parent {
    private Obra_Arte obraArte;
    private ExplorArtPresenter myPresenter;

    /**
     * Constructs an instance of ActivateArtworkFormView with the specified artwork.
     *
     * @param obraArte The artwork to be activated.
     */
    public AtivateArtworkFormView(Obra_Arte obraArte) {
        this.obraArte = obraArte;

        doLayout(obraArte);
    }

    /**
     * Configures the layout to display details about the artwork and the activation button.
     *
     * @param obraArte The artwork whose details will be displayed.
     */
    private void doLayout(Obra_Arte obraArte) {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(10));

        // Título
        Label titleLabel = new Label("Título:");
        Label titleStatusLabel = new Label(obraArte.getTitulo());
        titleLabel.setStyle("-fx-font-size: 12pt;");
        titleStatusLabel.setStyle("-fx-font-size: 12pt;");
        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleStatusLabel, 1, 0);
        Label activeLabel = new Label("Ativo:");
        activeLabel.setStyle("-fx-font-size: 12pt;");
        gridPane.add(activeLabel, 0, 1);
        Label activeStatusLabel = new Label(obraArte.getIsActive() == 1 ? " Sim " : " Não");
        activeStatusLabel.setStyle("-fx-font-size: 12pt;");
        gridPane.add(activeStatusLabel, 1, 1);
        gridPane.setPrefWidth(300);
        getChildren().add(gridPane);

        // Botão "Atualizar" e respetiva lógica
        Button updateButton = new Button("Ativar Obra de Arte");

        updateButton.setOnAction(event -> {
            try {
                // Criar alerta de confirmação
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Ativar Obra de Arte?");
                confirmationAlert.setHeaderText("Tem a certeza que deseja ativar a obra de arte?");
                confirmationAlert.setContentText("Ao confirmar, a Obra de Arte será visivel no Menu Explorar!.");

                // Obter o resultado da confirmação
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                // Update the artwork information here
                if (result == ButtonType.OK) {
                    obraArte.setIsActive(1);

                    // Update the artwork using the presenter or model
                    myPresenter.modifyArtwork(obraArte.getId_Obra_Arte(), obraArte);

                    // Close the window
                    getScene().getWindow().hide();
                }

            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        gridPane.add(updateButton, 0, 2, 2, 1);
    }

}
