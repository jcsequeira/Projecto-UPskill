package model;

/**
 * Represents materials used in the context of art.
 */
public class Materiais {
    private int id_Material;
    private String Tipo_Material;

    /**
     * Constructs a materials object with the specified details.
     *
     * @param id_Material   The ID of the material.
     * @param tipo_Material The type of material.
     */
    public Materiais(int id_Material, String tipo_Material) {
        this.id_Material = id_Material;
        Tipo_Material = tipo_Material;
    }

    /**
     * Constructs an empty materials object.
     */
    public Materiais() {
    }

    /**
     * Gets the ID of the material.
     *
     * @return The ID of the material.
     */
    public int getId_Material() {
        return id_Material;
    }

    /**
     * Sets the ID of the material.
     *
     * @param id_Material The ID of the material to set.
     */
    public void setId_Material(int id_Material) {
        this.id_Material = id_Material;
    }

    /**
     * Gets the type of material.
     *
     * @return The type of material.
     */
    public String getTipo_Material() {
        return Tipo_Material;
    }

    /**
     * Sets the type of material.
     *
     * @param tipo_Material The type of material to set.
     */
    public void setTipo_Material(String tipo_Material) {
        Tipo_Material = tipo_Material;
    }
}
