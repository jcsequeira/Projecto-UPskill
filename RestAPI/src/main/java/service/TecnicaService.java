package service;

import model.Tecnica;
import repository.TecnicaRepository;

import java.util.List;

public class TecnicaService {

    private TecnicaRepository tecnicaRepository;

    public TecnicaService(TecnicaRepository tecnicaRepository) {
        this.tecnicaRepository = tecnicaRepository;
    }

    public List<Tecnica> getAllTecnicas() {
        return tecnicaRepository.getAllTecnicas();
    }

    public Tecnica getTecnicaById(int tecnicaId) {
        return tecnicaRepository.getTecnicaById(tecnicaId);
    }

    public Tecnica addTecnica(Tecnica tecnica) {
        // You can add additional validation logic before saving to the database if needed
        // For example, check if the fields are not empty
        // Save to the database
        return tecnicaRepository.addTecnica(tecnica);
    }

    public Tecnica updateTecnica(int id, Tecnica tecnica) {
        // You might want to add validation logic or additional checks before updating
        return tecnicaRepository.updateTecnica(id, tecnica);
    }

    public String deleteTecnica(int id) {
        return tecnicaRepository.deleteTecnica(id);
    }
}
