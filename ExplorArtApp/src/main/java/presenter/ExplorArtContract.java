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

        void showAddShowForm(Evento evento);

        void showAddGalleristForm(Galerista galerista);

        void showAddColaboradorForm(Colaborador colaborador);

        void showAddGalleryForm(Galeria galeria);
    }

    interface Presenter {
        void exploreArtists() throws IOException;
        void addArtist(Artista artista) throws IOException;
        void modifyArtist(int artistId, Artista artista) throws IOException;
        void removeArtist(int artistId) throws IOException;

        //OrbaArte
        void exploreArtworks() throws IOException;
        void addArtwork(Obra_Arte obraArte) throws IOException;
        void ativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException;
        void deativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException;
        void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException;
        //Eventos
        void exploreEvents() throws IOException;
        void addShow(Evento evento) throws IOException;
        void modifyShow(int showId, Evento evento) throws IOException;
        void removeShow(int showId) throws IOException;
        //Galerista
        void addGalerist(Galerista galerista) throws IOException;
        void modifyGallerist(int galleristId, Galerista galerista) throws IOException;
        void removeGallerist(int galleristId) throws IOException;
        //Galeria
        void addGallery(Galeria galeria) throws IOException;
        void modifyGallery(int galleryId, Galeria galeria) throws IOException;
        void removeGallery(int galleryId) throws IOException;
        //Admin
        void addColaborador(Colaborador colaborador) throws IOException;
        void importDataFromArtsy();
        void deleteArtsyData();

        //View do's MenusActions
        void doArtistDetails(Artista artista);
        void doArtworkDetails(Obra_Arte obraArte) throws IOException;


        void doAddArtwork();
        void doAddArtist();
        void doAddShow();
        void doAddGalerist();
        void doAddColaborador();
        void doAddGaleria();
    }

    interface Model {
        List<Artista> getArtists() throws IOException;
        Artista getArtistById(int idArtista) throws IOException;
        void addArtist(Artista artista) throws IOException;
        void modifyArtist(int artistId, Artista artista) throws IOException;
        void removeArtist(int artistId) throws IOException;
        //ObraArte
        List<Obra_Arte> getArtworks() throws IOException;
        void addArtwork(Obra_Arte obraArte) throws IOException;
        void ativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException;
        void deativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException;
        void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException;
        //Eventos
        List<Evento> getEvents() throws IOException;
        void addShow(Evento evento) throws IOException;
        void modifyShow(int showId, Evento evento) throws IOException;
        void removeShow(int showId) throws IOException;
        //Galerias
        List<Galeria> getGalleries() throws IOException;
        Galeria getGalleryById(int galleryId) throws IOException;
        void addGallery(Galeria galeria) throws IOException;
        void modifyGallery(int galleryId, Galeria galeria) throws IOException;
        void removeGallery(int galleryId) throws IOException;
        //Galeristas
        List<Galerista> getGallerists() throws IOException;
        void addGalerist(Galerista galerista) throws IOException;
        void modifyGallerist(int galleristId, Galerista galerista) throws IOException;
        void removeGallerist(int galleristId) throws IOException;

        //Foreign keys tables Obra ARte
        List<Tecnica> getTechnics() throws IOException;

        Tecnica getTechniqueById(int idTecnica) throws IOException;

        List<Movimento> getMovement() throws IOException;

        Movimento getMovementById(int idMovimento) throws IOException;


        List<Materiais> getMaterials() throws IOException;
        Materiais getMaterialById(int idMaterial) throws IOException;
        //Utils
        List<Colaborador> getColaboradores() throws IOException;
        void addColaborador(Colaborador colaborador) throws IOException;
        List<Pais> getPaises() throws IOException;
        List<Cidade> getCidades() throws IOException;

        void importDataFromArtsy();
        void deleteArtsyData();
    }
}

