package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ExplorArtModel;
import model.Galeria;

public class GalleryDetailsView extends Parent {
    public GalleryDetailsView(Galeria galeria) {
        doLayout(galeria);
    }

    private void doLayout(Galeria galeria) {
        ExplorArtModel model = new ExplorArtModel();

        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Labels
        Label nomeLabel = new Label("Nome da galeria: " + galeria.getNome_Galeria());
        Label emailLabel = new Label("Email: " + galeria.getEmail());
        Label telefoneLabel = new Label("Telefone: " + galeria.getTelefone());
        Label urlLabel = new Label("Website: " + galeria.getWebsite());
        Label moradaLabel = new Label("Morada: " + galeria.getMorada());
        Label cidadeLabel = new Label("Cidade: "+ galeria.getId_Cidade());
        Label galeristaLabel = new Label("Galerista: " + galeria.getId_colaborador());


        detailsLayout.getChildren().addAll(nomeLabel, galeristaLabel, moradaLabel, cidadeLabel,
                emailLabel, telefoneLabel, urlLabel);

        getChildren().add(detailsLayout);
    }
}
