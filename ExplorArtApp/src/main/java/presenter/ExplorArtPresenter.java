package presenter;


import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import populate.ImportArtsyView;

import java.io.IOException;
import java.util.List;

/**
 * The ExplorArtPresenter class implements the Presenter interface of the ExplorArt application.
 * It acts as an intermediary between the View and Model components, handling user interactions
 * and updating the UI based on the data from the Model.
 */
public class ExplorArtPresenter implements ExplorArtContract.Presenter {

    /**
     * The View component associated with this presenter.
     */
    private final ExplorArtContract.View view;

    /**
     * The Model component associated with this presenter.
     */
    private final ExplorArtContract.Model model;

    /**
     * Constructs an ExplorArtPresenter with the specified View and Model components.
     *
     * @param view  The View component to be associated with this presenter.
     * @param model The Model component to be associated with this presenter.
     */
    public ExplorArtPresenter(ExplorArtContract.View view, ExplorArtContract.Model model) {
        this.view = view;
        this.model = model;
    }


    //------------------------------------------------------------------------------------------------------------------
    // ****** Menu Explorar ******
    /**
     * Retrieves a list of artworks from the model and displays them in the view.
     *
     * @throws IOException If an I/O error occurs while fetching the artworks.
     */
    @Override
    public void exploreArtworks() throws IOException {
        List<Obra_Arte> obras = model.getArtworks();
        view.showArtworks(obras);
    }

    /**
     * Displays detailed information about a specific artwork, including the artist, technique, movement, and materials.
     *
     * @param obraArte The artwork for which details should be displayed.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doArtworkDetails(Obra_Arte obraArte) throws IOException {
        int idArtista = obraArte.getId_artista();
        int idTecnica = obraArte.getId_Tecnica();
        int idMovimento = obraArte.getId_Estilo();
        int idMaterial = obraArte.getId_Material();

        Artista artista = model.getArtistById(idArtista);
        Tecnica tecnica = model.getTechniqueById(idTecnica);
        Movimento movimento = model.getMovementById(idMovimento);
        Materiais material = model.getMaterialById(idMaterial);

        view.showArtworkDetails(obraArte, artista, tecnica, movimento, material);
    }

    /**
     * Retrieves a list of artists from the model and displays them in the view.
     *
     * @throws IOException If an I/O error occurs while fetching the artists.
     */
    @Override
    public void exploreArtists() throws IOException {
        List<Artista> artistas = model.getArtists();
        view.showArtists(artistas);
    }

    /**
     * Displays detailed information about a specific artist.
     *
     * @param artista The artist for which details should be displayed.
     */
    @Override
    public void doArtistDetails(Artista artista) {
        view.showArtistDetails(artista);
    }

    /**
     * Retrieves a list of events from the model and displays them in the view.
     *
     * @throws IOException If an I/O error occurs while fetching the events.
     */
    @Override
    public void exploreEvents() throws IOException {
        List<Evento> eventos = model.getEvents();
        view.showEvents(eventos);
    }

