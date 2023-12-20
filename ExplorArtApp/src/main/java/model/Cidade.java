package model;
/**
 * The {@code Cidade} class represents a city with details such as ID and name.
 */
public class Cidade {

    /** The unique ID of the city. */
    private int id_Cidade;

    /** The name of the city. */
    private String Nome_Cidade;

    /**
     * Constructs a {@code Cidade} with the specified details.
     *
     * @param id_Cidade The unique ID of the city.
     * @param nome_Cidade The name of the city.
     */
    public Cidade(int id_Cidade, String nome_Cidade) {
        this.id_Cidade = id_Cidade;
        Nome_Cidade = nome_Cidade;
    }

    /**
     * Default constructor for the {@code Cidade} class.
     */
    public Cidade() {
    }

    /**
     * Gets the unique ID of the city.
     *
     * @return The unique ID of the city.
     */
    public int getId_Cidade() {
        return id_Cidade;
    }

    /**
     * Sets the unique ID of the city.
     *
     * @param id_Cidade The unique ID of the city.
     */
    public void setId_Cidade(int id_Cidade) {
        this.id_Cidade = id_Cidade;
    }

    /**
     * Gets the name of the city.
     *
     * @return The name of the city.
     */
    public String getNome_Cidade() {
        return Nome_Cidade;
    }

    /**
     * Sets the name of the city.
     *
     * @param nome_Cidade The name of the city.
     */
    public void setNome_Cidade(String nome_Cidade) {
        Nome_Cidade = nome_Cidade;
    }

    /**
     * Returns a string representation of the city (the city's name).
     *
     * @return The name of the city.
     */
    @Override
    public String toString() {
        return Nome_Cidade;
    }
}
