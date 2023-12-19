package model;

import java.time.LocalDate;

/**
 * Represents a galerista (gallery owner) in the system.
 */
public class Galerista {
    private LocalDate Data_Inicio_Atividade;
    private String password;
    private int id_colaborador;

    /**
     * Constructs a galerista with the specified details.
     *
     * @param data_Inicio_Atividade The start date of the galerista's activity.
     * @param password              The password of the galerista.
     * @param id_colaborador        The ID of the collaborator associated with the galerista.
     */
    public Galerista(LocalDate data_Inicio_Atividade, String password, int id_colaborador) {
        Data_Inicio_Atividade = data_Inicio_Atividade;
        this.password = password;
        this.id_colaborador = id_colaborador;
    }

    /**
     * Constructs an empty galerista.
     */
    public Galerista() {
    }

    /**
     * Gets the start date of the galerista's activity.
     *
     * @return The start date of the galerista's activity.
     */
    public LocalDate getData_Inicio_Atividade() {
        return Data_Inicio_Atividade;
    }

    /**
     * Sets the start date of the galerista's activity.
     *
     * @param data_Inicio_Atividade The start date of the galerista's activity to set.
     */
    public void setData_Inicio_Atividade(LocalDate data_Inicio_Atividade) {
        Data_Inicio_Atividade = data_Inicio_Atividade;
    }

    /**
     * Gets the password of the galerista.
     *
     * @return The password of the galerista.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the galerista.
     *
     * @param password The password of the galerista to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the ID of the collaborator associated with the galerista.
     *
     * @return The ID of the collaborator associated with the galerista.
     */
    public int getId_colaborador() {
        return id_colaborador;
    }

    /**
     * Sets the ID of the collaborator associated with the galerista.
     *
     * @param id_colaborador The ID of the collaborator to set.
     */
    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }
}
