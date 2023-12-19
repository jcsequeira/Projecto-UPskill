package service;

import exceptions.ServiceException;
import model.Galerista;
import repository.ColaboradorRepository;
import repository.DBConnection;
import repository.GaleristaRepository;

import java.sql.Connection;
import java.util.List;

public class GaleristaService {
    private final GaleristaRepository galeristaRepository;

    private final Connection con = DBConnection.getConnection();
    private final ColaboradorRepository colaboradorRepository = new ColaboradorRepository(con);

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
        try {validateGaleristaFields(newGalerista);
        return galeristaRepository.addGalerista(newGalerista);}
        catch (ServiceException e){
            e.printStackTrace();
            throw e;
        }
    }


    public Galerista updateGalerista(int galeristaId, Galerista updatedGalerista) {
        if (updatedGalerista.getPassword() == null){
            throw new ServiceException("Galerista's Password must be provided");
        }
        return galeristaRepository.updateGalerista(galeristaId, updatedGalerista);
    }

    public String deleteGalerista(int galeristaId) {
        return galeristaRepository.deleteGalerista(galeristaId);
    }

    private void validateGaleristaFields(Galerista galerista) {
        if (galerista.getPassword() == null || galerista.getId_colaborador() <= 0){
            throw  new ServiceException("All Galerista fields are mandatory!");
        } else {
            if (!colaboradorRepository.existsColaborador(galerista.getId_colaborador())) {
                throw new ServiceException("Invalid id_colaborador. It does not exist in the Colaborador table.");
            }
        }
    }
}
