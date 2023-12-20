package model;

import java.time.LocalDate;

/**
 * The {@code Artista} class represents an artist with details such as ID, name, birth date, biography,
 * death date, and nationality.
 */
public class Artista {

    /** The unique ID of the artist. */
    private long id_artista;

    /** The name of the artist. */
    private String nome_artista;

    /** The birth date of the artist. */
    private LocalDate Data_Nascimento;

    /** The biography of the artist. */
    private String Biografia;

    /** The death date of the artist. */
    private LocalDate Data_Morte;

    /** The nationality of the artist. */
    private String Nacionalidade;

    /** The flag indicating whether the artwork is associated with Artsy. */
    private int isArtsy;

    /**
     * Constructs an {@code Artista} with the specified details.
     *
     * @param id_artista The unique ID of the artist.
     * @param nome_artista The name of the artist.
     * @param data_Nascimento The birth date of the artist.
     * @param biografia The biography of the artist.
     * @param data_Morte The death date of the artist.
     * @param nacionalidade The nationality of the artist.
     * @param isArtsy If is from Artsy.
     */
    public Artista(long id_artista, String nome_artista, LocalDate data_Nascimento, String biografia, LocalDate data_Morte, String nacionalidade, int isArtsy) {
        this.id_artista = id_artista;
        this.nome_artista = nome_artista;
        Data_Nascimento = data_Nascimento;
        Biografia = biografia;
        Data_Morte = data_Morte;
        Nacionalidade = nacionalidade;
        this.isArtsy = isArtsy;
    }

    /**
     * Default constructor for the {@code Artista} class.
     */
    public Artista() {
    }

    /**
     * Gets the unique ID of the artist.
     *
     * @return The unique ID of the artist.
     */
    public long getId_artista() {
        return id_artista;
    }

    public void setId_artista(long id_artista) {
        this.id_artista = id_artista;
    }

    /**
     * Gets the name of the artist.
     *
     * @return The name of the artist.
     */
    public String getNome_artista() {
        return nome_artista;
    }

    /**
     * Gets the birth date of the artist.
     *
     * @return The birth date of the artist.
     */
    public LocalDate getData_Nascimento() {
        return Data_Nascimento;
    }

    /**
     * Gets the biography of the artist.
     *
     * @return The biography of the artist.
     */
    public String getBiografia() {
        return Biografia;
    }

    /**
     * Gets the death date of the artist.
     *
     * @return The death date of the artist.
     */
    public LocalDate getData_Morte() {
        return Data_Morte;
    }

    /**
     * Gets the nationality of the artist.
     *
     * @return The nationality of the artist.
     */
    public String getNacionalidade() {
        return Nacionalidade;
    }

    /**
     * Sets the name of the artist.
     *
     * @param nome_artista The name of the artist.
     */
    public void setNome_artista(String nome_artista) {
        this.nome_artista = nome_artista;
    }

    /**
     * Sets the birth date of the artist.
     *
     * @param data_Nascimento The birth date of the artist.
     */
    public void setData_Nascimento(LocalDate data_Nascimento) {
        Data_Nascimento = data_Nascimento;
    }

    /**
     * Sets the biography of the artist.
     *
     * @param biografia The biography of the artist.
     */
    public void setBiografia(String biografia) {
        Biografia = biografia;
    }

    /**
     * Sets the death date of the artist.
     *
     * @param data_Morte The death date of the artist.
     */
    public void setData_Morte(LocalDate data_Morte) {
        Data_Morte = data_Morte;
    }

    /**
     * Sets the nationality of the artist.
     *
     * @param nacionalidade The nationality of the artist.
     */
    public void setNacionalidade(String nacionalidade) {
        Nacionalidade = nacionalidade;
    }

    /**
     * Returns the flag indicating whether the artwork is associated with Artsy.
     *
     * @return The flag indicating whether the artwork is associated with Artsy.
     */
    public int getIsArtsy() {
        return isArtsy;
    }

    /**
     * Sets the flag indicating whether the artwork is associated with Artsy.
     *
     * @param isArtsy The flag indicating whether the artwork is associated with Artsy.
     */
    public void setIsArtsy(int isArtsy) {
        this.isArtsy = isArtsy;
    }

    /**
     * Returns a string representation of the artist (the artist's name).
     *
     * @return The name of the artist.
     */
    @Override
    public String toString() {
        return nome_artista;
    }
}
