package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Colaborador;
import model.ExplorArtModel;
import model.Pais;

import java.io.IOException;

/**
 * The ColaboradorDetailsView class represents a view for displaying details of a collaborator.
 * It includes information such as the collaborator's name, email, phone number, and nationality.
 */
public class ColaboradorDetailsView extends Parent {

    /**
     * Constructs an instance of ColaboradorDetailsView with the specified collaborator.
     *
     * @param colaborador The collaborator whose details will be displayed.
     * @throws IOException If an I/O exception occurs while retrieving data from the model.
     */
    public ColaboradorDetailsView(Colaborador colaborador) throws IOException {
        doLayout(colaborador);
    }

    /**
     * Configures the layout to display details about the collaborator.
     *
     * @param colaborador The collaborator whose details will be displayed.
     * @throws IOException If an I/O exception occurs while retrieving data from the model.
     */
    private void doLayout(Colaborador colaborador) throws IOException {
        ExplorArtModel model = new ExplorArtModel();
        Pais paisAux = model.getPaisById(colaborador.getCodigo_Pais());

        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Labels
        Label nomeLabel = new Label("Nome do colaborador: " + colaborador.getNome_Colaborador());
        Label emailLabel = new Label("Email: " + colaborador.getEmail());
        Label telefoneLabel = new Label("Telefone: " + colaborador.getTelefone());
        Label paisLabel = new Label("Naturalidade: " + paisAux.getNome_Pais());

        // Add labels to the layout
        detailsLayout.getChildren().addAll(nomeLabel, emailLabel, telefoneLabel, paisLabel);

        // Set the layout as the root of the scene
        getChildren().add(detailsLayout);
    }
}
