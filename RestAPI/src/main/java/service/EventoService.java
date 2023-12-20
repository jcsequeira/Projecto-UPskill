
package service;

import exceptions.ServiceException;
import model.Evento;
import repository.EventoRepository;

import java.util.List;
/**
 * The {@code EventoService} class provides methods to perform CRUD operations on {@code Evento} entities.
 * It interacts with the {@code EventoRepository} for database operations.
 * <p>
 * This class includes methods to:
 * <ul>
 *     <li>Retrieve all Eventos</li>
 *     <li>Retrieve an Evento by ID</li>
 *     <li>Add a new Evento</li>
 *     <li>Update an existing Evento</li>
 *     <li>Delete an Evento</li>
 *     <li>Add a list of Eventos</li>
 * </ul>
 * </p>
 * <p>
 * The class includes validation checks for mandatory dates before adding or updating Eventos.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 * @see Evento
 * @see EventoRepository
 * @see ServiceException
 */
public class EventoService {
    private final EventoRepository eventoRepository;

    /**
     * Constructs a new {@code EventoService} with the provided {@code EventoRepository}.
     *
     * @param eventoRepository The repository for {@code Evento} entities.
     */
    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    /**
     * Retrieves a list of all {@code Evento} entities.
     *
     * @return A list of all {@code Evento} entities.
     */
    public List<Evento> getAllEvento() {
        return eventoRepository.getAllEvento();
    }

    /**
     * Retrieves an {@code Evento} entity by its ID.
     *
     * @param eventoId The ID of the {@code Evento} entity to retrieve.
     * @return The {@code Evento} entity with the specified ID.
     */
    public Evento getEventoById(int eventoId) {
        return eventoRepository.getEventoById(eventoId);
    }

    /**
     * Adds a new {@code Evento} entity.
     *
     * @param newEvento The {@code Evento} entity to add.
     * @return The added {@code Evento} entity.
     * @throws ServiceException If start or end dates are null.
     */
    public Evento addEvento(Evento newEvento) {
        if (newEvento.getData_inicio() == null || newEvento.getData_Fim() == null) {
            throw new ServiceException("Start and end dates are mandatory for an event.");
        }
        return eventoRepository.addEvento(newEvento);
    }

    /**
     * Updates an existing {@code Evento} entity with the specified ID.
     *
     * @param eventoId      The ID of the {@code Evento} entity to update.
     * @param updatedEvento The updated {@code Evento} entity.
     * @return The updated {@code Evento} entity.
     * @throws ServiceException If start or end dates are null.
     */
    public Evento updateEvento(int eventoId, Evento updatedEvento) {
        if (updatedEvento.getData_inicio() == null || updatedEvento.getData_Fim() == null) {
            throw new ServiceException("Start and end dates are mandatory for an event.");
        }
        return eventoRepository.updateEvento(eventoId, updatedEvento);
    }

    /**
     * Deletes the {@code Evento} entity with the specified ID.
     *
     * @param eventoId The ID of the {@code Evento} entity to delete.
     * @return A message indicating the result of the deletion.
     */
    public String deleteEvento(int eventoId) {
        return eventoRepository.deleteEvento(eventoId);
    }

    /**
     * Adds a list of {@code Evento} entities.
     *
     * @param eventosList The list of {@code Evento} entities to add.
     */
    public void addAllEventos(List<Evento> eventosList) {
        for (Evento evento : eventosList) {
            addEvento(evento);
        }
    }
}
