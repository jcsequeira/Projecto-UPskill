package model;

import apiservice.ApiService;

import java.io.IOException;
import java.time.LocalDate;

/**
 * The {@code Galerista} class represents a gallery owner with details such as the start date of activity,
 * password, and collaborator ID.
 */
public class Galerista {
    /** The start date of the gallery owner's activity. */
    private LocalDate Data_Inicio_Atividade;

    /** The password associated with the gallery owner. */
    private String password;

    /** The ID of the collaborator associated with the gallery owner. */
    private int id_colaborador;

    /**
     * Constructs a {@code Galerista} with the specified details.
     *
     * @param data_Inicio_Atividade The start date of the gallery owner's activity.
     * @param password The password associated with the gallery owner.
     * @param id_colaborador The ID of the collaborator associated with the gallery owner.
     */
    public Galerista(LocalDate data_Inicio_Atividade, String password, int id_colaborador) {
        Data_Inicio_Atividade = data_Inicio_Atividade;
        this.password = password;
        this.id_colaborador = id_colaborador;
    }

    /**
     * Default constructor for the {@code Galerista} class.
     */
    public Galerista() {
    }

    /**
     * Gets the start date of the gallery owner's activity.
     *
     * @return The start date of the gallery owner's activity.
     */
    public LocalDate getData_Inicio_Atividade() {
        return Data_Inicio_Atividade;
    }

    /**
     * Sets the start date of the gallery owner's activity.
     *
     * @param data_Inicio_Atividade The start date of the gallery owner's activity.
     */
    public void setData_Inicio_Atividade(LocalDate data_Inicio_Atividade) {
        Data_Inicio_Atividade = data_Inicio_Atividade;
    }


    /**
     * Gets the password associated with the gallery owner.
     *
     * @return The password associated with the gallery owner.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with the gallery owner.
     *
     * @param password The password associated with the gallery owner.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the ID of the collaborator associated with the gallery owner.
     *
     * @return The ID of the collaborator associated with the gallery owner.
     */
    public int getId_colaborador() {
        return id_colaborador;
    }

    /**
     * Sets the ID of the collaborator associated with the gallery owner.
     *
     * @param id_colaborador The ID of the collaborator associated with the gallery owner.
     */
    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

    /**
     * Returns a string representation of the gallery owner, including details retrieved from the API.
     *
     * @return A string representation of the gallery owner.
     * @throws RuntimeException If an IOException occurs while fetching data from the API.
     */
    @Override
    public String toString() {
        try {
            return String.valueOf(ApiService.getItem("http://localhost:4567/api/colaboradores/" + id_colaborador,
                    Colaborador.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
