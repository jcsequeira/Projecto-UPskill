package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Colaborador;
import model.ExplorArtModel;
import model.Pais;

import java.io.IOException;

public class ColaboradorDetailsView extends Parent {

    public ColaboradorDetailsView(Colaborador colaborador) throws IOException {
        doLayout(colaborador);
    }

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

        detailsLayout.getChildren().addAll(nomeLabel, emailLabel, telefoneLabel, paisLabel);

        getChildren().add(detailsLayout);
    }
}
