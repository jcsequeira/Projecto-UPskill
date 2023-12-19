package model;

/**
 * Represents a gallery in the system.
 */
public class Galeria {
    private int id_Galeria;
    private String Nome_Galeria;
    private String Morada;
    private String Website;
    private String Email;
    private String Telefone;
    private int id_Cidade;
    private int id_colaborador;
    private int IsArtsy;

    /**
     * Constructs a gallery with the specified details.
     *
     * @param id_Galeria      The ID of the gallery.
     * @param nome_Galeria    The name of the gallery.
     * @param morada          The address of the gallery.
     * @param website         The website of the gallery.
     * @param email           The email of the gallery.
     * @param telefone        The phone number of the gallery.
     * @param id_Cidade       The ID of the city where the gallery is located.
     * @param id_colaborador  The ID of the collaborator associated with the gallery.
     * @param isArtsy         The flag indicating whether the gallery is associated with Artsy.
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
     * Constructs an empty gallery.
     */
    public Galeria() {
    }

    /**
     * Gets the ID of the gallery.
     *
     * @return The ID of the gallery.
     */
    public int getId_Galeria() {
        return id_Galeria;
    }

    /**
     * Sets the ID of the gallery.
     *
     * @param id_Galeria The ID of the gallery to set.
     */
    public void setId_Galeria(int id_Galeria) {
        this.id_Galeria = id_Galeria;
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
     * @param nome_Galeria The name of the gallery to set.
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
     * @param morada The address of the gallery to set.
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
     * @param website The website of the gallery to set.
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

    /**
     * Sets the email of the gallery.
     *
     * @param email The email of the gallery to set.
     */
    public void setEmail(String email) {
        Email = email;
    }

    /**
     * Gets the phone number of the gallery.
     *
     * @return The phone number of the gallery.
     */
    public String getTelefone() {
        return Telefone;
    }

    /**
     * Sets the phone number of the gallery.
     *
     * @param telefone The phone number of the gallery to set.
     */
    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    /**
     * Gets the ID of the city where the gallery is located.
     *
     * @return The ID of the city where the gallery is located.
     */
    public int getId_Cidade() {
        return id_Cidade;
    }

    /**
     * Sets the ID of the city where the gallery is located.
     *
     * @param id_Cidade The ID of the city to set.
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
     * @param id_colaborador The ID of the collaborator to set.
     */
    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

    /**
     * Gets the flag indicating whether the gallery is associated with Artsy.
     *
     * @return The flag indicating whether the gallery is associated with Artsy.
     */
    public int getIsArtsy() {
        return IsArtsy;
    }

    /**
     * Sets the flag indicating whether the gallery is associated with Artsy.
     *
     * @param isArtsy The flag to set.
     */
    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }
}
