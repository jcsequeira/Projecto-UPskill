package service;


import model.Pais;
import repository.PaisRepository;

import java.util.List;

public class PaisService {

    private PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public Pais addPais(Pais pais){
        // You can add additional validation logic before saving to the database if needed
        // For example, check if the fields are not empty
        // Save to the database
        return paisRepository.addPais(pais);
    }


}
