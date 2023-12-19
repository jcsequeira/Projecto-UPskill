package presenter;


import model.*;

import java.io.IOException;
import java.util.List;

/**
 * The ExplorArtContract interface defines the contracts for the ExplorArt application.
 */
public interface ExplorArtContract {

    /**
     * The View interface defines contracts related to the presentation layer in the ExplorArt application.
     */
    interface View {

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Explorar ******

        /**
         * Displays a list of artworks.
         * @param obras The list of artworks to be displayed.
         */
        void showArtworks(List<Obra_Arte> obras);

        /**
         * Displays a list of artists.
         * @param artistas The list of artists to be displayed.
         */
        void showArtists(List<Artista> artistas);

        /**
         * Displays a list of events.
         * @param eventos The list of events to be displayed.
         */
        void showEvents(List<Evento> eventos);

        /**
         * Displays a list of galleries.
         * @param galerias The list of galleries to be displayed.
         */
        void showGalleries(List<Galeria> galerias);

        /**
         * Displays details of an artwork.
         * @param obraArte The artwork to display details for.
         * @param artista The artist of the artwork.
         * @param tecnica The technique used in the artwork.
         * @param movimento The movement associated with the artwork.
         * @param material The materials used in the artwork.
         */
        void showArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material);

        /**
         * Displays details of an artist.
         * @param artista The artist to display details for.
         *
         */
        void showArtistDetails(Artista artista);

        /**
        * Displays details of a show.
        * @param evento The show to display details for.
        * @param galeria The gallery associated with the show.
        */
        void showShowsDetails(Evento evento, Galeria galeria);


        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Gerir OA ******
        /**
         * Displays the form for adding an artwork.
         * @param obraArte The artwork to pre-fill in the form (optional).
         */
        void showAddArtworkForm(Obra_Arte obraArte);

        /**
         * Displays a list of artworks for updating.
         * @param obras The list of artworks available for updating.
         */
        void showUpdateArtworks(List<Obra_Arte> obras);

