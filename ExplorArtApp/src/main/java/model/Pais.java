package model;

/**
 * The {@code Pais} class represents a country with various details such as country code, name, and nationality.
 */
public class Pais {

    /** The country code. */
    private int Codigo_Pais;

    /** The name of the country. */
    private String Nome_Pais;

    /** The nationality associated with the country. */
    private String Nacionalidade;

    /**
     * Constructs a {@code Pais} with the specified details.
     *
     * @param codigo_Pais The country code.
     * @param nome_Pais The name of the country.
     * @param nacionalidade The nationality associated with the country.
     */
    public Pais(int codigo_Pais, String nome_Pais, String nacionalidade) {
        this.Codigo_Pais = codigo_Pais;
        this.Nome_Pais = nome_Pais;
        this.Nacionalidade = nacionalidade;
    }

    /**
     * Default constructor for the {@code Pais} class.
     */
    public Pais() {
    }

    /**
     * Returns the country code.
     *
     * @return The country code.
     */
    public int getCodigo_Pais() {
        return Codigo_Pais;
    }

    /**
     * Sets the country code.
     *
     * @param codigo_Pais The country code.
     */
    public void setCodigo_Pais(int codigo_Pais) {
        this.Codigo_Pais = codigo_Pais;
    }

    /**
     * Returns the name of the country.
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
        this.Nome_Pais = nome_Pais;
    }

    /**
     * Returns the nationality associated with the country.
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
        this.Nacionalidade = nacionalidade;
    }

    /**
     * Returns a string representation of the nationality.
     *
     * @return A string representation of the nationality.
     */
    @Override
    public String toString() {
        return Nacionalidade;
    }
}
