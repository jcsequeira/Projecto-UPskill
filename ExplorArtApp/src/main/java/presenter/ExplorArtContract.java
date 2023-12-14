package presenter;

import model.*;

import java.io.IOException;
import java.util.List;


// Interfaces (no pacote presenter)
public interface ExplorArtContract {

    interface View {
        void showArtists(List<Artista> artistas);
        void showArtistDetails(Artista artista);

        void showArtworks(List<Obra_Arte> obras);

        void showEvents(List<Evento> eventos);

        void showGalleries(List<Galeria> galerias);

        void showGallerists(List<Galerista> galeristas);

        void showShowsDetails(Evento evento, Galeria galeria);

        void showArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material);

        void showAddArtworkForm(Obra_Arte obraArte);

        void showAddArtistForm(Artista artista);
    }

    interface Presenter {
        void exploreArtists() throws IOException;

        void exploreArtworks() throws IOException;

        void exploreEvents() throws IOException;



        void addArtwork(Obra_Arte obraArte) throws IOException;

        void ativateArtwork();
        void deativateArtwork();
        void addArtist(Artista artista) throws IOException;
        void updateArtist();
        void removeArtist();
        void addShow(Evento evento) throws IOException;
        void updateShow();
        void removeShow();
        void addGalerist();
        void updateGalerist();
        void removeGalerist();
        void addGallery();
        void updateGallery();
        void removeGallery();
        void importDataFromArtsy();
        void deleteArtsyData();


        void doArtistDetails(Artista artista);

        void doArtworkDetails(Obra_Arte obraArte) throws IOException;

        void doAddArtwork();
        void doAddArtist();
    }

    interface Model {
        List<Artista> getArtists() throws IOException;
        void addArtist(Artista artista) throws IOException;

        List<Obra_Arte> getArtworks() throws IOException;

        void addArtwork(Obra_Arte obraArte) throws IOException;

        List<Evento> getEvents() throws IOException;
        void addShow(Evento evento) throws IOException;

        List<Galeria> getGalleries() throws IOException;

        List<Galerista> getGallerists() throws IOException;

        Galeria getGalleryById(int galleryId) throws IOException;

        Artista getArtistById(int idArtista) throws IOException;

        List<Tecnica> getTechnics() throws IOException;

        Tecnica getTechniqueById(int idTecnica) throws IOException;

        List<Movimento> getMovement() throws IOException;

        Movimento getMovementById(int idMovimento) throws IOException;


        List<Materiais> getMaterials() throws IOException;
        Materiais getMaterialById(int idMaterial) throws IOException;

        List<Pais> getPaises() throws IOException;
    }
}

