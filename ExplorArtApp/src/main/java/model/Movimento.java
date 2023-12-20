package model;

/**
 * The {@code Movimento} class represents a movement with details such as ID and name.
 */
public class Movimento {

    /** The unique ID of the movement style. */
    private int id_Estilo;

    /** The name of the movement. */
    private String Nome_Movimento;

    /**
     * Constructs a {@code Movimento} with the specified details.
     *
     * @param id_Estilo The unique ID of the movement style.
     * @param nome_Movimento The name of the movement.
     */
    public Movimento(int id_Estilo, String nome_Movimento) {
        this.id_Estilo = id_Estilo;
        Nome_Movimento = nome_Movimento;
    }

    /**
     * Default constructor for the {@code Movimento} class.
     */
    public Movimento() {
    }

    /**
     * Gets the unique ID of the movement style.
     *
     * @return The unique ID of the movement style.
     */
    public int getId_Estilo() {
        return id_Estilo;
    }

    /**
     * Sets the unique ID of the movement style.
     *
     * @param id_Estilo The unique ID of the movement style.
     */
    public void setId_Estilo(int id_Estilo) {
        this.id_Estilo = id_Estilo;
    }


    /**
     * Gets the name of the movement.
     *
     * @return The name of the movement.
     */
    public String getNome_Movimento() {
        return Nome_Movimento;
    }

    /**
     * Sets the name of the movement.
     *
     * @param nome_Movimento The name of the movement.
     */
    public void setNome_Movimento(String nome_Movimento) {
        Nome_Movimento = nome_Movimento;
    }

    /**
     * Returns a string representation of the movement name.
     *
     * @return A string representation of the movement name.
     */
    @Override
    public String toString() {
        return Nome_Movimento;
    }
}
