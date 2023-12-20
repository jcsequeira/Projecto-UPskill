/**
 * The {@code ObraArteService} class provides methods to perform CRUD operations on {@code Obra_Arte} entities.
 * It interacts with the {@code ObraArteRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Obras de Arte</li>
 *     <li>Retrieve an Obra de Arte by ID</li>
 *     <li>Add a new Obra de Arte</li>
 *     <li>Update an existing Obra de Arte</li>
 *     <li>Delete an Obra de Arte</li>
 *     <li>Add a list of Obras de Arte</li>
 * </ul>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Obra_Arte
 * @see ObraArteRepository
 */
package service;

import model.Obra_Arte;
import repository.ObraArteRepository;

import java.util.List;

public class ObraArteService {
    private final ObraArteRepository obraArteRepository;

    /**
     * Constructs a new {@code ObraArteService} with the provided {@code ObraArteRepository}.
     *
     * @param obraArteRepository The repository for {@code Obra_Arte} entities.
     */
    public ObraArteService(ObraArteRepository obraArteRepository) {
        this.obraArteRepository = obraArteRepository;
    }

    /**
     * Retrieves a list of all {@code Obra_Arte} entities.
     *
     * @return A list of all {@code Obra_Arte} entities.
     */
    public List<Obra_Arte> getAllObraArte() {
        return obraArteRepository.getAllObraArte();
    }

    /**
     * Retrieves an {@code Obra_Arte} entity by its ID.
     *
     * @param obraArteId The ID of the {@code Obra_Arte} entity to retrieve.
     * @return The {@code Obra_Arte} entity with the specified ID.
     */
    public Obra_Arte getObraArteById(int obraArteId) {
        return obraArteRepository.getObraArteById(obraArteId);
    }

    /**
     * Adds a new {@code Obra_Arte} entity.
     *
     * @param newObraArte The {@code Obra_Arte} entity to add.
     * @return The added {@code Obra_Arte} entity.
     */
    public Obra_Arte addObraArte(Obra_Arte newObraArte) {
        return obraArteRepository.addObraArte(newObraArte);
    }

    /**
     * Updates an existing {@code Obra_Arte} entity with the specified ID.
     *
     * @param obraArteId      The ID of the {@code Obra_Arte} entity to update.
     * @param updatedObraArte The updated {@code Obra_Arte} entity.
     * @return The updated {@code Obra_Arte} entity.
     */
    public Obra_Arte updateObraArte(int obraArteId, Obra_Arte updatedObraArte) {
        return obraArteRepository.updateObraArte(obraArteId, updatedObraArte);
    }

    /**
     * Deletes the {@code Obra_Arte} entity with the specified ID.
     *
     * @param obraArteId The ID of the {@code Obra_Arte} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteObraArte(int obraArteId) {
        return obraArteRepository.deleteObraArte(obraArteId);
    }

    /**
     * Adds a list of {@code Obra_Arte} entities.
     *
     * @param obraArteList The list of {@code Obra_Arte} entities to add.
     */
    public void addAllObrasArte(List<Obra_Arte> obraArteList) {
        for (Obra_Arte obraArte : obraArteList) {
            obraArteRepository.addObraArte(obraArte);
        }
    }
}
