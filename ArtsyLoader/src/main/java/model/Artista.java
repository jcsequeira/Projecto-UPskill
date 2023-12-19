package model;

import java.time.LocalDate;

/**
 * Represents an artist in the system.
 */
public class Artista {

    private long id_artista;
    private String nome_artista;
    private LocalDate Data_Nascimento;
    private String Biografia;
    private LocalDate Data_Morte;
    private String Nacionalidade;
    private int IsArtsy;

    /**
     * Constructs an artist with the specified details.
     *
     * @param id_artista      The ID of the artist.
     * @param nome_artista    The name of the artist.
     * @param data_Nascimento The birth date of the artist.
     * @param biografia       The biography of the artist.
     * @param data_Morte      The death date of the artist.
     * @param nacionalidade   The nationality of the artist.
     * @param IsArtsy         The flag indicating whether the artist is associated with Artsy.
     */
    public Artista(long id_artista, String nome_artista, LocalDate data_Nascimento, String biografia, LocalDate data_Morte, String nacionalidade, int IsArtsy) {
        this.id_artista = id_artista;
        this.nome_artista = nome_artista;
        Data_Nascimento = data_Nascimento;
        Biografia = biografia;
        Data_Morte = data_Morte;
        Nacionalidade = nacionalidade;
        this.IsArtsy = IsArtsy;
    }

    /**
     * Constructs an empty artist.
     */
    public Artista() {
    }

    /**
     * Gets the ID of the artist.
     *
     * @return The ID of the artist.
     */
    public long getId_artista() {
        return id_artista;
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
     * Sets the ID of the artist.
     *
     * @param id_artista The ID of the artist to set.
     */
    public void setId_artista(long id_artista) {
        this.id_artista = id_artista;
    }

    /**
     * Sets the name of the artist.
     *
     * @param nome_artista The name of the artist to set.
     */
    public void setNome_artista(String nome_artista) {
        this.nome_artista = nome_artista;
    }

    /**
     * Sets the birth date of the artist.
     *
     * @param data_Nascimento The birth date of the artist to set.
     */
    public void setData_Nascimento(LocalDate data_Nascimento) {
        Data_Nascimento = data_Nascimento;
    }

    /**
     * Sets the biography of the artist.
     *
     * @param biografia The biography of the artist to set.
     */
    public void setBiografia(String biografia) {
        Biografia = biografia;
    }

    /**
     * Sets the death date of the artist.
     *
     * @param data_Morte The death date of the artist to set.
     */
    public void setData_Morte(LocalDate data_Morte) {
        Data_Morte = data_Morte;
    }

    /**
     * Sets the nationality of the artist.
     *
     * @param nacionalidade The nationality of the artist to set.
     */
    public void setNacionalidade(String nacionalidade) {
        Nacionalidade = nacionalidade;
    }

    /**
     * Gets the flag indicating whether the artist is associated with Artsy.
     *
     * @return The flag indicating whether the artist is associated with Artsy.
     */
    public int getIsArtsy() {
        return IsArtsy;
    }

    /**
     * Sets the flag indicating whether the artist is associated with Artsy.
     *
     * @param isArtsy The flag indicating whether the artist is associated with Artsy to set.
     */
    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }
}
