package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Cidade;
import model.ExplorArtModel;
import model.Galeria;

import java.io.IOException;

public class GalleryDetailsView extends Parent {
    public GalleryDetailsView(Galeria galeria) throws IOException {
        doLayout(galeria);
    }

    private void doLayout(Galeria galeria) throws IOException {
        ExplorArtModel model = new ExplorArtModel();

        // Create a VBox to hold the details
        VBox detailsLayout = new VBox(10);
        detailsLayout.setPadding(new Insets(20));

        // Labels
        Label nomeLabel = new Label("Nome da galeria: " + galeria.getNome_Galeria());
        Label emailLabel = new Label("Email: " + ((galeria.getEmail() == null || galeria.getEmail().isEmpty()) ? "N/A" : galeria.getEmail()));
        Label telefoneLabel = new Label("Telefone: " + ((galeria.getTelefone() == null || galeria.getTelefone().isEmpty()) ? "N/A" : galeria.getTelefone()));
        Label urlLabel = new Label("Website: " + ((galeria.getWebsite() == null || galeria.getWebsite().isEmpty()) ? "N/A" : galeria.getWebsite()));
        Label moradaLabel = new Label("Morada: " + ((galeria.getMorada() == null || galeria.getMorada().isEmpty()) ? "N/A" : galeria.getMorada()));
        Label cidadeLabel = new Label("Cidade: "+ model.getCidadeById(galeria.getId_Cidade()));
        Label galeristaLabel = new Label("Galerista: " + model.getColaboradorById(galeria.getId_colaborador()));


        detailsLayout.getChildren().addAll(nomeLabel, galeristaLabel, moradaLabel, cidadeLabel,
                emailLabel, telefoneLabel, urlLabel);

        getChildren().add(detailsLayout);
    }
}
