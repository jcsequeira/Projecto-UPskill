package model;

public class Cidade {

    private int id_Cidade;
    private String Nome_Cidade;

    public Cidade(int id_Cidade, String nome_Cidade) {
        this.id_Cidade = id_Cidade;
        Nome_Cidade = nome_Cidade;
    }

    public Cidade() {
    }

    public int getId_Cidade() {
        return id_Cidade;
    }

    public void setId_Cidade(int id_Cidade) {
        this.id_Cidade = id_Cidade;
    }

    public String getNome_Cidade() {
        return Nome_Cidade;
    }

    public void setNome_Cidade(String nome_Cidade) {
        Nome_Cidade = nome_Cidade;
    }

    @Override
    public String toString() {
        return Nome_Cidade;
    }
}