        /**
         * Displays details of an artwork for updating.
         * @param obraArte The artwork to update.
         * @param artista The artist of the artwork.
         * @param tecnica The technique used in the artwork.
         * @param movimento The movement associated with the artwork.
         * @param material The materials used in the artwork.
         */
        void showUpdateArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material);

        /**
         * Displays a list of artworks for activation.
         * @param obras The list of artworks available for activation.
         */
        void showAtivateArtwokrs(List<Obra_Arte> obras);

        /**
         * Displays details of an artwork for activation.
         * @param obraArte The artwork to activate.
         * @param artista The artist of the artwork.
         * @param tecnica The technique used in the artwork.
         * @param movimento The movement associated with the artwork.
         * @param material The materials used in the artwork.
         */
        void showAtivateArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material);

        /**
         * Displays a list of artworks for deactivation.
         * @param obras The list of artworks available for deactivation.
         */
        void showDeativateArtwokrs(List<Obra_Arte> obras);

        /**
         * Displays details of an artwork for deactivation.
         * @param obraArte The artwork to deactivate.
         * @param artista The artist of the artwork.
         * @param tecnica The technique used in the artwork.
         * @param movimento The movement associated with the artwork.
         * @param material The materials used in the artwork.
         */
        void showDeativateArtworkDetails(Obra_Arte obraArte, Artista artista, Tecnica tecnica, Movimento movimento, Materiais material);

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Gerir Artistas ******

        /**
         * Displays the form for adding an artist.
         * @param artista The artist to pre-fill in the form (optional).
         */
        void showAddArtistForm(Artista artista);

        /**
         * Displays a list of artists for updating.
         * @param artistas The list of artists available for updating.
         */
        void showUpdateArtist(List<Artista> artistas);

        /**
         * Displays details of an artist for updating.
         * @param artista The artist to update.
         */
        void showUpdateArtistDetails(Artista artista);

        /**
         * Displays a list of artists for removal.
         * @param artistas The list of artists available for removal.
         */
        void showRemoveArtists(List<Artista> artistas);

        /**
         * Displays a confirmation window for removing an artist.
         * @param artista The artist to be removed.
         */
        void showRemoveArtistWindow(Artista artista);

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Gerir Eventos ******

        /**
         * Displays the form for adding a show.
         * @param evento The show to pre-fill in the form (optional).
         */
        void showAddShowForm(Evento evento);

        /**
         * Displays a list of shows for updating.
         * @param eventos The list of shows available for updating.
         */
        void showUpdateShow(List<Evento> eventos);

        /**
         * Displays details of a show for updating.
         * @param evento The show to update.
         * @param galeria The gallery associated with the show.
         */
        void showUpdateShowDetails(Evento evento, Galeria galeria);

        /**
         * Displays a list of shows for removal.
         * @param eventos The list of shows available for removal.
         */
        void showRemoveShows(List<Evento> eventos);

        /**
         * Displays a confirmation window for removing a show.
         * @param evento The show to be removed.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void showRemoveShowWindow(Evento evento) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Galeristas ******

        /**
         * Initiates the addition of a gallerist (galerista).
         */
        void showAddGalleristForm(Galerista galerista);

        /**
         * Displays a list of gallerists for updating.
         * @param gallerists The list of gallerists available for updating.
         */
        void showUpdateGallerist(List<Galerista> gallerists);

        /**
         * Displays details of a gallerist for updating.
         * @param galerista The gallerist to update.
         */
        void showUpdateGalleristDetails(Galerista galerista);

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Galerias ******

        /**
         * Initiates the addition of a gallery (galeria).
         */
        void showAddGalleryForm(Galeria galeria);

        /**
         * Displays a list of galleries for updating.
         * @param galerias The list of galleries available for updating.
         */
        void showUpdateGallery(List<Galeria> galerias);

        /**
         * Displays details of a gallery for updating.
         * @param galeria The gallery to update.
         * @throws IOException If an I/O error occurs during the update.
         */
        void showUpdateGalleryDetails(Galeria galeria) throws IOException;

        /**
         * Displays details of a gallery.
         * @param galeria The gallery to display details for.
         * @throws IOException If an I/O error occurs during the display.
         */
        void showGalleryDetails(Galeria galeria) throws IOException;

        /**
         * Displays a list of galleries for removal.
         * @param galerias The list of galleries available for removal.
         */
        void showRemoveGalleries(List<Galeria> galerias);

        /**
         * Displays a confirmation window for removing a gallery.
         * @param galeria The gallery to be removed.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void showRemoveGalleryWindow(Galeria galeria) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Admin ******

        /**
         * Initiates the addition of a collaborator (colaborador).
         * @param colaborador The collaborator to pre-fill in the form (optional).
         */
        void showAddColaboradorForm(Colaborador colaborador);

        /**
         * Displays a list of collaborators for viewing.
         * @param colaboradores The list of collaborators available for viewing.
         */
        void visualizarColaboradores(List<Colaborador> colaboradores);

        /**
         * Displays details of a collaborator.
         * @param colaborador The collaborator to display details for.
         * @throws IOException If an I/O error occurs during the display.
         */
        void showColaboradorDetails(Colaborador colaborador) throws IOException;

        /**
         * Displays a list of collaborators for updating.
         * @param colaboradores The list of collaborators available for updating.
         */
        void showUpdateColab(List<Colaborador> colaboradores);

        /**
         * Displays details of a collaborator for updating.
         * @param colaborador The collaborator to update.
         */
        void showUpdateColabDetails(Colaborador colaborador);
    }

    //------------------------------------------------------------------------------------------------------------------
    // ****** Contratos do Presenter ******

    /**
     * The contract defining the interactions between the Presenter layer and other layers in the ExplorArt application.
     */
    interface Presenter {
        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Explorar ******
        /**
         * Initiates the exploration of artworks.
         * @throws IOException If an I/O error occurs during exploration.
         */
        void exploreArtworks() throws IOException;

        /**
         * Initiates the display of details for a specific artwork.
         * @param obraArte The artwork for which details are to be displayed.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doArtworkDetails(Obra_Arte obraArte) throws IOException;

        /**
         * Initiates the exploration of artists.
         * @throws IOException If an I/O error occurs during exploration.
         */
        void exploreArtists() throws IOException;

        /**
         * Initiates the display of details for a specific artist.
         * @param artista The artist for which details are to be displayed.
         */
        void doArtistDetails(Artista artista);

        /**
         * Initiates the exploration of events.
         * @throws IOException If an I/O error occurs during exploration.
         */
        void exploreEvents() throws IOException;

        /**
         * Initiates the exploration of galleries.
         * @throws IOException If an I/O error occurs during exploration.
         */
        void exploreGalleries() throws IOException;

        /**
         * Initiates the display of details for a specific gallery.
         * @param galeria The gallery for which details are to be displayed.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doGalleryDetails(Galeria galeria) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Gerir OA ******

        /**
         * Initiates the addition of an artwork.
         */
        void doAddArtwork();

        /**
         * Adds an artwork to the system.
         * @param obraArte The artwork to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addArtwork(Obra_Arte obraArte) throws IOException;

        /**
         * Initiates the process of updating artworks.
         * @throws IOException If an I/O error occurs during the update.
         */
        void doUpdateArtwork() throws IOException;

        /**
         * Initiates the display of details for updating a specific artwork.
         * @param obraArte The artwork to be updated.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doUpdateArtworkDetails(Obra_Arte obraArte) throws IOException;

        /**
         * Modifies an existing artwork in the system.
         * @param artworkId The ID of the artwork to be modified.
         * @param obraArte The updated information for the artwork.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException;

        /**
         * Initiates the deactivation of an artwork.
         * @throws IOException If an I/O error occurs during the deactivation.
         */
        void doDeativateArtwork() throws IOException;

        /**
         * Initiates the display of details for deactivating a specific artwork.
         * @param obraArte The artwork to be deactivated.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doDeativateArtworkDetails(Obra_Arte obraArte) throws IOException;

        /**
         * Initiates the activation of an artwork.
         * @throws IOException If an I/O error occurs during the activation.
         */
        void doAtivateArtwork() throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Gerir Artistas ******

        /**
         * Initiates the addition of an artist.
         */
        void doAddArtist();

        /**
         * Adds an artist to the system.
         * @param artista The artist to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addArtist(Artista artista) throws IOException;

        /**
         * Modifies an existing artist in the system.
         * @param artistId The ID of the artist to be modified.
         * @param artista The updated information for the artist.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyArtist(int artistId, Artista artista) throws IOException;

        /**
         * Initiates the process of updating artists.
         * @throws IOException If an I/O error occurs during the update.
         */
        void doUpdateArtist() throws IOException;

        /**
         * Initiates the display of details for updating a specific artist.
         * @param artista The artist to be updated.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doUpdateArtistDetails(Artista artista) throws IOException;

        /**
         * Initiates the removal of an artist.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void doRemoveArtist() throws IOException;

        /**
         * Removes an artist from the system.
         * @param artistId The ID of the artist to be removed.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void removeArtist(int artistId) throws IOException;

        /**
         * Initiates the display of a window for removing a specific artist.
         * @param artista The artist to be removed.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doRemoveArtistWindow(Artista artista) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Gerir Eventos ******

        /**
         * Initiates the addition of an event.
         */
        void doAddShow();

        /**
         * Adds an event to the system.
         * @param evento The event to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addShow(Evento evento) throws IOException;

        /**
         * Initiates the process of updating events.
         * @throws IOException If an I/O error occurs during the update.
         */
        void doUpdateShow() throws IOException;

        /**
         * Modifies an existing event in the system.
         * @param showId The ID of the event to be modified.
         * @param evento The updated information for the event.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyShow(int showId, Evento evento) throws IOException;

        /**
         * Initiates the display of details for updating a specific event.
         * @param evento The event to be updated.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doUpdateShowDetails(Evento evento) throws IOException;

        /**
         * Initiates the removal of an event.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void doRemoveShow() throws IOException;

        /**
         * Initiates the display of a window for removing a specific event.
         * @param evento The event to be removed.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doRemoveShowWindow(Evento evento) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Galeristas ******

        /**
         * Initiates the addition of a gallerist.
         */
        void doAddGalerist();

        /**
         * Adds a gallerist to the system.
         * @param galerista The gallerist to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addGalerist(Galerista galerista) throws IOException;

        /**
         * Initiates the process of updating gallerists.
         * @throws IOException If an I/O error occurs during the update.
         */
        void doUpdateGallerist() throws IOException;

        /**
         * Modifies an existing gallerist in the system.
         * @param galleristId The ID of the gallerist to be modified.
         * @param galerista The updated information for the gallerist.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyGallerist(int galleristId, Galerista galerista) throws IOException;

        /**
         * Initiates the display of details for updating a specific gallerist.
         * @param colaborador The gallerist to be updated.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doUpdateGalleristDetails(Galerista colaborador) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Galerias ******

        /**
         * Initiates the addition of a gallery.
         */
        void doAddGaleria();

        /**
         * Adds a gallery to the system.
         * @param galeria The gallery to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addGallery(Galeria galeria) throws IOException;

        /**
         * Initiates the process of updating galleries.
         * @throws IOException If an I/O error occurs during the update.
         */
        void doUpdateGallery() throws IOException;

        /**
         * Modifies an existing gallery in the system.
         * @param galleryId The ID of the gallery to be modified.
         * @param galeria The updated information for the gallery.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyGallery(int galleryId, Galeria galeria) throws IOException;

        /**
         * Initiates the display of details for updating a specific gallery.
         * @param galeria The gallery to be updated.
         * @throws IOException If an I/O error occurs during the display.
         */
        void doUpdateGalleryDetails(Galeria galeria) throws IOException;

        /**
         * Initiates the removal of a gallery.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void doRemoveGallery() throws IOException;

        /**
         * Removes a gallery from the system.
         * @param galleryId The ID of the gallery to be removed.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void removeGallery(int galleryId) throws IOException;


        //--------------------------------------------------------------------------------------------------------------
        //****** Menu Admin ******
        /**
         * Initiates the process of viewing collaborators.
         * @throws IOException If an I/O error occurs during the viewing process.
         */
        void visualizarColaboradores() throws IOException;

        /**
         * Initiates the process of displaying details for a specific collaborator.
         * @param colaborador The collaborator for which details are to be displayed.
         * @throws IOException If an I/O error occurs during the display of details.
         */
        void doColaboradorDetails(Colaborador colaborador) throws IOException;

        /**
         * Initiates the process of adding a new collaborator.
         */
        void doAddColaborador();

        /**
         * Adds a new collaborator to the system.
         * @param colaborador The collaborator to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addColaborador(Colaborador colaborador) throws IOException;

        /**
         * Initiates the process of updating a collaborator.
         * @throws IOException If an I/O error occurs during the update.
         */
        void doUpdateColab() throws IOException;

        /**
         * Initiates the process of updating details for a specific collaborator.
         * @param colaborador The collaborator for which details are to be updated.
         */
        void doUpdateColabDetails(Colaborador colaborador);

        /**
         * Modifies a collaborator with the provided ID.
         * @param id_colaborador The ID of the collaborator to be modified.
         * @param colaborador The updated collaborator information.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyColab(int id_colaborador, Colaborador colaborador) throws IOException;

        /**
         * Initiates the process of deleting Artsy data from the system.
         * @throws IOException If an I/O error occurs during the deletion process.
         */
        void deleteArtsyData() throws IOException;

        /**
         * Initiates the process of importing data from Artsy.
         * @throws IOException If an I/O error occurs during the import process.
         */
        void doImportDataFromArtsy();
    }


    //------------------------------------------------------------------------------------------------------------------
    // ****** Contratos do Model ******

    /**
     * The Model interface defines the contracts for the model component of the ExplorArt application.
     * It includes methods related to retrieving, adding, modifying, and removing various entities in the system,
     * such as artworks, artists, galleries, gallerists, events, techniques, movements, materials, collaborators,
     * countries, cities, and Artsy data.
     */
    interface Model {

        // ****** OA ******

        /**
         * Retrieves a list of artworks.
         * @return The list of artworks.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Obra_Arte> getArtworks() throws IOException;

        /**
         * Adds a new artwork to the system.
         * @param obraArte The artwork to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addArtwork(Obra_Arte obraArte) throws IOException;

        /**
         * Modifies an artwork with the provided ID.
         * @param artworkId The ID of the artwork to be modified.
         * @param obraArte The updated artwork information.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Artistas ******

        /**
         * Retrieves a list of artists.
         * @return The list of artists.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Artista> getArtists() throws IOException;

        /**
         * Retrieves an artist with the specified ID.
         * @param idArtista The ID of the artist to be retrieved.
         * @return The artist with the specified ID.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Artista getArtistById(int idArtista) throws IOException;

        /**
         * Adds a new artist to the system.
         * @param artista The artist to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addArtist(Artista artista) throws IOException;

        /**
         * Modifies an artist with the provided ID.
         * @param artistId The ID of the artist to be modified.
         * @param artista The updated artist information.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyArtist(int artistId, Artista artista) throws IOException;

        /**
         * Removes an artist with the provided ID.
         * @param artistId The ID of the artist to be removed.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void removeArtist(int artistId) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Galerias ******

        /**
         * Retrieves a list of galleries.
         * @return The list of galleries.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Galeria> getGalleries() throws IOException;

        /**
         * Retrieves a gallery with the specified ID.
         * @param galleryId The ID of the gallery to be retrieved.
         * @return The gallery with the specified ID.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Galeria getGalleryById(int galleryId) throws IOException;

        /**
         * Adds a new gallery to the system.
         * @param galeria The gallery to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addGallery(Galeria galeria) throws IOException;

        /**
         * Modifies a gallery with the provided ID.
         * @param galleryId The ID of the gallery to be modified.
         * @param galeria The updated gallery information.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyGallery(int galleryId, Galeria galeria) throws IOException;

        /**
         * Removes a gallery with the provided ID.
         * @param galleryId The ID of the gallery to be removed.
         * @throws IOException If an I/O error occurs during the removal.
         */
        void removeGallery(int galleryId) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Galeristas ******

        /**
         * Retrieves a list of gallerists.
         * @return The list of gallerists.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Galerista> getGallerists() throws IOException;

        /**
         * Adds a new gallerist to the system.
         * @param galerista The gallerist to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addGalerist(Galerista galerista) throws IOException;

        /**
         * Modifies a gallerist with the provided ID.
         * @param galleristId The ID of the gallerist to be modified.
         * @param galerista The updated gallerist information.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyGallerist(int galleristId, Galerista galerista) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Eventos ******

        /**
         * Retrieves a list of events (shows).
         * @return The list of events.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Evento> getEvents() throws IOException;

        /**
         * Adds a new event (show) to the system.
         * @param evento The event to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addShow(Evento evento) throws IOException;

        /**
         * Modifies an event (show) with the provided ID.
         * @param showId The ID of the event (show) to be modified.
         * @param evento The updated event (show) information.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyShow(int showId, Evento evento) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Técnicas ******

        /**
         * Retrieves a list of techniques.
         * @return The list of techniques.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Tecnica> getTechnics() throws IOException;

        /**
         * Retrieves a technique with the specified ID.
         * @param idTecnica The ID of the technique to be retrieved.
         * @return The technique with the specified ID.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Tecnica getTechniqueById(int idTecnica) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Movimentos ******

        /**
         * Retrieves a list of movements.
         * @return The list of movements.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Movimento> getMovement() throws IOException;

        /**
         * Retrieves a movement with the specified ID.
         * @param idMovimento The ID of the movement to be retrieved.
         * @return The movement with the specified ID.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Movimento getMovementById(int idMovimento) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Materiais ******

        /**
         * Retrieves a list of materials.
         * @return The list of materials.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Materiais> getMaterials() throws IOException;

        /**
         * Retrieves a material with the specified ID.
         * @param idMaterial The ID of the material to be retrieved.
         * @return The material with the specified ID.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Materiais getMaterialById(int idMaterial) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Colaboradores ******

        /**
         * Retrieves a list of collaborators.
         * @return The list of collaborators.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Colaborador> getColaboradores() throws IOException;

        /**
         * Adds a new collaborator to the system.
         * @param colaborador The collaborator to be added.
         * @throws IOException If an I/O error occurs during the addition.
         */
        void addColaborador(Colaborador colaborador) throws IOException;

        /**
         * Retrieves a collaborator with the specified ID.
         * @param id_colaborador The ID of the collaborator to be retrieved.
         * @return The collaborator with the specified ID.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Colaborador getColaboradorById(int id_colaborador) throws IOException;

        /**
         * Modifies a collaborator with the provided ID.
         * @param colabId The ID of the collaborator to be modified.
         * @param colaborador The updated collaborator information.
         * @throws IOException If an I/O error occurs during the modification.
         */
        void modifyColab(int colabId, Colaborador colaborador) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Paises ******

        /**
         * Retrieves a country with the specified code.
         * @param codigoPais The code of the country to be retrieved.
         * @return The country with the specified code.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Pais getPaisById(int codigoPais) throws IOException;

        /**
         * Retrieves a list of countries.
         * @return The list of countries.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Pais> getPaises() throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Cidades ******

        /**
         * Retrieves a list of cities.
         * @return The list of cities.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        List<Cidade> getCidades() throws IOException;

        /**
         * Retrieves a city with the specified ID.
         * @param id_Cidade The ID of the city to be retrieved.
         * @return The city with the specified ID.
         * @throws IOException If an I/O error occurs during the retrieval process.
         */
        Cidade getCidadeById(int id_Cidade) throws IOException;

        //--------------------------------------------------------------------------------------------------------------
        // ****** Artsy Data ******

        /**
         * Initiates the process of deleting Artsy data from the system.
         * @throws IOException If an I/O error occurs during the deletion process.
         */
        void deleteArtsyData() throws IOException;
    }

}
