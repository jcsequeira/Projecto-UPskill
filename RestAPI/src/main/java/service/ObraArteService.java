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

    public Obra_Arte getObraArteById(int obraArteId) { return obraArteRepository.getObraArteById(obraArteId);}

    public Obra_Arte addObraArte(Obra_Arte newObraArte) { return obraArteRepository.addObraArte(newObraArte);
    }

    public Obra_Arte updateObraArte(int obraArteId, Obra_Arte updatedObraArte) { return obraArteRepository.updateObraArte(obraArteId, updatedObraArte);
    }
}
