package model;

public class Colaborador {

    private int id_colaborador;
    private String Nome_Colaborador;
    private String Email;
    private String Telefone;
    private int Codigo_Pais;

    public Colaborador(int id_colaborador, String nome_Colaborador, String email, String telefone, int codigo_Pais) {
        this.id_colaborador = id_colaborador;
        Nome_Colaborador = nome_Colaborador;
        Email = email;
        Telefone = telefone;
        Codigo_Pais = codigo_Pais;
    }

    public Colaborador() {
    }

    public int getId_colaborador() {
        return id_colaborador;
    }

    public void setId_colaborador(int id_colaborador) {
        this.id_colaborador = id_colaborador;
    }

    public String getNome_Colaborador() {
        return Nome_Colaborador;
    }

    public void setNome_Colaborador(String nome_Colaborador) {
        Nome_Colaborador = nome_Colaborador;
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

    public int getCodigo_Pais() {
        return Codigo_Pais;
    }

    public void setCodigo_Pais(int codigo_Pais) {
        Codigo_Pais = codigo_Pais;
    }

    @Override
    public String toString() {
        return Nome_Colaborador;
    }
}


