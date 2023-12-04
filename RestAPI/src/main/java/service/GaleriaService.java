package service;

import model.Artista;
import model.Galeria;
import repository.GaleriaRepository;

import java.util.List;

public class GaleriaService {
    private GaleriaRepository galeriaRepository;

    public GaleriaService(GaleriaRepository galeriaRepository) {
        this.galeriaRepository = galeriaRepository;
    }

    public List<Galeria> getAllGaleria (){
        return galeriaRepository.getAllGaleria();
    }

    public Galeria getGaleriaById(int galeriaId) { return galeriaRepository.getGaleriaById(galeriaId);}

    public Galeria addGaleria(Galeria newGaleria) { return galeriaRepository.addGaleria(newGaleria);
    }

    public Galeria updateGaleria(int galeriaId, Galeria updatedGaleria) { return galeriaRepository.updateGaleria(galeriaId, updatedGaleria);
    }

    public String deleteGaleria (int galeriaId) {return galeriaRepository.deleteGaleria(galeriaId);}

    public void addAllGalerias(List<Galeria> galeriasList) {
        for (Galeria galeria : galeriasList) {
            galeriaRepository.addGaleria(galeria);
        }
    }
}
