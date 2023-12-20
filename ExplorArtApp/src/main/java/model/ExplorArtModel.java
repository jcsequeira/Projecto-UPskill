package model;

import apiservice.ApiService;
import presenter.ExplorArtContract;

import java.io.IOException;
import java.util.List;

/**
 * The {@code ExplorArtModel} class implements the Model layer in the ExplorArt application.
 * It communicates with the API service to perform various operations on artists, artworks, events, galleries, etc.
 */
public class ExplorArtModel implements ExplorArtContract.Model {

    /**
     * Retrieves a list of artists from the API.
     *
     * @return The list of artists.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public List<Artista> getArtists() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/artistas", Artista.class);
    }

    /**
     * Adds a new artist to the API.
     *
     * @param artista The artist to be added.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void addArtist(Artista artista) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/artistas", artista);
    }

    /**
     * Modifies an existing artist in the API.
     *
     * @param artistId The ID of the artist to be modified.
     * @param artista The updated artist information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void modifyArtist(int artistId, Artista artista) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/artistas/" + artistId, artista);
    }

    /**
     * Removes an artist from the API.
     *
     * @param artistId The ID of the artist to be removed.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void removeArtist(int artistId) throws IOException {
        ApiService.deleteToRestApi("http://localhost:4567/api/artistas/" + artistId);
    }

    /**
     * Retrieves a list of artworks from the API.
     *
     * @return The list of artworks.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public List<Obra_Arte> getArtworks() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/obrasarte", Obra_Arte.class);
    }

    /**
     * Adds a new artwork to the API.
     *
     * @param obraArte The artwork to be added.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void addArtwork(Obra_Arte obraArte) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/obrasarte", obraArte);
    }

    /**
     * Modifies an existing artwork in the API.
     *
     * @param artworkId The ID of the artwork to be modified.
     * @param obraArte The updated artwork information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void modifyArtwork(int artworkId, Obra_Arte obraArte) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/obrasarte/" + artworkId, obraArte);
    }

    /**
     * Retrieves a list of events from the API.
     *
     * @return The list of events.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public List<Evento> getEvents() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/eventos", Evento.class);
    }

    /**
     * Adds a new event to the API.
     *
     * @param evento The event to be added.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void addShow(Evento evento) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/eventos", evento);
    }

    /**
     * Modifies an existing event in the API.
     *
     * @param showId The ID of the event to be modified.
     * @param evento The updated event information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void modifyShow(int showId, Evento evento) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/eventos/" + showId, evento);
    }
    /**
     * Removes a show associated with the specified exhibition ID by making a DELETE request to a REST API endpoint.
     *
     * @param idExpo The ID of the exhibition from which to remove the show.
     * @throws IOException If an I/O error occurs while making the DELETE request.
     */
    @Override
    public void removeShow(int idExpo) throws IOException {
        ApiService.deleteToRestApi("http://localhost:4567/api/eventos/" + idExpo);
    }