    /**
     * Displays detailed information about a specific show.
     *
     * @param evento The show for which details should be displayed.
     */
    public void doShowDetails(Evento evento) {
        int idGaleria = evento.getId_Galeria();
        ExplorArtModel model = new ExplorArtModel();
        Galeria galeria = null;
        try {
            galeria = model.getGalleryById(idGaleria);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        view.showShowsDetails(evento, galeria);
    }

    /**
     * Retrieves a list of galleries from the model and displays them in the view.
     *
     * @throws IOException If an I/O error occurs while fetching the galleries.
     */
    @Override
    public void exploreGalleries() throws IOException {
        List<Galeria> galerias = model.getGalleries();
        view.showGalleries(galerias);
    }

    /**
     * Displays detailed information about a specific gallery.
     *
     * @param galeria The gallery for which details should be displayed.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doGalleryDetails(Galeria galeria) throws IOException {
        view.showGalleryDetails(galeria);
    }


    //------------------------------------------------------------------------------------------------------------------
    // ****** Menu Gerir OA ******

    /**
     * Adds a new artwork to the system.
     *
     * @param obraArte The artwork to be added.
     * @throws IOException If an I/O error occurs while adding the artwork.
     */
    @Override
    public void addArtwork(Obra_Arte obraArte) throws IOException {
        model.addArtwork(obraArte);
    }

    /**
     * Initiates the process of adding a new artwork by displaying the artwork form in the view.
     */
    @Override
    public void doAddArtwork() {
        Obra_Arte obraArte = new Obra_Arte();
        view.showAddArtworkForm(obraArte);
    }

    /**
     * Initiates the process of updating artworks by fetching the existing artworks from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the artworks.
     */
    @Override
    public void doUpdateArtwork() throws IOException {
        List<Obra_Arte> obras = model.getArtworks();
        view.showUpdateArtworks(obras);
    }

    /**
     * Initiates the process of updating the details of a specific artwork by displaying the existing
     * details in the view for modification.
     *
     * @param obraArte The artwork for which details should be updated.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doUpdateArtworkDetails(Obra_Arte obraArte) throws IOException {
        int idArtista = obraArte.getId_artista();
        int idTecnica = obraArte.getId_Tecnica();
        int idMovimento = obraArte.getId_Estilo();
        int idMaterial = obraArte.getId_Material();

        Artista artista = model.getArtistById(idArtista);
        Tecnica tecnica = model.getTechniqueById(idTecnica);
        Movimento movimento = model.getMovementById(idMovimento);
        Materiais material = model.getMaterialById(idMaterial);

        view.showUpdateArtworkDetails(obraArte, artista, tecnica, movimento, material);
    }

    /**
     * Modifies the details of a specific artwork based on the provided artwork ID.
     *
     * @param artworkId The ID of the artwork to be modified.
     * @param obraArte  The updated artwork details.
     * @throws IOException If an I/O error occurs while modifying the artwork.
     */
    @Override
    public void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        model.modifyArtwork(artworkId, obraArte);
    }

    /**
     * Initiates the process of activating artworks by fetching the existing artworks from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the artworks.
     */
    @Override
    public void doAtivateArtwork() throws IOException {
        List<Obra_Arte> obras = model.getArtworks();
        view.showAtivateArtwokrs(obras);
    }

    /**
     * Initiates the process of deactivating artworks by fetching the existing artworks from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the artworks.
     */
    @Override
    public void doDeativateArtwork() throws IOException {
        List<Obra_Arte> obras = model.getArtworks();
        view.showDeativateArtwokrs(obras);
    }

    /**
     * Initiates the process of deactivating the details of a specific artwork by displaying the existing
     * details in the view for deactivation.
     *
     * @param obraArte The artwork for which details should be deactivated.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doDeativateArtworkDetails(Obra_Arte obraArte) throws IOException {
        int idArtista = obraArte.getId_artista();
        int idTecnica = obraArte.getId_Tecnica();
        int idMovimento = obraArte.getId_Estilo();
        int idMaterial = obraArte.getId_Material();

        Artista artista = model.getArtistById(idArtista);
        Tecnica tecnica = model.getTechniqueById(idTecnica);
        Movimento movimento = model.getMovementById(idMovimento);
        Materiais material = model.getMaterialById(idMaterial);

        view.showDeativateArtworkDetails(obraArte, artista, tecnica, movimento, material);
    }

    /**
     * Initiates the process of activating the details of a specific artwork by displaying the existing
     * details in the view for activation.
     *
     * @param obraArte The artwork for which details should be activated.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    public void doAtivateArtworkDetails(Obra_Arte obraArte) throws IOException {
        int idArtista = obraArte.getId_artista();
        int idTecnica = obraArte.getId_Tecnica();
        int idMovimento = obraArte.getId_Estilo();
        int idMaterial = obraArte.getId_Material();

        Artista artista = model.getArtistById(idArtista);
        Tecnica tecnica = model.getTechniqueById(idTecnica);
        Movimento movimento = model.getMovementById(idMovimento);
        Materiais material = model.getMaterialById(idMaterial);

        view.showAtivateArtworkDetails(obraArte, artista, tecnica, movimento, material);
    }


    //------------------------------------------------------------------------------------------------------------------
    // ****** Menu Gerir Artistas ******
    /**
     * Adds a new artist to the system.
     *
     * @param artista The artist to be added.
     * @throws IOException If an I/O error occurs while adding the artist.
     */
    @Override
    public void addArtist(Artista artista) throws IOException {
        model.addArtist(artista);
    }

