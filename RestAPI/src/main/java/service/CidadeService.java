/**
 * The {@code CidadeService} class provides methods to perform CRUD operations on {@code Cidade} entities.
 * It interacts with the {@code CidadeRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Cidades</li>
 *     <li>Retrieve a Cidade by ID</li>
 *     <li>Add a new Cidade</li>
 *     <li>Update an existing Cidade</li>
 *     <li>Delete a Cidade</li>
 *     <li>Add a list of Cidades</li>
 * </ul>
 * </p>
 * <p>
 * The class includes validation checks for mandatory fields before adding or updating Cidades.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Cidade
 * @see CidadeRepository
 * @see ServiceException
 */
package service;

import exceptions.ServiceException;
import model.Cidade;
import repository.CidadeRepository;

import java.util.List;

public class CidadeService {

    private final CidadeRepository cidadeRepository;

    /**
     * Constructs a new {@code CidadeService} with the provided {@code CidadeRepository}.
     *
     * @param cidadeRepository The repository for {@code Cidade} entities.
     */
    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    /**
     * Retrieves a list of all {@code Cidade} entities.
     *
     * @return A list of all {@code Cidade} entities.
     */
    public List<Cidade> getAllCidade() {
        return cidadeRepository.getAllCidade();
    }

    /**
     * Retrieves a {@code Cidade} entity by its ID.
     *
     * @param cidadeId The ID of the {@code Cidade} entity to retrieve.
     * @return The {@code Cidade} entity with the specified ID.
     */
    public Cidade getCidadeById(int cidadeId) {
        return cidadeRepository.getCidadeById(cidadeId);
    }

    /**
     * Adds a new {@code Cidade} entity.
     *
     * @param cidade The {@code Cidade} entity to add.
     * @return The added {@code Cidade} entity.
     * @throws ServiceException If the mandatory field {@code Nome_Cidade} is null.
     */
    public Cidade addCidade(Cidade cidade) {
        if (cidade.getNome_Cidade() == null) {
            throw new ServiceException("Nome_Cidade can't be null.");
        }
        return cidadeRepository.addCidade(cidade);
    }

    /**
     * Updates an existing {@code Cidade} entity with the specified ID.
     *
     * @param id     The ID of the {@code Cidade} entity to update.
     * @param cidade The updated {@code Cidade} entity.
     * @return The updated {@code Cidade} entity.
     * @throws ServiceException If the mandatory field {@code Nome_Cidade} is null.
     */
    public Cidade updateCidade(int id, Cidade cidade) {
        if (cidade.getNome_Cidade() == null) {
            throw new ServiceException("Nome_Cidade can't be null.");
        }
        return cidadeRepository.updateCidade(id, cidade);
    }

    /**
     * Deletes the {@code Cidade} entity with the specified ID.
     *
     * @param id The ID of the {@code Cidade} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteCidade(int id) {
        return cidadeRepository.deleteCidade(id);
    }

    /**
     * Adds a list of {@code Cidade} entities.
     *
     * @param cidadeList The list of {@code Cidade} entities to add.
     */
    public void addAllCidades(List<Cidade> cidadeList) {
        for (Cidade cidade : cidadeList) {
            cidadeRepository.addCidade(cidade);
        }
    }
}
