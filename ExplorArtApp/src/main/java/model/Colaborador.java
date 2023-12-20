package model;

/**
 * The {@code Colaborador} class represents a collaborator with details such as ID, name, email, telephone, and country code.
 */
public class Colaborador {

    /** The unique ID of the collaborator. */
    private int id_colaborador;

    /** The name of the collaborator. */
    private String Nome_Colaborador;

    /** The email address of the collaborator. */
    private String Email;

    /** The telephone number of the collaborator. */
    private String Telefone;

    /** The country code of the collaborator. */
    private int Codigo_Pais;

    /**
     * Constructs a {@code Colaborador} with the specified details.
     *
     * @param id_colaborador The unique ID of the collaborator.
     * @param nome_Colaborador The name of the collaborator.
     * @param email The email address of the collaborator.
     * @param telefone The telephone number of the collaborator.
     * @param codigo_Pais The country code of the collaborator.
     */
    public Colaborador(int id_colaborador, String nome_Colaborador, String email, String telefone, int codigo_Pais) {
        this.id_colaborador = id_colaborador;
        Nome_Colaborador = nome_Colaborador;
        Email = email;
        Telefone = telefone;
        Codigo_Pais = codigo_Pais;
    }

    /**
     * Default constructor for the {@code Colaborador} class.
     */
    public Colaborador() {
    }

    /**
     * Gets the unique ID of the collaborator.
     *
     * @return The unique ID of the collaborator.
     */
    public int getId_colaborador() {
        return id_colaborador;
    }

    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

    /**
     * Gets the name of the collaborator.
     *
     * @return The name of the collaborator.
     */
    public String getNome_Colaborador() {
        return Nome_Colaborador;
    }

    /**
     * Sets the name of the collaborator.
     *
     * @param nome_Colaborador The name of the collaborator.
     */
    public void setNome_Colaborador(String nome_Colaborador) {
        Nome_Colaborador = nome_Colaborador;
    }

    /**
     * Gets the email address of the collaborator.
     *
     * @return The email address of the collaborator.
     */
    public String getEmail() {
        return Email;
    }



    /**
     * Sets the email address of the collaborator.
     *
     * @param email The email address of the collaborator.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Gets the telephone number of the collaborator.
     *
     * @return The telephone number of the collaborator.
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * Sets the telephone number of the collaborator.
     *
     * @param telefone The telephone number of the collaborator.
     */
    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    /**
     * Gets the country code of the collaborator.
     *
     * @return The country code of the collaborator.
     */
    public int getCodigo_Pais() {
        return Codigo_Pais;
    }

    /**
     * Sets the country code of the collaborator.
     *
     * @param codigo_Pais The country code of the collaborator.
     */
    public void setCodigo_Pais(int codigo_Pais) {
        Codigo_Pais = codigo_Pais;
    }

    /**
     * Returns a string representation of the collaborator (the collaborator's name).
     *
     * @return The name of the collaborator.
     */
    @Override
    public String toString() {
        return Nome_Colaborador;
    }
}
