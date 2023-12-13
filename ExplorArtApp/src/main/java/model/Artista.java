package model;

import java.time.LocalDate;

public class Artista {
    private long id_artista;
    private String nome_artista;
    private LocalDate Data_Nascimento;
    private String Biografia;
    private LocalDate Data_Morte;
    private String Nacionalidade;
    private int IsArtsy;

    public Artista(long id_artista, String nome_artista, LocalDate data_Nascimento, String biografia, LocalDate data_Morte, String nacionalidade, int IsArtsy) {
        this.id_artista = id_artista;
        this.nome_artista = nome_artista;
        Data_Nascimento = data_Nascimento;
        Biografia = biografia;
        Data_Morte = data_Morte;
        Nacionalidade = nacionalidade;
        this.IsArtsy = IsArtsy;
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

    public String getNacionalidade() {
        return Nacionalidade;
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

    public void setNacionalidade(String nacionalidade) {
        Nacionalidade = nacionalidade;
    }

    public int getIsArtsy() {
        return IsArtsy;
    }

    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }

    @Override
    public String toString() {
        return nome_artista;
    }

}
//