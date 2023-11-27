package model;

public class Galeria {
    private int id_Galeria;
    private String Nome_Galeria;
    private String Morada;
    private String Website;
    private String Email;
    private String Telefone;
    private int id_Cidade;
    private int id_colaborador;
    private int IsArtsy;

    public Galeria(int id_Galeria, String nome_Galeria, String morada, String website, String email,
                   String telefone, int id_Cidade, int id_colaborador, int isArtsy) {
        this.id_Galeria = id_Galeria;
        Nome_Galeria = nome_Galeria;
        Morada = morada;
        Website = website;
        Email = email;
        Telefone = telefone;
        this.id_Cidade = id_Cidade;
        this.id_colaborador = id_colaborador;
        IsArtsy = isArtsy;
    }

    public Galeria() {
    }

    public int getId_Galeria() {
        return id_Galeria;
    }

    public void setId_Galeria(int id_Galeria) {
        this.id_Galeria = id_Galeria;
    }

    public String getNome_Galeria() {
        return Nome_Galeria;
    }

    public void setNome_Galeria(String nome_Galeria) {
        Nome_Galeria = nome_Galeria;
    }

    public String getMorada() {
        return Morada;
    }

    public void setMorada(String morada) {
        Morada = morada;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public int getId_Cidade() {
        return id_Cidade;
    }

    public void setId_Cidade(int id_Cidade) {
        this.id_Cidade = id_Cidade;
    }

    public int getId_colaborador() {
        return id_colaborador;
    }

    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

    public int getIsArtsy() {
        return IsArtsy;
    }

    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }
}
