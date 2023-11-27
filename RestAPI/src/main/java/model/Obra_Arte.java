package model;

import java.time.LocalDate;

public class Obra_Arte {
    private int id_Obra_Arte;
    private String Titulo;
    private String Link_Imagem;
    private LocalDate Ano_Criacao;
    private float Preco;
    private float Largura;
    private float Profundidade;
    private float Diametro;
    private int IsActive;
    private int id_artista;
    private int id_Tecnica;
    private int id_Estilo;
    private int IsArtsy;

    public Obra_Arte(int id_Obra_Arte, String titulo, String link_Imagem, LocalDate ano_Criacao,
                     float preco, float largura, float profundidade, float diametro, int isActive,
                     int id_artista, int id_Tecnica, int id_Estilo, int isArtsy) {
        this.id_Obra_Arte = id_Obra_Arte;
        Titulo = titulo;
        Link_Imagem = link_Imagem;
        Ano_Criacao = ano_Criacao;
        Preco = preco;
        Largura = largura;
        Profundidade = profundidade;
        Diametro = diametro;
        this.IsActive = isActive;
        this.id_artista = id_artista;
        this.id_Tecnica = id_Tecnica;
        this.id_Estilo = id_Estilo;
        this.IsArtsy = isArtsy;
    }

    public Obra_Arte() {
    }

    public int getId_Obra_Arte() {
        return id_Obra_Arte;
    }

    public void setId_Obra_Arte(int id_Obra_Arte) {
        this.id_Obra_Arte = id_Obra_Arte;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getLink_Imagem() {
        return Link_Imagem;
    }

    public void setLink_Imagem(String link_Imagem) {
        Link_Imagem = link_Imagem;
    }

    public LocalDate getAno_Criacao() {
        return Ano_Criacao;
    }

    public void setAno_Criacao(LocalDate ano_Criacao) {
        Ano_Criacao = ano_Criacao;
    }

    public float getPreco() {
        return Preco;
    }

    public void setPreco(float preco) {
        Preco = preco;
    }

    public float getLargura() {
        return Largura;
    }

    public void setLargura(float largura) {
        Largura = largura;
    }

    public float getProfundidade() {
        return Profundidade;
    }

    public void setProfundidade(float profundidade) {
        Profundidade = profundidade;
    }

    public float getDiametro() {
        return Diametro;
    }

    public void setDiametro(float diametro) {
        Diametro = diametro;
    }

    public int getIsActive() {
        return IsActive;
    }

    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    public int getId_artista() {
        return id_artista;
    }

    public void setId_artista(int id_artista) {
        this.id_artista = id_artista;
    }

    public int getId_Tecnica() {
        return id_Tecnica;
    }

    public void setId_Tecnica(int id_Tecnica) {
        this.id_Tecnica = id_Tecnica;
    }

    public int getId_Estilo() {
        return id_Estilo;
    }

    public void setId_Estilo(int id_Estilo) {
        this.id_Estilo = id_Estilo;
    }

    public int getIsArtsy() {
        return IsArtsy;
    }

    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }
}
