package presenter;

// Presenter (no pacote presenter)

import model.*;

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
    public void addArtwork(Obra_Arte obraArte) throws IOException {
        model.addArtwork(obraArte);
    }

    @Override
    public void ativateArtwork() {
        // TODO
    }


    @Override
    public void deativateArtwork() {
        //TODO
    }

    @Override
    public void addArtist(Artista artista) throws IOException {
        model.addArtist(artista);
    }

    @Override
    public void doAddArtist() {
        Artista artista = new Artista();
        view.showAddArtistForm(artista);
    }

    @Override
    public void updateArtist() {
        //TODO
    }

    @Override
    public void removeArtist() {
        //TODO
    }

    @Override
    public void addShow(Evento evento) throws IOException {
        model.addShow(evento);
    }




    @Override
    public void doAddShow() {
        Evento evento = new Evento();
        view.showAddShowForm(evento);
    }

    @Override
    public void updateShow() {
        //TODO
    }

    @Override
    public void removeShow() {
        //TODO
    }

    @Override
    public void addGalerist(Galerista galerista) throws IOException {
        model.addGalerist(galerista);
    }


    @Override
    public void updateGalerist() {
//TODO
    }

    @Override
    public void removeGalerist() {
//TODO
    }

    @Override
    public void addGallery() {
//TODO
    }

    @Override
    public void updateGallery() {
//TODO
    }

    @Override
    public void removeGallery() {
//TODO
    }

    @Override
    public void importDataFromArtsy() {
//TODO
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
