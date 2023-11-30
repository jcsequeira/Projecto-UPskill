package service;

import exceptions.ServiceException;
import model.Administrador;
import repository.AdministradorRepository;
import repository.ColaboradorRepository;
import repository.DBConnection;

import java.sql.Connection;
import java.util.List;

public class AdministradorService {
    private AdministradorRepository administradorRepository;
    private final Connection con = DBConnection.getConnection();
    private final ColaboradorRepository colaboradorRepository = new ColaboradorRepository(con);

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
        if (administrador.getPassword() == null) {
            throw new ServiceException("All administrador fields are mandatory!");
        }
        return administradorRepository.updateAdministrador(id, administrador);}

    public String deleteAdministrador(int id) {return administradorRepository.deleteAdministrador(id);}

    private void validateAdministradorFields(Administrador administrador) {
        // Validate if mandatory fields are not null and Codigo_Pais is greater than 0
        if (administrador.getId_colaborador() <= 0 || administrador.getPassword() == null) {
            throw new ServiceException("All administrador fields are mandatory!");
        } else {
            // Check if the provided id_colaborador exists in the Colaborador table
            if (!colaboradorRepository.existsColaborador(administrador.getId_colaborador())) {
                throw new ServiceException("Invalid id_colaborador. It does not exist in the Colaborador table.");
            }
        }
    }
}
