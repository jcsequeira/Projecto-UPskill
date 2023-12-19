package view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.Artista;
import model.ExplorArtModel;
import presenter.ExplorArtPresenter;

public class RemoveArtistaView extends Parent {
    private Artista artista;
    private ExplorArtPresenter explorArtPresenter;
    public RemoveArtistaView(Artista artista) {
        this.artista = artista;

        doLayout(artista);
    }

    private void doLayout(Artista artista) {
        explorArtPresenter = new ExplorArtPresenter(new ExplorArtView(), new ExplorArtModel());
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Título
        Label nameLabel = new Label("Nome:" + artista.getNome_artista());
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);

        // Data de Nascimento
        Label birthDayLabel = new Label("Data de nascimento:");
        DatePicker birthDayPicker = new DatePicker();
        gridPane.add(birthDayLabel, 0, 1);
        gridPane.add(birthDayPicker, 1, 1);
    }
}
