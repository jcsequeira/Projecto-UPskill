
package service;

import model.Tecnica;
import repository.TecnicaRepository;

import java.util.List;
/**
 * The {@code TecnicaService} class provides methods to perform CRUD operations on {@code Tecnica} entities.
 * It interacts with the {@code TecnicaRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Tecnicas</li>
 *     <li>Retrieve a Tecnica by ID</li>
 *     <li>Add a new Tecnica</li>
 *     <li>Update an existing Tecnica</li>
 *     <li>Delete a Tecnica</li>
 *     <li>Add a list of Tecnicas</li>
 * </ul>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Tecnica
 * @see TecnicaRepository
 */
public class TecnicaService {

    private final TecnicaRepository tecnicaRepository;

    /**
     * Constructs a new {@code TecnicaService} with the provided {@code TecnicaRepository}.
     *
     * @param tecnicaRepository The repository for {@code Tecnica} entities.
     */
    public TecnicaService(TecnicaRepository tecnicaRepository) {
        this.tecnicaRepository = tecnicaRepository;
    }

    /**
     * Retrieves a list of all {@code Tecnica} entities.
     *
     * @return A list of all {@code Tecnica} entities.
     */
    public List<Tecnica> getAllTecnicas() {
        return tecnicaRepository.getAllTecnicas();
    }

    /**
     * Retrieves a {@code Tecnica} entity by its ID.
     *
     * @param tecnicaId The ID of the {@code Tecnica} entity to retrieve.
     * @return The {@code Tecnica} entity with the specified ID.
     */
    public Tecnica getTecnicaById(int tecnicaId) {
        return tecnicaRepository.getTecnicaById(tecnicaId);
    }

    /**
     * Adds a new {@code Tecnica} entity.
     *
     * @param tecnica The {@code Tecnica} entity to add.
     * @return The added {@code Tecnica} entity.
     */
    public Tecnica addTecnica(Tecnica tecnica) {
        // You can add additional validation logic before saving to the database if needed
        // For example, check if the fields are not empty
        // Save to the database
        return tecnicaRepository.addTecnica(tecnica);
    }

    /**
     * Updates an existing {@code Tecnica} entity with the specified ID.
     *
     * @param id      The ID of the {@code Tecnica} entity to update.
     * @param tecnica The updated {@code Tecnica} entity.
     * @return The updated {@code Tecnica} entity.
     */
    public Tecnica updateTecnica(int id, Tecnica tecnica) {
        // You might want to add validation logic or additional checks before updating
        return tecnicaRepository.updateTecnica(id, tecnica);
    }

    /**
     * Deletes the {@code Tecnica} entity with the specified ID.
     *
     * @param id The ID of the {@code Tecnica} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteTecnica(int id) {
        return tecnicaRepository.deleteTecnica(id);
    }

    /**
     * Adds a list of {@code Tecnica} entities.
     *
     * @param tecnicasList The list of {@code Tecnica} entities to add.
     */
    public void addAllTecnicas(List<Tecnica> tecnicasList) {
        for (Tecnica tecnica : tecnicasList) {
            tecnicaRepository.addTecnica(tecnica);
        }
    }
}
