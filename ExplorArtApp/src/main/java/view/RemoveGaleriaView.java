package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ExplorArtModel;
import model.Galeria;
import presenter.ExplorArtPresenter;

import java.io.IOException;

/**
 * The RemoveGaleriaView class represents a view for displaying details about a gallery and providing the option
 * to remove it. It extends the {@link javafx.scene.Parent} class.
 *
 *
 */
public class RemoveGaleriaView extends Parent {

    private ExplorArtPresenter myPresenter;
    private ExplorArtModel model;

    /**
     * Constructs a new RemoveGaleriaView instance for the specified gallery.
     *
     * @param galeria The gallery to be displayed and removed.
     * @throws IOException If an I/O error occurs.
     */
    public RemoveGaleriaView(Galeria galeria) throws IOException {
        doLayout(galeria);
    }

    private void doLayout(Galeria galeria) throws IOException {
        myPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
        model = new ExplorArtModel();
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Display details using labels
        Label nameLabel = new Label("Nome da galeria: " + galeria.getNome_Galeria());
        Label galeristaLabel = new Label("Galerista: " + model.getColaboradorById(galeria.getId_colaborador()));
        Label moradaGaleriaLabel = new Label("Morada: " + galeria.getMorada());
        Label cidadeGaleriaLabel = new Label("Cidade: " + model.getCidadeById(galeria.getId_Cidade()));
        Label telefoneLabel = new Label("Telefone: " + galeria.getTelefone());
        Label emailLabel = new Label("Email: " + galeria.getEmail());
        Label urlLabel = new Label("Website: " + galeria.getWebsite());

        // Button "Remover" and its logic
        Button removeButton = new Button("Remover galeria");
        removeButton.setOnAction(event -> {
            try {
                // Create a confirmation alert
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Remover galeria");
                confirmationAlert.setHeaderText("Tem a certeza que deseja remover a galeria da Base de Dados?");
                confirmationAlert.setContentText("Ao confirmar, a galeria será removida permanentemente da Base de Dados.");

                // Obtain the confirmation result
                ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

                if (result == ButtonType.OK) {
                    myPresenter.removeGallery(galeria.getId_Galeria());

                    // If the user confirms, close the window
                    getScene().getWindow().hide();
                }

            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter valores. Certifique-se de que os campos numéricos estão preenchidos corretamente.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Add labels to the layout
        detailsLayout.getChildren().addAll(nameLabel, galeristaLabel, moradaGaleriaLabel, cidadeGaleriaLabel, telefoneLabel,
                emailLabel, urlLabel, removeButton);

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }
}
