package model;

import apiservice.ApiService;

import java.io.IOException;
import java.time.LocalDate;

public class Galerista {
    private LocalDate Data_Inicio_Atividade;
    private String password;
    private int id_colaborador;

    public Galerista(LocalDate data_Inicio_Atividade, String password, int id_colaborador) {
        Data_Inicio_Atividade = data_Inicio_Atividade;
        this.password = password;
        this.id_colaborador = id_colaborador;
    }

    public Galerista() {
    }

    public LocalDate getData_Inicio_Atividade() {
        return Data_Inicio_Atividade;
    }

    public void setData_Inicio_Atividade(LocalDate data_Inicio_Atividade) {
        Data_Inicio_Atividade = data_Inicio_Atividade;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId_colaborador() {
        return id_colaborador;
    }

    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

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
