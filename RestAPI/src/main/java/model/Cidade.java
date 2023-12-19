package model;

/**
 * Represents a city in the system.
 */
public class Cidade {

    private int id_Cidade;
    private String Nome_Cidade;

    /**
     * Constructs a city with the specified details.
     *
     * @param id_Cidade    The ID of the city.
     * @param nome_Cidade  The name of the city.
     */
    public Cidade(int id_Cidade, String nome_Cidade) {
        this.id_Cidade = id_Cidade;
        Nome_Cidade = nome_Cidade;
    }

    /**
     * Constructs an empty city.
     */
    public Cidade() {
    }

    /**
     * Gets the ID of the city.
     *
     * @return The ID of the city.
     */
    public int getId_Cidade() {
        return id_Cidade;
    }

    /**
     * Sets the ID of the city.
     *
     * @param id_Cidade The ID of the city to set.
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
     * @param nome_Cidade The name of the city to set.
     */
    public void setNome_Cidade(String nome_Cidade) {
        Nome_Cidade = nome_Cidade;
    }
}
