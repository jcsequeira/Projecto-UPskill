package model;

/**
 * The `Administrador` class represents an administrator entity with a password and associated collaborator ID.
 */
public class Administrador {

    private String password;
    private int id_colaborador;

    /**
     * Constructs a new `Administrador` with the specified password and collaborator ID.
     *
     * @param password         The password for the administrator.
     * @param id_colaborador   The ID of the associated collaborator.
     */
    public Administrador(String password, int id_colaborador) {
        this.password = password;
        this.id_colaborador = id_colaborador;
    }

    /**
     * Constructs a new default `Administrador`.
     */
    public Administrador() {
    }

    /**
     * Gets the password of the administrator.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the administrator.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the ID of the associated collaborator.
     *
     * @return The collaborator ID.
     */
    public int getId_colaborador() {
        return id_colaborador;
    }

    /**
     * Sets the ID of the associated collaborator.
     *
     * @param id_colaborador The new collaborator ID.
     */
    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }
}
