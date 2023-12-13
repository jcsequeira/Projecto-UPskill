package model;

public class Tecnica {
    private int id_Tecnica;
    private String Tipo_Tecnica;

    public Tecnica(int id_Tecnica, String tipo_Tecnica) {
        this.id_Tecnica = id_Tecnica;
        Tipo_Tecnica = tipo_Tecnica;
    }

    public Tecnica() {
    }


    public int getId_Tecnica() {
        return id_Tecnica;
    }

    public void setId_Tecnica(int id_Tecnica) {
        this.id_Tecnica = id_Tecnica;
    }

    public String getTipo_Tecnica() {
        return Tipo_Tecnica;
    }

    public void setTipo_Tecnica(String tipo_Tecnica) {
        Tipo_Tecnica = tipo_Tecnica;
    }

    @Override
    public String toString() {
        return Tipo_Tecnica;
    }
}
