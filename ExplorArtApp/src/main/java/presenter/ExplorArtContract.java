package presenter;

import model.*;

import java.io.IOException;
import java.util.List;


// Interfaces (no pacote presenter)
public interface ExplorArtContract {

    interface View {
        void showArtists(List<Artista> artistas);

        void showArtworks(List<Obra_Arte> obras);

        void showEvents(List<Evento> eventos);

        void showGalleries(List<Galeria> galerias);

        void showGallerists(List<Galerista> galeristas);

    }

    interface Presenter {
        void exploreArtists() throws IOException;

        void exploreArtworks();

        void exploreEvents();

        void manageArtworks();

        void manageArtists();

        void manageEvents();

        void manageGalleries();

        void manageGallerists();

    }

    interface Model {
        List<Artista> getArtists() throws IOException;

        List<Obra_Arte> getArtworks();

        List<Evento> getEvents();

        List<Galeria> getGalleries();

        List<Galerista> getGallerists();
    }
}

