package model;


import apiservice.ApiService;


import presenter.ExplorArtContract;

import java.io.IOException;
import java.util.List;

public class ExplorArtModel implements ExplorArtContract.Model {

    private double progress;
    @Override
    public List<Artista> getArtists() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/artistas", Artista.class);
    }

    @Override
    public void addArtist(Artista artista) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/artistas", artista);
    }

    @Override
    public void modifyArtist(int artistId, Artista artista) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/artistas/" + artistId, artista);
    }

    @Override
    public void removeArtist(int artistId) throws IOException {
        ApiService.deleteToRestApi("http://localhost:4567/api/artistas/" + artistId);
    }

    @Override
    public List<Obra_Arte> getArtworks() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/obrasarte", Obra_Arte.class);
    }

    @Override
    public void addArtwork(Obra_Arte obraArte) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/obrasarte", obraArte);
    }

    @Override
    public void ativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/obrasarte/" + artworkId, obraArte);
    }

    @Override
    public void deativateArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/obrasarte/" + artworkId, obraArte);
    }

    @Override
    public void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/obrasarte/" + artworkId, obraArte);
    }

    @Override
    public List<Evento> getEvents() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/eventos", Evento.class);
    }

    @Override
    public void addShow(Evento evento) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/eventos", evento);
    }

    @Override
    public void modifyShow(int showId, Evento evento) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/eventos/" + showId, evento);
    }

    @Override
    public void removeShow(int showId) throws IOException {
        ApiService.deleteToRestApi("http://localhost:4567/api/eventos/" + showId);
    }

    @Override
    public List<Galeria> getGalleries() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/galerias", Galeria.class);
    }

    @Override
    public List<Galerista> getGallerists() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/galeristas", Galerista.class);
    }

    @Override
    public void addGalerist(Galerista galerista) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/galeristas", galerista);
    }

    @Override
    public void modifyGallerist(int galleristId, Galerista galerista) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/galeristas/" + galleristId, galerista);
    }

    @Override
    public void removeGallerist(int galleristId) throws IOException {
        ApiService.deleteToRestApi("http://localhost:4567/api/galeristas/" + galleristId);
    }

    @Override
    public Galeria getGalleryById(int galleryId) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/galerias/" + galleryId, Galeria.class);
    }

    @Override
    public void addGallery(Galeria galeria) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/galerias", galeria);
    }

    @Override
    public void modifyGallery(int galleryId, Galeria galeria) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/galerias/" + galleryId, galeria);
    }

    @Override
    public void removeGallery(int galleryId) throws IOException {
        ApiService.deleteToRestApi("http://localhost:4567/api/galerias/" + galleryId);
    }

    @Override
    public Artista getArtistById(int idArtista) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/artistas/" + idArtista, Artista.class);
    }

    @Override
    public List<Tecnica> getTechnics() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/tecnicas", Tecnica.class);
    }

    @Override
    public Tecnica getTechniqueById(int idTecnica) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/tecnicas/" + idTecnica, Tecnica.class);
    }

    @Override
    public List<Movimento> getMovement() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/movimentos", Movimento.class);
    }

    @Override
    public Movimento getMovementById(int idMovimento) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/movimentos/" + idMovimento, Movimento.class);
    }

    @Override
    public List<Materiais> getMaterials() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/materiais", Materiais.class);
    }

    @Override
    public Materiais getMaterialById(int idMaterial) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/materiais/" + idMaterial, Materiais.class);
    }

    @Override
    public List<Colaborador> getColaboradores() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/colaboradores", Colaborador.class);
    }

    @Override
    public void addColaborador(Colaborador colaborador) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/colaboradores", colaborador);
    }

    @Override
    public Pais getPaisById(int codigoPais) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/paises/" + codigoPais, Pais.class);
    }

    @Override
    public List<Pais> getPaises() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/paises", Pais.class);
    }

    @Override
    public List<Cidade> getCidades() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/cidades", Cidade.class);
    }


    public void importDataFromArtsy()  {
        // Your background task logic
        // Update the 'progress' variable as the task progresses
        //PopulateRun.doPopulate(this);
        setProgress(0.5);
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void updateProgress(double progress) {
        setProgress(progress);
    }

    public double getProgress() {
        return progress;
    }


    @Override
    public void deleteArtsyData() throws IOException {
        ApiService.triggerDeleteAllArtsyInRestApi("http://localhost:4567/api/cleanartsydata");
    }
}