    /**
     * Initiates the process of adding a new artist by displaying the artist form in the view.
     */
    @Override
    public void doAddArtist() {
        Artista artista = new Artista();
        view.showAddArtistForm(artista);
    }

    /**
     * Initiates the process of updating artists by fetching the existing artists from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the artists.
     */
    @Override
    public void doUpdateArtist() throws IOException {
        List<Artista> artistas = model.getArtists();
        view.showUpdateArtist(artistas);
    }

    /**
     * Initiates the process of updating the details of a specific artist by displaying the existing
     * details in the view for modification.
     *
     * @param artista The artist for which details should be updated.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doUpdateArtistDetails(Artista artista) throws IOException {
        // Display the artist details along with additional information
        view.showUpdateArtistDetails(artista);
    }

    /**
     * Modifies the details of a specific artist based on the provided artist ID.
     *
     * @param artistId The ID of the artist to be modified.
     * @param artista  The updated artist details.
     * @throws IOException If an I/O error occurs while modifying the artist.
     */
    @Override
    public void modifyArtist(int artistId, Artista artista) throws IOException {
        model.modifyArtist(artistId, artista);
    }

    /**
     * Removes a specific artist from the system based on the provided artist ID.
     *
     * @param artistId The ID of the artist to be removed.
     * @throws IOException If an I/O error occurs while removing the artist.
     */
    @Override
    public void removeArtist(int artistId) throws IOException {
        model.removeArtist(artistId);
    }

    /**
     * Initiates the process of removing artists by fetching the existing artists from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the artists.
     */
    @Override
    public void doRemoveArtist() throws IOException {
        List<Artista> artistas = model.getArtists();
        view.showRemoveArtists(artistas);
    }

    /**
     * Initiates the process of removing a specific artist by displaying a confirmation window in the view.
     *
     * @param artista The artist to be removed.
     * @throws IOException If an I/O error occurs while displaying the removal window.
     */
    @Override
    public void doRemoveArtistWindow(Artista artista) throws IOException {
        view.showRemoveArtistWindow(artista);
    }


    //------------------------------------------------------------------------------------------------------------------
    // ****** Menu Gerir Eventos ******
    /**
     * Adds a new show (evento) to the system.
     *
     * @param evento The show to be added.
     * @throws IOException If an I/O error occurs while adding the show.
     */
    @Override
    public void addShow(Evento evento) throws IOException {
        model.addShow(evento);
    }

    /**
     * Initiates the process of adding a new show by displaying the show form in the view.
     */
    @Override
    public void doAddShow() {
        Evento evento = new Evento();
        view.showAddShowForm(evento);
    }

    /**
     * Initiates the process of updating shows by fetching the existing shows from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the shows.
     */
    @Override
    public void doUpdateShow() throws IOException {
        List<Evento> eventos = model.getEvents();
        view.showUpdateShow(eventos);
    }

    /**
     * Initiates the process of updating the details of a specific show by displaying the existing
     * details in the view for modification.
     *
     * @param evento The show for which details should be updated.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doUpdateShowDetails(Evento evento) throws IOException {
        int idGaleria = evento.getId_Galeria();
        Galeria galeria = model.getGalleryById(idGaleria);
        view.showUpdateShowDetails(evento, galeria);
    }

    /**
     * Modifies the details of a specific show based on the provided show ID.
     *
     * @param showId The ID of the show to be modified.
     * @param evento The updated show details.
     * @throws IOException If an I/O error occurs while modifying the show.
     */
    @Override
    public void modifyShow(int showId, Evento evento) throws IOException {
        model.modifyShow(showId, evento);
    }

