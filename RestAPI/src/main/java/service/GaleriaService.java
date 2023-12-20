/**
 * The {@code GaleriaService} class provides methods to perform CRUD operations on {@code Galeria} entities.
 * It interacts with the {@code GaleriaRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Galerias</li>
 *     <li>Retrieve a Galeria by ID</li>
 *     <li>Add a new Galeria</li>
 *     <li>Update an existing Galeria</li>
 *     <li>Delete a Galeria</li>
 *     <li>Add a list of Galerias</li>
 * </ul>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Galeria
 * @see GaleriaRepository
 */
package service;

import model.Galeria;
import repository.GaleriaRepository;

import java.util.List;

public class GaleriaService {
    private final GaleriaRepository galeriaRepository;

    /**
     * Constructs a new {@code GaleriaService} with the provided {@code GaleriaRepository}.
     *
     * @param galeriaRepository The repository for {@code Galeria} entities.
     */
    public GaleriaService(GaleriaRepository galeriaRepository) {
        this.galeriaRepository = galeriaRepository;
    }

    /**
     * Retrieves a list of all {@code Galeria} entities.
     *
     * @return A list of all {@code Galeria} entities.
     */
    public List<Galeria> getAllGaleria() {
        return galeriaRepository.getAllGaleria();
    }

    /**
     * Retrieves a {@code Galeria} entity by its ID.
     *
     * @param galeriaId The ID of the {@code Galeria} entity to retrieve.
     * @return The {@code Galeria} entity with the specified ID.
     */
    public Galeria getGaleriaById(int galeriaId) {
        return galeriaRepository.getGaleriaById(galeriaId);
    }

    /**
     * Adds a new {@code Galeria} entity.
     *
     * @param newGaleria The {@code Galeria} entity to add.
     * @return The added {@code Galeria} entity.
     */
    public Galeria addGaleria(Galeria newGaleria) {
        return galeriaRepository.addGaleria(newGaleria);
    }

    /**
     * Updates an existing {@code Galeria} entity with the specified ID.
     *
     * @param galeriaId      The ID of the {@code Galeria} entity to update.
     * @param updatedGaleria The updated {@code Galeria} entity.
     * @return The updated {@code Galeria} entity.
     */
    public Galeria updateGaleria(int galeriaId, Galeria updatedGaleria) {
        return galeriaRepository.updateGaleria(galeriaId, updatedGaleria);
    }

    /**
     * Deletes the {@code Galeria} entity with the specified ID.
     *
     * @param galeriaId The ID of the {@code Galeria} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteGaleria(int galeriaId) {
        return galeriaRepository.deleteGaleria(galeriaId);
    }

    /**
     * Adds a list of {@code Galeria} entities.
     *
     * @param galeriasList The list of {@code Galeria} entities to add.
     */
    public void addAllGalerias(List<Galeria> galeriasList) {
        for (Galeria galeria : galeriasList) {
            galeriaRepository.addGaleria(galeria);
        }
    }
}
