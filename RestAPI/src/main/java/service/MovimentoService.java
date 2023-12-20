
package service;

import model.Movimento;
import repository.MovimentoRepository;

import java.util.List;
/**
 * The {@code MovimentoService} class provides methods to perform CRUD operations on {@code Movimento} entities.
 * It interacts with the {@code MovimentoRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Movimentos</li>
 *     <li>Retrieve a Movimento by ID</li>
 *     <li>Add a new Movimento</li>
 *     <li>Update an existing Movimento</li>
 *     <li>Delete a Movimento</li>
 *     <li>Add a list of Movimentos</li>
 * </ul>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Movimento
 * @see MovimentoRepository
 */
public class MovimentoService {

    private final MovimentoRepository movimentoRepository;

    /**
     * Constructs a new {@code MovimentoService} with the provided {@code MovimentoRepository}.
     *
     * @param movimentoRepository The repository for {@code Movimento} entities.
     */
    public MovimentoService(MovimentoRepository movimentoRepository) {
        this.movimentoRepository = movimentoRepository;
    }

    /**
     * Retrieves a list of all {@code Movimento} entities.
     *
     * @return A list of all {@code Movimento} entities.
     */
    public List<Movimento> getAllMovimentos() {
        return movimentoRepository.getAllMovimentos();
    }

    /**
     * Retrieves a {@code Movimento} entity by its ID.
     *
     * @param movimentoId The ID of the {@code Movimento} entity to retrieve.
     * @return The {@code Movimento} entity with the specified ID.
     */
    public Movimento getMovimentoById(int movimentoId) {
        return movimentoRepository.getMovimentoById(movimentoId);
    }

    /**
     * Adds a new {@code Movimento} entity.
     *
     * @param movimento The {@code Movimento} entity to add.
     * @return The added {@code Movimento} entity.
     */
    public Movimento addMovimento(Movimento movimento) {
        // You can add additional validation logic before saving to the database if needed
        // For example, check if the fields are not empty
        // Save to the database
        return movimentoRepository.addMovimento(movimento);
    }

    /**
     * Updates an existing {@code Movimento} entity with the specified ID.
     *
     * @param id        The ID of the {@code Movimento} entity to update.
     * @param movimento The updated {@code Movimento} entity.
     * @return The updated {@code Movimento} entity.
     */
    public Movimento updateMovimento(int id, Movimento movimento) {
        // You might want to add validation logic or additional checks before updating
        return movimentoRepository.updateMovimento(id, movimento);
    }

    /**
     * Deletes the {@code Movimento} entity with the specified ID.
     *
     * @param id The ID of the {@code Movimento} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteMovimento(int id) {
        return movimentoRepository.deleteMovimento(id);
    }

    /**
     * Adds a list of {@code Movimento} entities.
     *
     * @param movimentosList The list of {@code Movimento} entities to add.
     */
    public void addAllMovimentos(List<Movimento> movimentosList) {
        for (Movimento movimento : movimentosList) {
            movimentoRepository.addMovimento(movimento);
        }
    }
}
