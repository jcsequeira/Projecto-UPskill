package service;



import model.Pais;
import repository.PaisRepository;

import java.util.List;


public class PaisService {

    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public List<Pais> getAllPais (){
        return paisRepository.getAllPais();
    }

    public Pais getPaisById(int paisId){ return paisRepository.getPaisById(paisId); }

    public Pais addPais(Pais pais){
        // You can add additional validation logic before saving to the database if needed
        // For example, check if the fields are not empty
        // Save to the database
        return paisRepository.addPais(pais);
    }

    public Pais updatePais(int id, Pais pais) {
        // You might want to add validation logic or additional checks before updating
        return paisRepository.updatePais(id, pais);
    }

    public void deletePais(int id) {
        paisRepository.deletePais(id);
    }

    public void addAllPaises(List<Pais> paisesList) {
        for (Pais pais : paisesList) {
            paisRepository.addPais(pais);
        }
    }
}
