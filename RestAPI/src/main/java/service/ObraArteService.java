package service;

import model.Obra_Arte;
import repository.ObraArteRepository;

import java.util.List;

public class ObraArteService {
    private ObraArteRepository obraArteRepository;

    public ObraArteService(ObraArteRepository obraArteRepository) {
        this.obraArteRepository = obraArteRepository;
    }

    public List<Obra_Arte> getAllObraArte (){
        return obraArteRepository.getAllObraArte();
    }
}