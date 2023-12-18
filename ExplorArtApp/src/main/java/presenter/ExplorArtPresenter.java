package presenter;

// Presenter (no pacote presenter)

import model.*;
import view.ImportArtsyView;

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
    public void exploreArtworks() throws IOException {
        List<Obra_Arte> obras = model.getArtworks();
        view.showArtworks(obras);
    }

    @Override
    public void exploreEvents() throws IOException {
        List<Evento> eventos = model.getEvents();
        view.showEvents(eventos);
    }

    @Override
    public void doAddArtwork() {
        Obra_Arte obraArte = new Obra_Arte();
        view.showAddArtworkForm(obraArte);
    }

    @Override
    public void doUpdateArtwork() throws IOException {
        List<Obra_Arte> obras = model.getArtworks();
        view.showUpdateArtworks(obras);
    }

    @Override
    public void addArtwork(Obra_Arte obraArte) throws IOException {
        model.addArtwork(obraArte);
    }



    @Override
    public void ativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        model.ativateArtwork(artworkId, obraArte);
    }

    @Override
    public void deativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        model.deativateArtwork(artworkId, obraArte);
    }

    @Override
    public void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        model.modifyArtwork(artworkId, obraArte);
    }


    @Override
    public void addArtist(Artista artista) throws IOException {
        model.addArtist(artista);
    }

    @Override
    public void modifyArtist(int artistId, Artista artista) throws IOException {
        model.modifyArtist(artistId, artista);
    }

    @Override
    public void removeArtist(int artistId) throws IOException {
        model.removeArtist(artistId);
    }

    @Override
    public void doAddArtist() {
        Artista artista = new Artista();
        view.showAddArtistForm(artista);
    }

    @Override
    public void addShow(Evento evento) throws IOException {
        model.addShow(evento);
    }

    @Override
    public void modifyShow(int showId, Evento evento) throws IOException {
        model.modifyShow(showId, evento);
    }

    @Override
    public void removeShow(int showId) throws IOException {
        model.removeShow(showId);
    }


    @Override
    public void doAddShow() {
        Evento evento = new Evento();
        view.showAddShowForm(evento);
    }

    @Override
    public void doAddGalerist() {
        Galerista galerista = new Galerista();
        view.showAddGalleristForm(galerista);
    }

    @Override
    public void doAddColaborador() {
        Colaborador colaborador = new Colaborador();
        view.showAddColaboradorForm(colaborador);
    }

    @Override
    public void doAddGaleria() {
        Galeria galeria = new Galeria();
        view.showAddGalleryForm(galeria);
    }

    @Override
    public void doImportDataFromArtsy() {
        view.showImportDataFromArtsyView();
    }

    @Override
    public void doUpdateShow() throws IOException {
        List<Evento> eventos = model.getEvents();
        view.showUpdateShow(eventos);
    }

    @Override
    public void doUpdateShowDetails(Evento evento) throws IOException {
        view.showUpdateShowDetails(evento);
    }

    @Override
    public void doUpdateArtworkDetails(Obra_Arte obraArte) throws IOException {
        ExplorArtModel model = new ExplorArtModel();

        int idArtista = obraArte.getId_artista();
        int idTecnica = obraArte.getId_Tecnica();
        int idMovimento = obraArte.getId_Estilo();
        int idMaterial = obraArte.getId_Material();

        Artista artista = null;
        Tecnica tecnica = null;
        Movimento movimento = null;
        Materiais material = null;

        // Retrieve artist information
        artista = model.getArtistById(idArtista);

        // Retrieve technique information
        tecnica = model.getTechniqueById(idTecnica);

        // Retrieve movement information
        movimento = model.getMovementById(idMovimento);

        // Retrieve material information
        material = model.getMaterialById(idMaterial);

        // Display the artwork details along with additional information
        view.showUpdateArtworkDetails(obraArte, artista, tecnica, movimento, material);
    }

    @Override
    public void doUpdateArtist() throws IOException {
        List<Artista> artistas = model.getArtists();
        view.showUpdateArtist(artistas);
    }

    @Override
    public void doUpdateArtistDetails(Artista artista) throws IOException {
        ExplorArtModel model = new ExplorArtModel();

        // Display the artist details along with additional information
        view.showUpdateArtistDetails(artista);
    }

    @Override
    public void addGalerist(Galerista galerista) throws IOException {
        model.addGalerist(galerista);
    }

    @Override
    public void modifyGallerist(int galleristId, Galerista galerista) throws IOException {
        model.modifyGallerist(galleristId, galerista);
    }

    @Override
    public void removeGallerist(int galleristId) throws IOException {
        model.removeGallerist(galleristId);
    }


    @Override
    public void addGallery(Galeria galeria) throws IOException {
        model.addGallery(galeria);
    }

    @Override
    public void modifyGallery(int galleryId, Galeria galeria) throws IOException {
        model.modifyGallery(galleryId, galeria);
    }

    @Override
    public void removeGallery(int galleryId) throws IOException {
        model.removeGallery(galleryId);
    }


    @Override
    public void addColaborador(Colaborador colaborador) throws IOException {
        model.addColaborador(colaborador);
    }

    @Override
    public void importDataFromArtsy(ImportArtsyView importArtsyView) {

            // Start the background task in the model or background module
            model.importDataFromArtsy();

            // Periodically update the progress bar in the view
            new Thread(() -> {
                while (model.getProgress() < 1.0) {
                    double progress = model.getProgress();
                    importArtsyView.updateProgressBar(progress);

                    try {
                        // Add a delay if needed
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Task is complete, perform any additional actions if needed
            }).start();
        }


    @Override
    public void deleteArtsyData() {
//TODO
    }

    public void doShowDetails(Evento evento) {
        // Create a new view or dialog to display the details of the Show
        // You need to implement the details view (e.g., ShowDetailsView) accordingly
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


    @Override
    public void doArtistDetails(Artista artista) {

        view.showArtistDetails(artista);

    }

    @Override
    public void doArtworkDetails(Obra_Arte obraArte) throws IOException {

        ExplorArtModel model = new ExplorArtModel();

        int idArtista = obraArte.getId_artista();
        int idTecnica = obraArte.getId_Tecnica();
        int idMovimento = obraArte.getId_Estilo();
        int idMaterial = obraArte.getId_Material();

        Artista artista = null;
        Tecnica tecnica = null;
        Movimento movimento = null;
        Materiais material = null;

        // Retrieve artist information
        artista = model.getArtistById(idArtista);

        // Retrieve technique information
        tecnica = model.getTechniqueById(idTecnica);

        // Retrieve movement information
        movimento = model.getMovementById(idMovimento);

        // Retrieve material information
        material = model.getMaterialById(idMaterial);

        // Display the artwork details along with additional information
        view.showArtworkDetails(obraArte, artista, tecnica, movimento, material);
    }


}
