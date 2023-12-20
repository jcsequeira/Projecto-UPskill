package model;

/**
 * The {@code Galeria} class represents a gallery with details such as ID, name, address, website, email, telephone,
 * city ID, collaborator ID, and Artsy status.
 */
public class Galeria {

    /** The unique ID of the gallery. */
    private int id_Galeria;

    /** The name of the gallery. */
    private String Nome_Galeria;

    /** The address of the gallery. */
    private String Morada;

    /** The website of the gallery. */
    private String Website;

    /** The email of the gallery. */
    private String Email;

    /** The telephone number of the gallery. */
    private String Telefone;

    /** The ID of the city associated with the gallery. */
    private int id_Cidade;

    /** The ID of the collaborator associated with the gallery. */
    private int id_colaborador;

    /** The Artsy status of the gallery. */
    private int IsArtsy;

    /**
     * Constructs a {@code Galeria} with the specified details.
     *
     * @param id_Galeria The unique ID of the gallery.
     * @param nome_Galeria The name of the gallery.
     * @param morada The address of the gallery.
     * @param website The website of the gallery.
     * @param email The email of the gallery.
     * @param telefone The telephone number of the gallery.
     * @param id_Cidade The ID of the city associated with the gallery.
     * @param id_colaborador The ID of the collaborator associated with the gallery.
     * @param isArtsy The Artsy status of the gallery.
     */
    public Galeria(int id_Galeria, String nome_Galeria, String morada, String website, String email,
                   String telefone, int id_Cidade, int id_colaborador, int isArtsy) {
        this.id_Galeria = id_Galeria;
        Nome_Galeria = nome_Galeria;
        Morada = morada;
        Website = website;
        Email = email;
        Telefone = telefone;
        this.id_Cidade = id_Cidade;
        this.id_colaborador = id_colaborador;
        IsArtsy = isArtsy;
    }

    /**
     * Default constructor for the {@code Galeria} class.
     */
    public Galeria() {
    }

    /**
     * Gets the unique ID of the gallery.
     *
     * @return The unique ID of the gallery.
     */
    public int getId_Galeria() {
        return id_Galeria;
    }

    /**
     * Gets the name of the gallery.
     *
     * @return The name of the gallery.
     */
    public String getNome_Galeria() {
        return Nome_Galeria;
    }

    /**
     * Sets the name of the gallery.
     *
     * @param nome_Galeria The name of the gallery.
     */
    public void setNome_Galeria(String nome_Galeria) {
        Nome_Galeria = nome_Galeria;
    }

    /**
     * Gets the address of the gallery.
     *
     * @return The address of the gallery.
     */
    public String getMorada() {
        return Morada;
    }

    /**
     * Sets the address of the gallery.
     *
     * @param morada The address of the gallery.
     */
    public void setMorada(String morada) {
        Morada = morada;
    }

    /**
     * Gets the website of the gallery.
     *
     * @return The website of the gallery.
     */
    public String getWebsite() {
        return Website;
    }

    /**
     * Sets the website of the gallery.
     *
     * @param website The website of the gallery.
     */
    public void setWebsite(String website) {
        Website = website;
    }

    /**
     * Gets the email of the gallery.
     *
     * @return The email of the gallery.
     */
    public String getEmail() {
        return Email;
    }

    public void setId_Galeria(int id_Galeria) {
        this.id_Galeria = id_Galeria;
    }

    public int getIsArtsy() {
        return IsArtsy;
    }

    /**
     * Sets the email of the gallery.
     *
     * @param email The email of the gallery.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Gets the telephone number of the gallery.
     *
     * @return The telephone number of the gallery.
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * Sets the telephone number of the gallery.
     *
     * @param telefone The telephone number of the gallery.
     */
    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    /**
     * Gets the ID of the city associated with the gallery.
     *
     * @return The ID of the city associated with the gallery.
     */
    public int getId_Cidade() {
        return id_Cidade;
    }

    /**
     * Sets the ID of the city associated with the gallery.
     *
     * @param id_Cidade The ID of the city associated with the gallery.
     */
    public void setId_Cidade(int id_Cidade) {
        this.id_Cidade = id_Cidade;
    }

    /**
     * Gets the ID of the collaborator associated with the gallery.
     *
     * @return The ID of the collaborator associated with the gallery.
     */
    public int getId_colaborador() {
        return id_colaborador;
    }

    /**
     * Sets the ID of the collaborator associated with the gallery.
     *
     * @param id_colaborador The ID of the collaborator associated with the gallery.
     */
    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

    /**
     * Sets the Artsy status of the gallery.
     *
     * @param isArtsy The Artsy status of the gallery.
     */
    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }

    /**
     * Returns the name of the gallery as a string representation.
     *
     * @return The name of the gallery.
     */
    @Override
    public String toString() {
        return Nome_Galeria;
    }
}

