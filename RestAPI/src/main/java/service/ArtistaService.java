/**
 * The {@code ArtistaService} class provides methods to perform CRUD operations on {@code Artista} entities.
 * It interacts with the {@code ArtistaRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Artistas</li>
 *     <li>Retrieve an Artista by ID</li>
 *     <li>Add a new Artista</li>
 *     <li>Update an existing Artista</li>
 *     <li>Delete an Artista</li>
 *     <li>Add a list of Artistas</li>
 * </ul>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Artista
 * @see ArtistaRepository
 */
package service;

import model.Artista;
import repository.ArtistaRepository;

import java.util.List;

public class ArtistaService {
    private final ArtistaRepository artistaRepository;

    /**
     * Constructs a new {@code ArtistaService} with the provided {@code ArtistaRepository}.
     *
     * @param artistaRepository The repository for {@code Artista} entities.
     */
    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    /**
     * Retrieves a list of all {@code Artista} entities.
     *
     * @return A list of all {@code Artista} entities.
     */
    public List<Artista> getAllArtists() {
        return artistaRepository.getAllArtistas();
    }

    /**
     * Retrieves an {@code Artista} entity by its ID.
     *
     * @param artistaId The ID of the {@code Artista} entity to retrieve.
     * @return The {@code Artista} entity with the specified ID.
     */
    public Artista getArtistaById(int artistaId) {
        return artistaRepository.getArtistaById(artistaId);
    }

    /**
     * Adds a new {@code Artista} entity.
     *
     * @param artista The {@code Artista} entity to add.
     * @return The added {@code Artista} entity.
     */
    public Artista addArtista(Artista artista) {
        return artistaRepository.addArtista(artista);
    }

    /**
     * Updates an existing {@code Artista} entity with the specified ID.
     *
     * @param id      The ID of the {@code Artista} entity to update.
     * @param artista The updated {@code Artista} entity.
     * @return The updated {@code Artista} entity.
     */
    public Artista updateArtista(int id, Artista artista) {
        return artistaRepository.updateArtista(id, artista);
    }

    /**
     * Deletes the {@code Artista} entity with the specified ID.
     *
     * @param id The ID of the {@code Artista} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteArtista(int id) {
        return artistaRepository.deleteArtista(id);
    }

    /**
     * Adds a list of {@code Artista} entities.
     *
     * @param artistasList The list of {@code Artista} entities to add.
     */
    public void addAllArtistas(List<Artista> artistasList) {
        for (Artista artista : artistasList) {
            artistaRepository.addArtista(artista);
        }
    }
}
