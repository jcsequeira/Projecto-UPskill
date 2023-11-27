package model;

public class Movimento {
    private int id_Estilo;
    private String Nome_Movimento;

    public Movimento(int id_Estilo, String nome_Movimento) {
        this.id_Estilo = id_Estilo;
        Nome_Movimento = nome_Movimento;
    }

    public Movimento() {
    }

    public int getId_Estilo() {
        return id_Estilo;
    }

    public void setId_Estilo(int id_Estilo) {
        this.id_Estilo = id_Estilo;
    }

    public String getNome_Movimento() {
        return Nome_Movimento;
    }

    public void setNome_Movimento(String nome_Movimento) {
        Nome_Movimento = nome_Movimento;
    }
}
