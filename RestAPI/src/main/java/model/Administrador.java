package model;

/**
 * Represents an administrator in the system.
 */
public class Administrador {

    private String password;
    private int id_colaborador;

    /**
     * Constructs an administrator with the specified password and collaborator ID.
     *
     * @param password         The password of the administrator.
     * @param id_colaborador   The ID of the collaborator associated with the administrator.
     */
    public Administrador(String password, int id_colaborador) {
        this.password = password;
        this.id_colaborador = id_colaborador;
    }

    /**
     * Constructs an empty administrator.
     */
    public Administrador() {
    }

    /**
     * Gets the password of the administrator.
     *
     * @return The password of the administrator.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the administrator.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the ID of the collaborator associated with the administrator.
     *
     * @return The ID of the collaborator.
     */
    public int getId_colaborador() {
        return id_colaborador;
    }

    /**
     * Sets the ID of the collaborator associated with the administrator.
     *
     * @param id_colaborador The ID of the collaborator to set.
     */
    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }
}
