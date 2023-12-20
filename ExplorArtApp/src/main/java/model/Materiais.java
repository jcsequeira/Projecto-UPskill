package model;

/**
 * The {@code Materiais} class represents a material with details such as ID and type.
 */
public class Materiais {

    /** The unique ID of the material. */
    private int id_Material;

    /** The type of the material. */
    private String Tipo_Material;

    /**
     * Constructs a {@code Materiais} with the specified details.
     *
     * @param id_Material The unique ID of the material.
     * @param tipo_Material The type of the material.
     */
    public Materiais(int id_Material, String tipo_Material) {
        this.id_Material = id_Material;
        Tipo_Material = tipo_Material;
    }

    /**
     * Default constructor for the {@code Materiais} class.
     */
    public Materiais() {
    }

    /**
     * Gets the unique ID of the material.
     *
     * @return The unique ID of the material.
     */
    public int getId_Material() {
        return id_Material;
    }

    /**
     * Sets the unique ID of the material.
     *
     * @param id_Material The unique ID of the material.
     */
    public void setId_Material(int id_Material) {
        this.id_Material = id_Material;
    }


    /**
     * Gets the type of the material.
     *
     * @return The type of the material.
     */
    public String getTipo_Material() {
        return Tipo_Material;
    }

    /**
     * Sets the type of the material.
     *
     * @param tipo_Material The type of the material.
     */
    public void setTipo_Material(String tipo_Material) {
        Tipo_Material = tipo_Material;
    }

    /**
     * Returns a string representation of the material type.
     *
     * @return A string representation of the material type.
     */
    @Override
    public String toString() {
        return Tipo_Material;
    }
}