    /**
     * Removes a show associated with the specified exhibition ID.
     *
     * @param idExpo The ID of the exhibition from which to remove the show.
     */
    @Override
    public void removeShow(int idExpo) throws IOException {
        model.removeShow(idExpo);
    }
    /**
     * Initiates the process of removing shows by fetching the existing shows from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the shows.
     */
    @Override
    public void doRemoveShow() throws IOException {
        List<Evento> eventos = model.getEvents();
        view.showRemoveShows(eventos);
    }

    /**
     * Initiates the process of removing a specific show by displaying a confirmation window in the view.
     *
     * @param evento The show to be removed.
     * @throws IOException If an I/O error occurs while displaying the removal window.
     */
    @Override
    public void doRemoveShowWindow(Evento evento) throws IOException {
        view.showRemoveShowWindow(evento);
    }




    //------------------------------------------------------------------------------------------------------------------
    // ****** Menu Gerir Galerias ******

    /**
     * Adds a new gallery to the system.
     *
     * @param galeria The gallery to be added.
     * @throws IOException If an I/O error occurs while adding the gallery.
     */
    @Override
    public void addGallery(Galeria galeria) throws IOException {
        model.addGallery(galeria);
    }

    /**
     * Initiates the process of adding a new gallery by displaying the gallery form in the view.
     */
    @Override
    public void doAddGaleria() {
        Galeria galeria = new Galeria();
        view.showAddGalleryForm(galeria);
    }

    /**
     * Initiates the process of updating galleries by fetching the existing galleries from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the galleries.
     */
    @Override
    public void doUpdateGallery() throws IOException {
        List<Galeria> galerias = model.getGalleries();
        view.showUpdateGallery(galerias);
    }

    /**
     * Initiates the process of updating the details of a specific gallery by displaying the existing
     * details in the view for modification.
     *
     * @param galeria The gallery for which details should be updated.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doUpdateGalleryDetails(Galeria galeria) throws IOException {
        view.showUpdateGalleryDetails(galeria);
    }

    /**
     * Modifies the details of a specific gallery based on the provided gallery ID.
     *
     * @param galleryId The ID of the gallery to be modified.
     * @param galeria   The updated gallery details.
     * @throws IOException If an I/O error occurs while modifying the gallery.
     */
    @Override
    public void modifyGallery(int galleryId, Galeria galeria) throws IOException {
        model.modifyGallery(galleryId, galeria);
    }

    /**
     * Removes a specific gallery from the system based on the provided gallery ID.
     *
     * @param galleryId The ID of the gallery to be removed.
     * @throws IOException If an I/O error occurs while removing the gallery.
     */
    @Override
    public void removeGallery(int galleryId) throws IOException {
        model.removeGallery(galleryId);
    }

    /**
     * Initiates the process of removing galleries by fetching the existing galleries from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the galleries.
     */
    @Override
    public void doRemoveGallery() throws IOException {
        List<Galeria> galerias = model.getGalleries();
        view.showRemoveGalleries(galerias);
    }

    /**
     * Initiates the process of removing a specific gallery by displaying a confirmation window in the view.
     *
     * @param galeria The gallery to be removed.
     * @throws IOException If an I/O error occurs while displaying the removal window.
     */
    public void doRemoveGalleryWindow(Galeria galeria) throws IOException {
        view.showRemoveGalleryWindow(galeria);
    }


    //------------------------------------------------------------------------------------------------------------------
    // ****** Menu Gerir Galeristas ******

    /**
     * Adds a new gallery owner (galerista) to the system.
     *
     * @param galerista The gallery owner to be added.
     * @throws IOException If an I/O error occurs while adding the gallery owner.
     */
    @Override
    public void addGalerist(Galerista galerista) throws IOException {
        model.addGalerist(galerista);
    }

    /**
     * Initiates the process of adding a new gallery owner by displaying the gallery owner form in the view.
     */
    @Override
    public void doAddGalerist() {
        Galerista galerista = new Galerista();
        view.showAddGalleristForm(galerista);
    }

    /**
     * Initiates the process of updating gallery owners by fetching the existing gallery owners from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the gallery owners.
     */
    @Override
    public void doUpdateGallerist() throws IOException {
        List<Galerista> galeristas = model.getGallerists();
        view.showUpdateGallerist(galeristas);
    }

