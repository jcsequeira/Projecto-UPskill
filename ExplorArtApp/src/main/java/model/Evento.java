package model;

import java.time.LocalDate;

/**
 * The {@code Evento} class represents an event with details such as ID, name, start and end dates, description, gallery ID, and Artsy status.
 */
public class Evento {

    /** The unique ID of the event. */
    private int id_Expo;

    /** The name of the event. */
    private String Nome;

    /** The start date of the event. */
    private LocalDate Data_inicio;

    /** The end date of the event. */
    private LocalDate Data_Fim;

    /** The description of the event. */
    private String Descricao;

    /** The ID of the gallery associated with the event. */
    private int id_Galeria;

    /** The Artsy status of the event. */
    private int IsArtsy;

    /**
     * Constructs an {@code Evento} with the specified details.
     *
     * @param id_Expo The unique ID of the event.
     * @param nome The name of the event.
     * @param data_inicio The start date of the event.
     * @param data_Fim The end date of the event.
     * @param descricao The description of the event.
     * @param id_Galeria The ID of the gallery associated with the event.
     * @param isArtsy The Artsy status of the event.
     */
    public Evento(int id_Expo, String nome, LocalDate data_inicio, LocalDate data_Fim, String descricao, int id_Galeria, int isArtsy) {
        this.id_Expo = id_Expo;
        Nome = nome;
        Data_inicio = data_inicio;
        Data_Fim = data_Fim;
        Descricao = descricao;
        this.id_Galeria = id_Galeria;
        IsArtsy = isArtsy;
    }

    /**
     * Default constructor for the {@code Evento} class.
     */
    public Evento() {
    }

    /**
     * Gets the unique ID of the event.
     *
     * @return The unique ID of the event.
     */
    public int getId_Expo() {
        return id_Expo;
    }

    public void setId_Expo(int id_Expo) {
        this.id_Expo = id_Expo;
    }

    /**
     * Gets the name of the event.
     *
     * @return The name of the event.
     */
    public String getNome() {
        return Nome;
    }

    /**
     * Sets the name of the event.
     *
     * @param nome The name of the event.
     */
    public void setNome(String nome) {
        Nome = nome;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDate getData_inicio() {
        return Data_inicio;
    }

    /**
     * Sets the start date of the event.
     *
     * @param data_inicio The start date of the event.
     */
    public void setData_inicio(LocalDate data_inicio) {
        Data_inicio = data_inicio;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date of the event.
     */
    public LocalDate getData_Fim() {
        return Data_Fim;
    }

    /**
     * Sets the end date of the event.
     *
     * @param data_Fim The end date of the event.
     */
    public void setData_Fim(LocalDate data_Fim) {
        Data_Fim = data_Fim;
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * Sets the description of the event.
     *
     * @param descricao The description of the event.
     */
    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    /**
     * Gets the ID of the gallery associated with the event.
     *
     * @return The ID of the gallery associated with the event.
     */
    public int getId_Galeria() {
        return id_Galeria;
    }

    /**
     * Sets the ID of the gallery associated with the event.
     *
     * @param id_Galeria The ID of the gallery associated with the event.
     */
    public void setId_Galeria(int id_Galeria) {
        this.id_Galeria = id_Galeria;
    }

    public int getIsArtsy() {
        return IsArtsy;
    }

    /**
     * Sets the Artsy status of the event.
     *
     * @param isArtsy The Artsy status of the event.
     */
    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }
}
