package model;

/**
 * The {@code Pais} class represents a country with its unique code, name, and nationality.
 */
public class Pais {

    /**
     * The unique code of the country.
     */
    private int Codigo_Pais;

    /**
     * The name of the country.
     */
    private String Nome_Pais;

    /**
     * The nationality associated with the country.
     */
    private String Nacionalidade;

    /**
     * Constructs a new {@code Pais} instance with the specified parameters.
     *
     * @param codigo_Pais   The unique code of the country.
     * @param nome_Pais     The name of the country.
     * @param nacionalidade The nationality associated with the country.
     */
    public Pais(int codigo_Pais, String nome_Pais, String nacionalidade) {
        Codigo_Pais = codigo_Pais;
        Nome_Pais = nome_Pais;
        Nacionalidade = nacionalidade;
    }

    /**
     * Constructs a default {@code Pais} instance.
     */
    public Pais() {
    }

    /**
     * Gets the unique code of the country.
     *
     * @return The unique code of the country.
     */
    public int getCodigo_Pais() {
        return Codigo_Pais;
    }

    /**
     * Sets the unique code of the country.
     *
     * @param codigo_Pais The unique code of the country.
     */
    public void setCodigo_Pais(int codigo_Pais) {
        Codigo_Pais = codigo_Pais;
    }

    /**
     * Gets the name of the country.
     *
     * @return The name of the country.
     */
    public String getNome_Pais() {
        return Nome_Pais;
    }

    /**
     * Sets the name of the country.
     *
     * @param nome_Pais The name of the country.
     */
    public void setNome_Pais(String nome_Pais) {
        Nome_Pais = nome_Pais;
    }

    /**
     * Gets the nationality associated with the country.
     *
     * @return The nationality associated with the country.
     */
    public String getNacionalidade() {
        return Nacionalidade;
    }

    /**
     * Sets the nationality associated with the country.
     *
     * @param nacionalidade The nationality associated with the country.
     */
    public void setNacionalidade(String nacionalidade) {
        Nacionalidade = nacionalidade;
    }
}

