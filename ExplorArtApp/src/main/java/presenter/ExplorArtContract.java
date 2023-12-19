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

        void showUpdateArtworks(List<Obra_Arte> obras);

        void showUpdateArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material);

        void showUpdateArtist(List<Artista> artistas);

        void showUpdateArtistDetails(Artista artista);




        void showUpdateShow(List<Evento> eventos);

        void showUpdateShowDetails(Evento evento, Galeria galeria);

        void showUpdateGallerist(List<Galerista> gallerists);

        void showUpdateGalleristDetails(Galerista galerista);

        void visualizarColaboradores(List<Colaborador> colaboradores);

        void showColaboradorDetails(Colaborador colaborador) throws IOException;

        void showUpdateGallery(List<Galeria> galerias);

        void showUpdateGalleryDetails(Galeria galeria) throws IOException;

        void showUpdateColab(List<Colaborador> colaboradores);

        void showUpdateColabDetails(Colaborador colaborador);

        void showGalleryDetails(Galeria galeria) throws IOException;
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



            void deleteArtsyData() throws IOException;

            //View do's MenusActions
            void doArtistDetails(Artista artista);

            void doUpdateArtist() throws IOException;

            void doUpdateArtistDetails(Artista artista) throws IOException;

            void doAddArtist();

            //Artwork
            void doArtworkDetails(Obra_Arte obraArte) throws IOException;

            void doAddArtwork();

            void doUpdateArtwork() throws IOException;

            void doUpdateArtworkDetails(Obra_Arte obraArte) throws IOException;

            //show
            void doAddShow();

            //gallerist
            void doAddGalerist();

            //colaborador
            void doAddColaborador();

            //gallery
            void doAddGaleria();

            //Admin
            void doImportDataFromArtsy();


            void doUpdateShow() throws IOException;

            void doUpdateShowDetails(Evento evento) throws IOException;

            void doUpdateGallerist() throws IOException;

            void doUpdateGalleristDetails(Galerista colaborador) throws IOException;

            void visualizarColaboradores() throws IOException;

            void doColaboradorDetails(Colaborador colaborador) throws IOException;

            void doUpdateGallery() throws IOException;

            void doUpdateGalleryDetails(Galeria galeria) throws IOException;

            void doUpdateColab() throws IOException;

            void doUpdateColabDetails(Colaborador colaborador);

            void modifyColab(int id_colaborador, Colaborador colaborador) throws IOException;

            void exploreGalleries() throws IOException;

            void doGalleryDetails(Galeria galeria) throws IOException;
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
                Colaborador getColaboradorById(int id_colaborador) throws IOException;

                Pais getPaisById(int codigoPais) throws IOException;

                List<Pais> getPaises() throws IOException;

                List<Cidade> getCidades() throws IOException;


                Cidade getCidadeById(int id_Cidade) throws IOException;

                void deleteArtsyData() throws IOException;


                void modifyColab(int colabId, Colaborador colaborador) throws IOException;
            }

        }
