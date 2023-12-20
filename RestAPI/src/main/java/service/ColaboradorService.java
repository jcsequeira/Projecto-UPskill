/**
 * The {@code ColaboradorService} class provides methods to perform CRUD operations on {@code Colaborador} entities.
 * It interacts with the {@code ColaboradorRepository} for database operations and enforces validation rules.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Colaboradores</li>
 *     <li>Retrieve a Colaborador by ID</li>
 *     <li>Add a new Colaborador</li>
 *     <li>Update an existing Colaborador</li>
 *     <li>Delete a Colaborador</li>
 * </ul>
 * </p>
 * <p>
 * The class includes validation checks for mandatory fields before adding or updating Colaboradores.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Colaborador
 * @see ColaboradorRepository
 * @see ServiceException
 */
package service;

import exceptions.ServiceException;
import model.Colaborador;
import repository.ColaboradorRepository;

import java.util.List;

public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepository;

    /**
     * Constructs a new {@code ColaboradorService} with the provided {@code ColaboradorRepository}.
     *
     * @param colaboradorRepository The repository for {@code Colaborador} entities.
     */
    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    /**
     * Retrieves a list of all {@code Colaborador} entities.
     *
     * @return A list of all {@code Colaborador} entities.
     */
    public List<Colaborador> getAllColaboradores() {
        return colaboradorRepository.getAllColaboradores();
    }

    /**
     * Retrieves a {@code Colaborador} entity by its ID.
     *
     * @param colaboradorId The ID of the {@code Colaborador} entity to retrieve.
     * @return The {@code Colaborador} entity with the specified ID.
     */
    public Colaborador getColaboradorById(int colaboradorId) {
        return colaboradorRepository.getColaboradorById(colaboradorId);
    }

    /**
     * Adds a new {@code Colaborador} entity.
     *
     * @param colaborador The {@code Colaborador} entity to add.
     * @return The added {@code Colaborador} entity.
     * @throws ServiceException If mandatory fields are null or Codigo_Pais is less than or equal to 0.
     */
    public Colaborador addColaborador(Colaborador colaborador) {
        try {
            validateColaboradorFields(colaborador);
            return colaboradorRepository.addColaborador(colaborador);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Updates an existing {@code Colaborador} entity with the specified ID.
     *
     * @param id          The ID of the {@code Colaborador} entity to update.
     * @param colaborador The updated {@code Colaborador} entity.
     * @return The updated {@code Colaborador} entity.
     * @throws ServiceException If mandatory fields are null or Codigo_Pais is less than or equal to 0.
     */
    public Colaborador updateColaborador(int id, Colaborador colaborador) {
        try {
            validateColaboradorFields(colaborador);
            return colaboradorRepository.updateColaborador(id, colaborador);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void validateColaboradorFields(Colaborador colaborador) {
        if (colaborador.getNome_Colaborador() == null ||
                colaborador.getEmail() == null ||
                colaborador.getTelefone() == null ||
                colaborador.getCodigo_Pais() <= 0) {
            throw new ServiceException("All colaborador fields are mandatory!");
        }
    }

    /**
     * Deletes the {@code Colaborador} entity with the specified ID.
     *
     * @param id The ID of the {@code Colaborador} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteColaborador(int id) {
        return colaboradorRepository.deleteColaborador(id);
    }
}
