/**
 * The {@code GaleristaService} class provides methods to perform CRUD operations on {@code Galerista} entities.
 * It interacts with the {@code GaleristaRepository} for database operations and enforces validation rules.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Galeristas</li>
 *     <li>Retrieve a Galerista by ID</li>
 *     <li>Add a new Galerista</li>
 *     <li>Update an existing Galerista</li>
 *     <li>Delete a Galerista</li>
 * </ul>
 * </p>
 * <p>
 * The class includes validation checks for mandatory fields before adding or updating Galeristas.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Galerista
 * @see GaleristaRepository
 * @see ColaboradorRepository
 * @see DBConnection
 * @see ServiceException
 */
package service;

import exceptions.ServiceException;
import model.Galerista;
import repository.ColaboradorRepository;
import repository.DBConnection;
import repository.GaleristaRepository;

import java.sql.Connection;
import java.util.List;

public class GaleristaService {
    private final GaleristaRepository galeristaRepository;

    private final Connection con = DBConnection.getConnection();
    private final ColaboradorRepository colaboradorRepository = new ColaboradorRepository(con);

    /**
     * Constructs a new {@code GaleristaService} with the provided {@code GaleristaRepository}.
     *
     * @param galeristaRepository The repository for {@code Galerista} entities.
     */
    public GaleristaService(GaleristaRepository galeristaRepository) {
        this.galeristaRepository = galeristaRepository;
    }

    /**
     * Retrieves a list of all {@code Galerista} entities.
     *
     * @return A list of all {@code Galerista} entities.
     */
    public List<Galerista> getAllGaleristas() {
        return galeristaRepository.getAllGaleristas();
    }

    /**
     * Retrieves a {@code Galerista} entity by its ID.
     *
     * @param galeristaId The ID of the {@code Galerista} entity to retrieve.
     * @return The {@code Galerista} entity with the specified ID.
     */
    public Galerista getGaleristaById(int galeristaId) {
        return galeristaRepository.getGaleristaById(galeristaId);
    }

    /**
     * Adds a new {@code Galerista} entity.
     *
     * @param newGalerista The {@code Galerista} entity to add.
     * @return The added {@code Galerista} entity.
     * @throws ServiceException If mandatory fields are null or id_colaborador is less than or equal to 0.
     */
    public Galerista addGalerista(Galerista newGalerista) {
        try {
            validateGaleristaFields(newGalerista);
            return galeristaRepository.addGalerista(newGalerista);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Updates an existing {@code Galerista} entity with the specified ID.
     *
     * @param galeristaId      The ID of the {@code Galerista} entity to update.
     * @param updatedGalerista The updated {@code Galerista} entity.
     * @return The updated {@code Galerista} entity.
     * @throws ServiceException If the password is null.
     */
    public Galerista updateGalerista(int galeristaId, Galerista updatedGalerista) {
        if (updatedGalerista.getPassword() == null) {
            throw new ServiceException("Galerista's Password must be provided");
        }
        return galeristaRepository.updateGalerista(galeristaId, updatedGalerista);
    }

    /**
     * Deletes the {@code Galerista} entity with the specified ID.
     *
     * @param galeristaId The ID of the {@code Galerista} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteGalerista(int galeristaId) {
        return galeristaRepository.deleteGalerista(galeristaId);
    }
    /**
     * Validates the fields of a {@code Galerista} entity.
     * <p>
     * This method checks if the password and id_colaborador fields of the provided {@code Galerista} are not null and id_colaborador is greater than 0.
     * Additionally, it verifies if the id_colaborador exists in the Colaborador table using the {@code ColaboradorRepository}.
     * </p>
     *
     * @param galerista The {@code Galerista} entity to validate.
     * @throws ServiceException If any of the mandatory fields are null or if the id_colaborador is invalid.
     * @see Galerista
     * @see ColaboradorRepository
     * @see ServiceException
     */
    private void validateGaleristaFields(Galerista galerista) {
        if (galerista.getPassword() == null || galerista.getId_colaborador() <= 0) {
            throw new ServiceException("All Galerista fields are mandatory!");
        } else {
            if (!colaboradorRepository.existsColaborador(galerista.getId_colaborador())) {
                throw new ServiceException("Invalid id_colaborador. It does not exist in the Colaborador table.");
            }
        }
    }
}
