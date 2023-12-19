package model;
/**
 * The {@code Tecnica} class represents an artistic technique with its unique ID and type.
 */
public class Tecnica {

    /**
     * The unique ID of the artistic technique.
     */
    private int id_Tecnica;

    /**
     * The type of the artistic technique.
     */
    private String Tipo_Tecnica;

    /**
     * Constructs a new {@code Tecnica} instance with the specified parameters.
     *
     * @param id_Tecnica    The unique ID of the artistic technique.
     * @param tipo_Tecnica  The type of the artistic technique.
     */
    public Tecnica(int id_Tecnica, String tipo_Tecnica) {
        this.id_Tecnica = id_Tecnica;
        Tipo_Tecnica = tipo_Tecnica;
    }

    /**
     * Constructs a default {@code Tecnica} instance.
     */
    public Tecnica() {
    }

    /**
     * Gets the unique ID of the artistic technique.
     *
     * @return The unique ID of the artistic technique.
     */
    public int getId_Tecnica() {
        return id_Tecnica;
    }

    /**
     * Sets the unique ID of the artistic technique.
     *
     * @param id_Tecnica The unique ID of the artistic technique.
     */
    public void setId_Tecnica(int id_Tecnica) {
        this.id_Tecnica = id_Tecnica;
    }

    /**
     * Gets the type of the artistic technique.
     *
     * @return The type of the artistic technique.
     */
    public String getTipo_Tecnica() {
        return Tipo_Tecnica;
    }

    /**
     * Sets the type of the artistic technique.
     *
     * @param tipo_Tecnica The type of the artistic technique.
     */
    public void setTipo_Tecnica(String tipo_Tecnica) {
        Tipo_Tecnica = tipo_Tecnica;
    }
}
