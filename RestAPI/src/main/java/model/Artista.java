package model;

import java.time.LocalDate;

public class Artista {
    private long id_artista;
    private String nome_artista;
    private LocalDate Data_Nascimento;
    private String Biografia;
    private LocalDate Data_Morte;
    private int Codigo_Pais;

    public Artista(long id_artista, String nome_artista, LocalDate data_Nascimento, String biografia, LocalDate data_Morte, int codigo_Pais) {
        this.id_artista = id_artista;
        this.nome_artista = nome_artista;
        Data_Nascimento = data_Nascimento;
        Biografia = biografia;
        Data_Morte = data_Morte;
        Codigo_Pais = codigo_Pais;
    }

    public Artista() {
    }

    public long getId_artista() {
        return id_artista;
    }

    public String getNome_artista() {
        return nome_artista;
    }

    public LocalDate getData_Nascimento() {
        return Data_Nascimento;
    }

    public String getBiografia() {
        return Biografia;
    }

    public LocalDate getData_Morte() {
        return Data_Morte;
    }

    public int getCodigo_Pais() {
        return Codigo_Pais;
    }

    public void setId_artista(long id_artista) {
        this.id_artista = id_artista;
    }

    public void setNome_artista(String nome_artista) {
        this.nome_artista = nome_artista;
    }

    public void setData_Nascimento(LocalDate data_Nascimento) {
        Data_Nascimento = data_Nascimento;
    }

    public void setBiografia(String biografia) {
        Biografia = biografia;
    }

    public void setData_Morte(LocalDate data_Morte) {
        Data_Morte = data_Morte;
    }

    public void setCodigo_Pais(int codigo_Pais) {
        Codigo_Pais = codigo_Pais;
    }
}
