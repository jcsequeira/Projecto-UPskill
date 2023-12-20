
package service;

import model.Pais;
import repository.PaisRepository;

import java.util.List;
/**
 * The {@code PaisService} class provides methods to perform CRUD operations on {@code Pais} entities.
 * It interacts with the {@code PaisRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Paises</li>
 *     <li>Retrieve a Pais by ID</li>
 *     <li>Add a new Pais</li>
 *     <li>Update an existing Pais</li>
 *     <li>Delete a Pais</li>
 *     <li>Add a list of Paises</li>
 * </ul>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Pais
 * @see PaisRepository
 */
public class PaisService {

    private final PaisRepository paisRepository;

    /**
     * Constructs a new {@code PaisService} with the provided {@code PaisRepository}.
     *
     * @param paisRepository The repository for {@code Pais} entities.
     */
    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    /**
     * Retrieves a list of all {@code Pais} entities.
     *
     * @return A list of all {@code Pais} entities.
     */
    public List<Pais> getAllPais() {
        return paisRepository.getAllPais();
    }

    /**
     * Retrieves a {@code Pais} entity by its ID.
     *
     * @param paisId The ID of the {@code Pais} entity to retrieve.
     * @return The {@code Pais} entity with the specified ID.
     */
    public Pais getPaisById(int paisId) {
        return paisRepository.getPaisById(paisId);
    }

    /**
     * Adds a new {@code Pais} entity.
     *
     * @param pais The {@code Pais} entity to add.
     * @return The added {@code Pais} entity.
     */
    public Pais addPais(Pais pais) {
        // You can add additional validation logic before saving to the database if needed
        // For example, check if the fields are not empty
        // Save to the database
        return paisRepository.addPais(pais);
    }

    /**
     * Updates an existing {@code Pais} entity with the specified ID.
     *
     * @param id   The ID of the {@code Pais} entity to update.
     * @param pais The updated {@code Pais} entity.
     * @return The updated {@code Pais} entity.
     */
    public Pais updatePais(int id, Pais pais) {
        // You might want to add validation logic or additional checks before updating
        return paisRepository.updatePais(id, pais);
    }

    /**
     * Deletes the {@code Pais} entity with the specified ID.
     *
     * @param id The ID of the {@code Pais} entity to delete.
     */
    public void deletePais(int id) {
        paisRepository.deletePais(id);
    }

    /**
     * Adds a list of {@code Pais} entities.
     *
     * @param paisesList The list of {@code Pais} entities to add.
     */
    public void addAllPaises(List<Pais> paisesList) {
        for (Pais pais : paisesList) {
            paisRepository.addPais(pais);
        }
    }
}