    /**
     * Retrieves a list of galleries from the API.
     *
     * @return The list of galleries.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public List<Galeria> getGalleries() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/galerias", Galeria.class);
    }

    /**
     * Retrieves a list of gallerists from the API.
     *
     * @return The list of gallerists.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public List<Galerista> getGallerists() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/galeristas", Galerista.class);
    }

    /**
     * Adds a new gallerist to the API.
     *
     * @param galerista The gallerist to be added.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void addGalerist(Galerista galerista) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/galeristas", galerista);
    }

    /**
     * Modifies an existing gallerist in the API.
     *
     * @param galleristId The ID of the gallerist to be modified.
     * @param galerista The updated gallerist information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void modifyGallerist(int galleristId, Galerista galerista) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/galeristas/" + galleristId, galerista);
    }

    /**
     * Retrieves information about a specific gallery from the API.
     *
     * @param galleryId The ID of the gallery.
     * @return The gallery information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public Galeria getGalleryById(int galleryId) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/galerias/" + galleryId, Galeria.class);
    }

    /**
     * Adds a new gallery to the API.
     *
     * @param galeria The gallery to be added.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void addGallery(Galeria galeria) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/galerias", galeria);
    }

    /**
     * Modifies an existing gallery in the API.
     *
     * @param galleryId The ID of the gallery to be modified.
     * @param galeria The updated gallery information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void modifyGallery(int galleryId, Galeria galeria) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/galerias/" + galleryId, galeria);
    }

    /**
     * Removes a gallery from the API.
     *
     * @param galleryId The ID of the gallery to be removed.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public void removeGallery(int galleryId) throws IOException {
        ApiService.deleteToRestApi("http://localhost:4567/api/galerias/" + galleryId);
    }

    /**
     * Retrieves information about a specific artist from the API.
     *
     * @param idArtista The ID of the artist.
     * @return The artist information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public Artista getArtistById(int idArtista) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/artistas/" + idArtista, Artista.class);
    }

    /**
     * Retrieves a list of techniques from the API.
     *
     * @return The list of techniques.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public List<Tecnica> getTechnics() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/tecnicas", Tecnica.class);
    }

    /**
     * Retrieves information about a specific technique from the API.
     *
     * @param idTecnica The ID of the technique.
     * @return The technique information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public Tecnica getTechniqueById(int idTecnica) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/tecnicas/" + idTecnica, Tecnica.class);
    }

    /**
     * Retrieves a list of movements from the API.
     *
     * @return The list of movements.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public List<Movimento> getMovement() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/movimentos", Movimento.class);
    }

    /**
     * Retrieves information about a specific movement from the API.
     *
     * @param idMovimento The ID of the movement.
     * @return The movement information.
     * @throws IOException If an I/O error occurs during the API call.
     */
    @Override
    public Movimento getMovementById(int idMovimento) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/movimentos/" + idMovimento, Movimento.class);
    }

    /**
     * Retrieves a list of materials from the API.
     *
     * @return A list of {@code Materiais} objects representing materials.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public List<Materiais> getMaterials() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/materiais", Materiais.class);
    }

    /**
     * Retrieves a specific material by its ID from the API.
     *
     * @param idMaterial The ID of the material to retrieve.
     * @return A {@code Materiais} object representing the requested material.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public Materiais getMaterialById(int idMaterial) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/materiais/" + idMaterial, Materiais.class);
    }

    /**
     * Retrieves a list of collaborators from the API.
     *
     * @return A list of {@code Colaborador} objects representing collaborators.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public List<Colaborador> getColaboradores() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/colaboradores", Colaborador.class);
    }

    /**
     * Retrieves a specific collaborator by their ID from the API.
     *
     * @param id_colaborador The ID of the collaborator to retrieve.
     * @return A {@code Colaborador} object representing the requested collaborator.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    public Colaborador getColaboradorById(int id_colaborador) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/colaboradores/" + id_colaborador, Colaborador.class);
    }

    /**
     * Adds a new collaborator to the API.
     *
     * @param colaborador The {@code Colaborador} object representing the collaborator to add.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public void addColaborador(Colaborador colaborador) throws IOException {
        ApiService.postToRestApi("http://localhost:4567/api/colaboradores", colaborador);
    }

    /**
     * Retrieves a country by its ID from the API.
     *
     * @param codigoPais The ID of the country to retrieve.
     * @return A {@code Pais} object representing the requested country.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public Pais getPaisById(int codigoPais) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/paises/" + codigoPais, Pais.class);
    }

    /**
     * Retrieves a list of countries from the API.
     *
     * @return A list of {@code Pais} objects representing countries.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public List<Pais> getPaises() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/paises", Pais.class);
    }

    /**
     * Retrieves a list of cities from the API.
     *
     * @return A list of {@code Cidade} objects representing cities.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public List<Cidade> getCidades() throws IOException {
        return ApiService.getAllItems("http://localhost:4567/api/cidades", Cidade.class);
    }

    /**
     * Retrieves a specific city by its ID from the API.
     *
     * @param id_Cidade The ID of the city to retrieve.
     * @return A {@code Cidade} object representing the requested city.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public Cidade getCidadeById(int id_Cidade) throws IOException {
        return ApiService.getItem("http://localhost:4567/api/cidades/" + id_Cidade, Cidade.class);
    }

    /**
     * Deletes Artsy-related data from the API.
     *
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public void deleteArtsyData() throws IOException {
        ApiService.triggerDeleteAllArtsyInRestApi("http://localhost:4567/api/cleanartsydata");
    }

    /**
     * Modifies a collaborator in the API.
     *
     * @param colabId      The ID of the collaborator to modify.
     * @param colaborador  The {@code Colaborador} object representing the modified collaborator.
     * @throws IOException if an I/O error occurs while making the API request.
     */
    @Override
    public void modifyColab(int colabId, Colaborador colaborador) throws IOException {
        ApiService.putToRestApi("http://localhost:4567/api/colaboradores/" + colabId, colaborador);
    }
}
