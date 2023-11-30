package service;

import exceptions.ServiceException;
import model.Administrador;
import repository.AdministradorRepository;

import java.util.List;

public class AdministradorService {
    private AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> getAllAdministrador (){
        return administradorRepository.getAllAdministrador();
    }

    public Administrador getAdministradorById(int administradorId){ return administradorRepository.getAdministradorById(administradorId); }

    public Administrador addAdministrador(Administrador administrador) {
        try {
            validateAdministradorFields(administrador);
            // If validation passes, add the administrador
            return administradorRepository.addAdministrador(administrador);
        } catch (ServiceException e) {
            // Handle the ServiceException appropriately
            // You might want to log the exception or notify the user
            e.printStackTrace(); // Example: Logging the exception
            throw e; // Re-throw the exception if needed
        }
    }

    public Administrador updateAdministrador(int id, Administrador administrador) {
        try {
            validateAdministradorFields(administrador);
            // If validation passes, update the administrador
            return administradorRepository.updateAdministrador(id, administrador);
        } catch (ServiceException e) {
            // Handle the ServiceException appropriately
            // You might want to log the exception or notify the user
            e.printStackTrace(); // Example: Logging the exception
            throw e; // Re-throw the exception if needed
        }
    }

    private void validateAdministradorFields(Administrador administrador) {
        // Validate if mandatory fields are not null and Codigo_Pais is greater than 0
        if (administrador.getId_colaborador() <= 0 ||
                administrador.getPassword() == null)
        { throw new ServiceException("All administrador fields are mandatory!");
        }
    }


    public String deleteAdministrador(int id) {return administradorRepository.deleteAdministrador(id);}
}
