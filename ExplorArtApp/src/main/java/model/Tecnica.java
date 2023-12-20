package model;

/**
 * The {@code Tecnica} class represents a technique with details such as technique ID and type.
 */
public class Tecnica {

    /** The technique ID. */
    private int id_Tecnica;

    /** The type of technique. */
    private String Tipo_Tecnica;

    /**
     * Constructs a {@code Tecnica} with the specified details.
     *
     * @param id_Tecnica The technique ID.
     * @param tipo_Tecnica The type of technique.
     */
    public Tecnica(int id_Tecnica, String tipo_Tecnica) {
        this.id_Tecnica = id_Tecnica;
        Tipo_Tecnica = tipo_Tecnica;
    }

    /**
     * Default constructor for the {@code Tecnica} class.
     */
    public Tecnica() {
    }

    /**
     * Returns the technique ID.
     *
     * @return The technique ID.
     */
    public int getId_Tecnica() {
        return id_Tecnica;
    }

    /**
     * Sets the technique ID.
     *
     * @param id_Tecnica The technique ID.
     */
    public void setId_Tecnica(int id_Tecnica) {
        this.id_Tecnica = id_Tecnica;
    }

    /**
     * Returns the type of technique.
     *
     * @return The type of technique.
     */
    public String getTipo_Tecnica() {
        return Tipo_Tecnica;
    }

    /**
     * Sets the type of technique.
     *
     * @param tipo_Tecnica The type of technique.
     */
    public void setTipo_Tecnica(String tipo_Tecnica) {
        Tipo_Tecnica = tipo_Tecnica;
    }

    /**
     * Returns a string representation of the technique type.
     *
     * @return A string representation of the technique type.
     */
    @Override
    public String toString() {
        return Tipo_Tecnica;
    }
}
