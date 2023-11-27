package model;

public class Administrador {

    private String password;
    private int id_colaborador;

    public Administrador(String password, int id_colaborador) {
        this.password = password;
        this.id_colaborador = id_colaborador;
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
}
