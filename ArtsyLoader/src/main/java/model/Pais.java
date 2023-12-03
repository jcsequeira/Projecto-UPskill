package model;

public class Pais {

    private int Codigo_Pais;
    private String Nome_Pais;
    private String Nacionalidade;

    public Pais(int codigo_Pais, String nome_Pais, String nacionalidade) {
        Codigo_Pais = codigo_Pais;
        Nome_Pais = nome_Pais;
        Nacionalidade = nacionalidade;
    }

    public Pais() {
    }

    public int getCodigo_Pais() {
        return Codigo_Pais;
    }

    public void setCodigo_Pais(int codigo_Pais) {
        Codigo_Pais = codigo_Pais;
    }

    public String getNome_Pais() {
        return Nome_Pais;
    }

    public void setNome_Pais(String nome_Pais) {
        Nome_Pais = nome_Pais;
    }

    public String getNacionalidade() {
        return Nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        Nacionalidade = nacionalidade;
    }
}
