package model;
/**
 * The {@code Administrador} class represents an administrator in the system.
 * It contains information such as password and collaborator ID.
 */
public class Administrador {

    /** The password of the administrator. */
    private String password;

    /** The ID of the collaborator associated with the administrator. */
    private int id_colaborador;

    /**
     * Constructs an {@code Administrador} with the specified password and collaborator ID.
     *
     * @param password The password associated with the administrator.
     * @param id_colaborador The ID of the collaborator associated with the administrator.
     */
    public Administrador(String password, int id_colaborador) {
        this.password = password;
        this.id_colaborador = id_colaborador;
    }

    /**
     * Gets the ID of the collaborator associated with the administrator.
     *
     * @return The ID of the collaborator.
     */
    public int getId_colaborador() {
        return id_colaborador;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }
}

