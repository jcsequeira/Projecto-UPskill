package service;

import exceptions.ServiceException;
import model.Artista;
import model.Evento;
import repository.EventoRepository;

import java.util.List;

public class EventoService {
    private EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public List<Evento> getAllEvento (){
        return eventoRepository.getAllEvento();
    }

    public Evento getEventoById(int eventoId) { return eventoRepository.getEventoById(eventoId);}

    public Evento addEvento(Evento newEvento) {
        // Validate events with mandatory dates
        if (newEvento.getData_inicio() == null || newEvento.getData_Fim() == null) {
            throw new ServiceException("Start and end dates are mandatory for an event.");
        }
        return eventoRepository.addEvento(newEvento);
    }

    public Evento updateEvento(int eventoId, Evento updatedEvento) {
        // Validate events with mandatory dates
        if (updatedEvento.getData_inicio() == null || updatedEvento.getData_Fim() == null) {
            throw new ServiceException("Start and end dates are mandatory for an event.");
        }
        return eventoRepository.updateEvento(eventoId, updatedEvento);
    }

    public String deleteEvento (int eventoId) {return eventoRepository.deleteEvento(eventoId);}

    public void addAllEventos(List<Evento> eventosList) {
        for (Evento evento : eventosList) {
            eventoRepository.addEvento(evento);
        }
    }
}
