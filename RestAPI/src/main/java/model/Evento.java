package model;

import java.time.LocalDate;

public class Evento {

    private int id_Expo;
    private String Nome;
    private LocalDate Data_inicio;
    private LocalDate Data_Fim;
    private String Descricao;
    private int id_Galeria;
    private int IsArtsy;

    public Evento(int id_Expo, String nome, LocalDate data_inicio, LocalDate data_Fim, String descricao, int id_Galeria, int isArtsy) {
        this.id_Expo = id_Expo;
        Nome = nome;
        Data_inicio = data_inicio;
        Data_Fim = data_Fim;
        Descricao = descricao;
        this.id_Galeria = id_Galeria;
        IsArtsy = isArtsy;
    }

    public Evento() {
    }

    public int getId_Expo() {
        return id_Expo;
    }

    public void setId_Expo(int id_Expo) {
        this.id_Expo = id_Expo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public LocalDate getData_inicio() {
        return Data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        Data_inicio = data_inicio;
    }

    public LocalDate getData_Fim() {
        return Data_Fim;
    }

    public void setData_Fim(LocalDate data_Fim) {
        Data_Fim = data_Fim;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public int getId_Galeria() {
        return id_Galeria;
    }

    public void setId_Galeria(int id_Galeria) {
        this.id_Galeria = id_Galeria;
    }

    public int getIsArtsy() {
        return IsArtsy;
    }

    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }
}

