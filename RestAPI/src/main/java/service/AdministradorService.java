
package service;

import exceptions.ServiceException;
import model.Administrador;
import repository.AdministradorRepository;
import repository.ColaboradorRepository;
import repository.DBConnection;

import java.sql.Connection;
import java.util.List;
/**
 * The {@code AdministradorService} class provides methods to perform CRUD operations on {@code Administrador} entities.
 * It interacts with the {@code AdministradorRepository} for database operations and enforces validation rules.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Administradores</li>
 *     <li>Retrieve an Administrador by ID</li>
 *     <li>Add a new Administrador</li>
 *     <li>Update an existing Administrador</li>
 *     <li>Delete an Administrador</li>
 * </ul>
 * </p>
 * <p>
 * The class also validates mandatory fields and checks the existence of related entities before performing certain operations.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Administrador
 * @see AdministradorRepository
 * @see ColaboradorRepository
 * @see DBConnection
 * @see ServiceException
 */
public class AdministradorService {
    private final AdministradorRepository administradorRepository;
    private final Connection con = DBConnection.getConnection();
    private final ColaboradorRepository colaboradorRepository = new ColaboradorRepository(con);

    /**
     * Constructs a new {@code AdministradorService} with the provided {@code AdministradorRepository}.
     *
     * @param administradorRepository The repository for {@code Administrador} entities.
     */
    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    /**
     * Retrieves a list of all {@code Administrador} entities.
     *
     * @return A list of all {@code Administrador} entities.
     */
    public List<Administrador> getAllAdministrador() {
        return administradorRepository.getAllAdministrador();
    }

    /**
     * Retrieves an {@code Administrador} entity by its ID.
     *
     * @param administradorId The ID of the {@code Administrador} entity to retrieve.
     * @return The {@code Administrador} entity with the specified ID.
     */
    public Administrador getAdministradorById(int administradorId) {
        return administradorRepository.getAdministradorById(administradorId);
    }

    /**
     * Adds a new {@code Administrador} entity.
     *
     * @param administrador The {@code Administrador} entity to add.
     * @return The added {@code Administrador} entity.
     * @throws ServiceException If validation fails or an error occurs during the addition.
     */
    public Administrador addAdministrador(Administrador administrador) {
        try {
            validateAdministradorFields(administrador);
            return administradorRepository.addAdministrador(administrador);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Updates an existing {@code Administrador} entity with the specified ID.
     *
     * @param id            The ID of the {@code Administrador} entity to update.
     * @param administrador The updated {@code Administrador} entity.
     * @return The updated {@code Administrador} entity.
     * @throws ServiceException If validation fails or an error occurs during the update.
     */
    public Administrador updateAdministrador(int id, Administrador administrador) {
        if (administrador.getPassword() == null) {
            throw new ServiceException("All administrador fields are mandatory!");
        }
        return administradorRepository.updateAdministrador(id, administrador);
    }

    /**
     * Deletes the {@code Administrador} entity with the specified ID.
     *
     * @param id The ID of the {@code Administrador} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteAdministrador(int id) {
        return administradorRepository.deleteAdministrador(id);
    }
    /**
     * Validates the fields of the provided {@code Administrador} entity.
     * Checks if the ID of the associated Colaborador exists and ensures mandatory fields are not null.
     *
     * @param administrador The {@code Administrador} entity to validate.
     * @throws ServiceException If mandatory fields are null or if the associated Colaborador ID is invalid.
     * @see Administrador
     * @see ColaboradorRepository
     * @see ServiceException
     */
    private void validateAdministradorFields(Administrador administrador) {
        if (administrador.getId_colaborador() <= 0 || administrador.getPassword() == null) {
            throw new ServiceException("All administrador fields are mandatory!");
        } else {
            if (!colaboradorRepository.existsColaborador(administrador.getId_colaborador())) {
                throw new ServiceException("Invalid id_colaborador. It does not exist in the Colaborador table.");
            }
        }
    }
}
