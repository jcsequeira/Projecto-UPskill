/**
 * The {@code MateriaisService} class provides methods to perform CRUD operations on {@code Materiais} entities.
 * It interacts with the {@code MateriaisRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Materiais</li>
 *     <li>Retrieve a Material by ID</li>
 *     <li>Add a new Material</li>
 *     <li>Update an existing Material</li>
 *     <li>Delete a Material</li>
 *     <li>Add a list of Materiais</li>
 * </ul>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Materiais
 * @see MateriaisRepository
 */
package service;

import model.Materiais;
import repository.MateriaisRepository;

import java.util.List;

public class MateriaisService {

    private final MateriaisRepository materiaisRepository;

    /**
     * Constructs a new {@code MateriaisService} with the provided {@code MateriaisRepository}.
     *
     * @param materiaisRepository The repository for {@code Materiais} entities.
     */
    public MateriaisService(MateriaisRepository materiaisRepository) {
        this.materiaisRepository = materiaisRepository;
    }

    /**
     * Retrieves a list of all {@code Materiais} entities.
     *
     * @return A list of all {@code Materiais} entities.
     */
    public List<Materiais> getAllMateriais() {
        return materiaisRepository.getAllMateriais();
    }

    /**
     * Retrieves a {@code Materiais} entity by its ID.
     *
     * @param materialId The ID of the {@code Materiais} entity to retrieve.
     * @return The {@code Materiais} entity with the specified ID.
     */
    public Materiais getMaterialById(int materialId) {
        return materiaisRepository.getMaterialById(materialId);
    }

    /**
     * Adds a new {@code Materiais} entity.
     *
     * @param material The {@code Materiais} entity to add.
     * @return The added {@code Materiais} entity.
     */
    public Materiais addMaterial(Materiais material) {
        return materiaisRepository.addMaterial(material);
    }

    /**
     * Updates an existing {@code Materiais} entity with the specified ID.
     *
     * @param id       The ID of the {@code Materiais} entity to update.
     * @param material The updated {@code Materiais} entity.
     * @return The updated {@code Materiais} entity.
     */
    public Materiais updateMaterial(int id, Materiais material) {
        return materiaisRepository.updateMaterial(id, material);
    }

    /**
     * Deletes the {@code Materiais} entity with the specified ID.
     *
     * @param id The ID of the {@code Materiais} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteMaterial(int id) {
        return materiaisRepository.deleteMaterial(id);
    }

    /**
     * Adds a list of {@code Materiais} entities.
     *
     * @param materiaisList The list of {@code Materiais} entities to add.
     */
    public void addAllMateriais(List<Materiais> materiaisList) {
        for (Materiais materiais : materiaisList) {
            materiaisRepository.addMaterial(materiais);
        }
    }
}