    /**
     * Initiates the process of updating the details of a specific gallery owner by displaying the existing
     * details in the view for modification.
     *
     * @param galerista The gallery owner for which details should be updated.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doUpdateGalleristDetails(Galerista galerista) throws IOException {
        view.showUpdateGalleristDetails(galerista);
    }

    /**
     * Modifies the details of a specific gallery owner based on the provided gallery owner ID.
     *
     * @param galleristId The ID of the gallery owner to be modified.
     * @param galerista   The updated gallery owner details.
     * @throws IOException If an I/O error occurs while modifying the gallery owner.
     */
    @Override
    public void modifyGallerist(int galleristId, Galerista galerista) throws IOException {
        model.modifyGallerist(galleristId, galerista);
    }


    //------------------------------------------------------------------------------------------------------------------
    // ****** Menu Admin ******

    /**
     * Displays the list of collaborators in the system.
     *
     * @throws IOException If an I/O error occurs while fetching the collaborators.
     */
    @Override
    public void visualizarColaboradores() throws IOException {
        List<Colaborador> colaboradores = model.getColaboradores();
        view.visualizarColaboradores(colaboradores);
    }

    /**
     * Displays the details of a specific collaborator.
     *
     * @param colaborador The collaborator for which details should be displayed.
     * @throws IOException If an I/O error occurs while fetching additional information.
     */
    @Override
    public void doColaboradorDetails(Colaborador colaborador) throws IOException {
        view.showColaboradorDetails(colaborador);
    }

    /**
     * Adds a new collaborator to the system.
     *
     * @param colaborador The collaborator to be added.
     * @throws IOException If an I/O error occurs while adding the collaborator.
     */
    @Override
    public void addColaborador(Colaborador colaborador) throws IOException {
        model.addColaborador(colaborador);
    }

    /**
     * Initiates the process of adding a new collaborator by displaying the collaborator form in the view.
     */
    @Override
    public void doAddColaborador() {
        Colaborador colaborador = new Colaborador();
        view.showAddColaboradorForm(colaborador);
    }

    /**
     * Initiates the process of updating collaborators by fetching the existing collaborators from the model
     * and displaying them in the view for selection.
     *
     * @throws IOException If an I/O error occurs while fetching the collaborators.
     */
    @Override
    public void doUpdateColab() throws IOException {
        List<Colaborador> colaboradores = model.getColaboradores();
        view.showUpdateColab(colaboradores);
    }

    /**
     * Initiates the process of updating the details of a specific collaborator by displaying the existing
     * details in the view for modification.
     *
     * @param colaborador The collaborator for which details should be updated.
     */
    @Override
    public void doUpdateColabDetails(Colaborador colaborador) {
        view.showUpdateColabDetails(colaborador);
    }

    /**
     * Modifies the details of a specific collaborator based on the provided collaborator ID.
     *
     * @param colabId     The ID of the collaborator to be modified.
     * @param colaborador The updated collaborator details.
     * @throws IOException If an I/O error occurs while modifying the collaborator.
     */
    @Override
    public void modifyColab(int colabId, Colaborador colaborador) throws IOException {
        model.modifyColab(colabId, colaborador);
    }

    /**
     * Initiates the process of importing data from Artsy by showing the import data view.
     */
    @Override
    public void doImportDataFromArtsy() {
        showImportDataFromArtsyView();
    }

    /**
     * Displays the import data view for Artsy data.
     */
    public void showImportDataFromArtsyView() {
        Stage importDataFromArtsyStage = new Stage();
        importDataFromArtsyStage.setTitle("Importação de Dados");

        ImportArtsyView importArtsyView = new ImportArtsyView();
        Scene sceneImportDataFromArtsy = new Scene(importArtsyView);

        importDataFromArtsyStage.setScene(sceneImportDataFromArtsy);
        importDataFromArtsyStage.setResizable(true);

        importDataFromArtsyStage.sizeToScene();
        importDataFromArtsyStage.show();
    }

    /**
     * Deletes Artsy data from the system.
     *
     * @throws IOException If an I/O error occurs while deleting Artsy data.
     */
    @Override
    public void deleteArtsyData() throws IOException {
        model.deleteArtsyData();
    }
}

