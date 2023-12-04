package model;

public class Materiais {
    private int id_Material;
    private String Tipo_Material;

    public Materiais(int id_Material, String tipo_Material) {
        this.id_Material = id_Material;
        Tipo_Material = tipo_Material;
    }

    public Materiais() {
    }

    public int getId_Material() {
        return id_Material;
    }

    public void setId_Material(int id_Material) {
        this.id_Material = id_Material;
    }

    public String getTipo_Material() {
        return Tipo_Material;
    }

    public void setTipo_Material(String tipo_Material) {
        Tipo_Material = tipo_Material;
    }
}
