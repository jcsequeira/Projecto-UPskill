package service;

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
        //validar eventos com datas obrigatorias!
        return eventoRepository.addEvento(newEvento);
    }

    public Evento updateEvento(int eventoId, Evento updatedEvento) {
        //validar eventos com datas obrigatorias!
        return eventoRepository.updateEvento(eventoId, updatedEvento);
    }

    public String deleteEvento (int eventoId) {return eventoRepository.deleteEvento(eventoId);}
}
