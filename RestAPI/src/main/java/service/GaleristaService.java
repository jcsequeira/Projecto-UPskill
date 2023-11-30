package service;

import model.Galerista;
import repository.GaleristaRepository;

import java.util.List;

public class GaleristaService {
    private GaleristaRepository galeristaRepository;

    public GaleristaService(GaleristaRepository galeristaRepository) {
        this.galeristaRepository = galeristaRepository;
    }

    public List<Galerista> getAllGaleristas() {
        return galeristaRepository.getAllGaleristas();
    }

    public Galerista getGaleristaById(int galeristaId) {
        return galeristaRepository.getGaleristaById(galeristaId);
    }

    public Galerista addGalerista(Galerista newGalerista) {
        return galeristaRepository.addGalerista(newGalerista);
    }

    public Galerista updateGalerista(int galeristaId, Galerista updatedGalerista) {
        return galeristaRepository.updateGalerista(galeristaId, updatedGalerista);
    }

    public String deleteGalerista(int galeristaId) {
        return galeristaRepository.deleteGalerista(galeristaId);
    }
}
