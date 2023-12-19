package model;

import java.time.LocalDate;

/**
 * Represents an event in the system.
 */
public class Evento {

    private int id_Expo;
    private String Nome;
    private LocalDate Data_inicio;
    private LocalDate Data_Fim;
    private String Descricao;
    private int id_Galeria;
    private int IsArtsy;

    /**
     * Constructs an event with the specified details.
     *
     * @param id_Expo      The ID of the event.
     * @param nome         The name of the event.
     * @param data_inicio  The start date of the event.
     * @param data_Fim     The end date of the event.
     * @param descricao    The description of the event.
     * @param id_Galeria   The ID of the gallery associated with the event.
     * @param isArtsy      The flag indicating whether the event is associated with Artsy.
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
     * Constructs an empty event.
     */
    public Evento() {
    }

    /**
     * Gets the ID of the event.
     *
     * @return The ID of the event.
     */
    public int getId_Expo() {
        return id_Expo;
    }

    /**
     * Sets the ID of the event.
     *
     * @param id_Expo The ID of the event to set.
     */
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
     * @param nome The name of the event to set.
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
     * @param data_inicio The start date of the event to set.
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
     * @param data_Fim The end date of the event to set.
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
     * @param descricao The description of the event to set.
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
     * @param id_Galeria The ID of the gallery to set.
     */
    public void setId_Galeria(int id_Galeria) {
        this.id_Galeria = id_Galeria;
    }

    /**
     * Gets the flag indicating whether the event is associated with Artsy.
     *
     * @return The flag indicating whether the event is associated with Artsy.
     */
    public int getIsArtsy() {
        return IsArtsy;
    }

    /**
     * Sets the flag indicating whether the event is associated with Artsy.
     *
     * @param isArtsy The flag to set.
     */
    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }
}
