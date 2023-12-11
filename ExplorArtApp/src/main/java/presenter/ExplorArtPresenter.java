package presenter;

// Presenter (no pacote presenter)

import model.Artista;
import model.Evento;
import model.Obra_Arte;

import java.io.IOException;
import java.util.List;

public class ExplorArtPresenter implements ExplorArtContract.Presenter {

    private final ExplorArtContract.View view;
    private final ExplorArtContract.Model model;

    public ExplorArtPresenter(ExplorArtContract.View view, ExplorArtContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void exploreArtists() throws IOException {
        List<Artista> artistas = model.getArtists();
        view.showArtists(artistas);
    }

    @Override
    public void exploreArtworks() {
        List<Obra_Arte> obras = model.getArtworks();
        view.showArtworks(obras);
    }

    @Override
    public void exploreEvents() {
        List<Evento> eventos = model.getEvents();
        view.showEvents(eventos);
    }

    @Override
    public void manageArtworks() {
        // Lógica para gerenciar obras de arte
    }

    @Override
    public void manageArtists() {
        // Lógica para gerenciar artistas
    }

    @Override
    public void manageEvents() {
        // Lógica para gerenciar eventos
    }

    @Override
    public void manageGalleries() {
        // Lógica para gerenciar galerias
    }

    @Override
    public void manageGallerists() {
        // Lógica para gerenciar galeristas
    }

}
