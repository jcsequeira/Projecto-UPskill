package model;

/**
 * Represents an art movement or style.
 */
public class Movimento {
    private int id_Estilo;
    private String Nome_Movimento;

    /**
     * Constructs a movement object with the specified details.
     *
     * @param id_Estilo      The ID of the art movement.
     * @param nome_Movimento The name of the art movement.
     */
    public Movimento(int id_Estilo, String nome_Movimento) {
        this.id_Estilo = id_Estilo;
        Nome_Movimento = nome_Movimento;
    }

    /**
     * Constructs an empty movement object.
     */
    public Movimento() {
    }

    /**
     * Gets the ID of the art movement.
     *
     * @return The ID of the art movement.
     */
    public int getId_Estilo() {
        return id_Estilo;
    }

    /**
     * Sets the ID of the art movement.
     *
     * @param id_Estilo The ID of the art movement to set.
     */
    public void setId_Estilo(int id_Estilo) {
        this.id_Estilo = id_Estilo;
    }

    /**
     * Gets the name of the art movement.
     *
     * @return The name of the art movement.
     */
    public String getNome_Movimento() {
        return Nome_Movimento;
    }

    /**
     * Sets the name of the art movement.
     *
     * @param nome_Movimento The name of the art movement to set.
     */
    public void setNome_Movimento(String nome_Movimento) {
        Nome_Movimento = nome_Movimento;
    }
}
