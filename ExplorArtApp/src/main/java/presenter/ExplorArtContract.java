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

        void showShowsDetails(Evento evento, Galeria galeria);
    }

    interface Presenter {
        void exploreArtists() throws IOException;

        void exploreArtworks() throws IOException;

        void exploreEvents() throws IOException;

        void manageArtworks();

        void manageArtists();

        void manageEvents();

        void manageGalleries();

        void manageGallerists();

    }

    interface Model {
        List<Artista> getArtists() throws IOException;

        List<Obra_Arte> getArtworks() throws IOException;

        List<Evento> getEvents() throws IOException;

        List<Galeria> getGalleries() throws IOException;

        List<Galerista> getGallerists() throws IOException;

        Galeria getGalleryById(int galleryId) throws IOException;
    }
}

