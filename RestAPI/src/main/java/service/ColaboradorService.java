package service;

import exceptions.ServiceException;
import model.Colaborador;
import repository.ColaboradorRepository;

import java.util.List;

public class ColaboradorService {
    private ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<Colaborador> getAllColaboradores (){
        return colaboradorRepository.getAllColaboradores();
    }

    public Colaborador getColaboradorById(int colaboradorId){ return colaboradorRepository.getColaboradorById(colaboradorId); }

    public Colaborador addColaborador(Colaborador colaborador) {
        try {
            validateColaboradorFields(colaborador);

            // If validation passes, add the colaborador
            return colaboradorRepository.addColaborador(colaborador);
        } catch (ServiceException e) {
            // Handle the ServiceException appropriately
            // You might want to log the exception or notify the user
            e.printStackTrace(); // Example: Logging the exception
            throw e; // Re-throw the exception if needed
        }
    }

    public Colaborador updateColaborador(int id, Colaborador colaborador) {
        try {
            validateColaboradorFields(colaborador);

            // If validation passes, update the colaborador
            return colaboradorRepository.updateColaborador(id, colaborador);
        } catch (ServiceException e) {
            // Handle the ServiceException appropriately
            // You might want to log the exception or notify the user
            e.printStackTrace(); // Example: Logging the exception
            throw e; // Re-throw the exception if needed
        }
    }

    private void validateColaboradorFields(Colaborador colaborador) {
        // Validate if mandatory fields are not null and Codigo_Pais is greater than 0
        if (colaborador.getNome_Colaborador() == null ||
                colaborador.getEmail() == null ||
                colaborador.getTelefone() == null ||
                colaborador.getCodigo_Pais() <= 0) {
            throw new ServiceException("All colaborador fields are mandatory!");
        }
    }


    public String deleteColaborador(int id) {return colaboradorRepository.deleteColaborador(id);}
}
