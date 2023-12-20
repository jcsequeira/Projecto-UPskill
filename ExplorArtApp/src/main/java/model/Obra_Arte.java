package model;

import java.time.LocalDate;

/**
 * The {@code Obra_Arte} class represents an artwork with various details such as ID, title, and creation information.
 */
public class Obra_Arte {

    /** The unique ID of the artwork. */
    private int id_Obra_Arte;

    /** The title of the artwork. */
    private String Titulo;

    /** The link to the image of the artwork. */
    private String Link_Imagem;

    /** The creation date of the artwork. */
    private LocalDate Ano_Criacao;

    /** The price of the artwork. */
    private float Preco;

    /** The height of the artwork. */
    private float altura;

    /** The width of the artwork. */
    private float Largura;

    /** The depth of the artwork. */
    private float Profundidade;

    /** The diameter of the artwork. */
    private float Diametro;

    /** Indicator for the active status of the artwork. */
    private int IsActive;

    /** The ID of the artist associated with the artwork. */
    private int id_artista;

    /** The ID of the technique used in the artwork. */
    private int id_Tecnica;

    /** The ID of the artistic style of the artwork. */
    private int id_Estilo;

    /** Indicator for the artwork being associated with Artsy. */
    private int IsArtsy;

    /** The ID of the material used in the artwork. */
    private int id_Material;

    /**
     * Constructs an {@code Obra_Arte} with the specified details.
     *
     * @param id_Obra_Arte The unique ID of the artwork.
     * @param titulo The title of the artwork.
     * @param link_Imagem The link to the image of the artwork.
     * @param ano_Criacao The creation date of the artwork.
     * @param preco The price of the artwork.
     * @param altura The height of the artwork.
     * @param largura The width of the artwork.
     * @param profundidade The depth of the artwork.
     * @param diametro The diameter of the artwork.
     * @param isActive Indicator for the active status of the artwork.
     * @param id_artista The ID of the artist associated with the artwork.
     * @param id_Tecnica The ID of the technique used in the artwork.
     * @param id_Estilo The ID of the artistic style of the artwork.
     * @param isArtsy Indicator for the artwork being associated with Artsy.
     * @param id_Material The ID of the material used in the artwork.
     */
    public Obra_Arte(int id_Obra_Arte, String titulo, String link_Imagem, LocalDate ano_Criacao, float preco,
                     float altura, float largura, float profundidade, float diametro, int isActive, int id_artista,
                     int id_Tecnica, int id_Estilo, int isArtsy, int id_Material) {
        // Initialization of fields...
    }

    /**
     * Default constructor for the {@code Obra_Arte} class.
     */
    public Obra_Arte() {
    }

    /**
     * Returns the unique ID of the artwork.
     *
     * @return The unique ID of the artwork.
     */
    public int getId_Obra_Arte() {
        return id_Obra_Arte;
    }

    /**
     * Sets the unique ID of the artwork.
     *
     * @param id_Obra_Arte The unique ID of the artwork.
     */
    public void setId_Obra_Arte(int id_Obra_Arte) {
        this.id_Obra_Arte = id_Obra_Arte;
    }

    /**
     * Returns the title of the artwork.
     *
     * @return The title of the artwork.
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * Sets the title of the artwork.
     *
     * @param titulo The title of the artwork.
     */
    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    /**
     * Returns the link to the image of the artwork.
     *
     * @return The link to the image of the artwork.
     */
    public String getLink_Imagem() {
        return Link_Imagem;
    }

    /**
     * Sets the link to the image of the artwork.
     *
     * @param link_Imagem The link to the image of the artwork.
     */
    public void setLink_Imagem(String link_Imagem) {
        Link_Imagem = link_Imagem;
    }

    /**
     * Returns the creation date of the artwork.
     *
     * @return The creation date of the artwork.
     */
    public LocalDate getAno_Criacao() {
        return Ano_Criacao;
    }

    /**
     * Sets the creation date of the artwork.
     *
     * @param ano_Criacao The creation date of the artwork.
     */
    public void setAno_Criacao(LocalDate ano_Criacao) {
        Ano_Criacao = ano_Criacao;
    }

    /**
     * Returns the price of the artwork.
     *
     * @return The price of the artwork.
     */
    public float getPreco() {
        return Preco;
    }


    /**
     * Sets the price of the artwork.
     *
     * @param preco The price of the artwork.
     */
    public void setPreco(float preco) {
        Preco = preco;
    }

    /**
     * Returns the height of the artwork.
     *
     * @return The height of the artwork.
     */
    public float getAltura() {
        return altura;
    }

    /**
     * Sets the height of the artwork.
     *
     * @param altura The height of the artwork.
     */
    public void setAltura(float altura) {
        this.altura = altura;
    }

    /**
     * Returns the width of the artwork.
     *
     * @return The width of the artwork.
     */
    public float getLargura() {
        return Largura;
    }

    /**
     * Sets the width of the artwork.
     *
     * @param largura The width of the artwork.
     */
    public void setLargura(float largura) {
        Largura = largura;
    }

    /**
     * Returns the depth of the artwork.
     *
     * @return The depth of the artwork.
     */
    public float getProfundidade() {
        return Profundidade;
    }

    /**
     * Sets the depth of the artwork.
     *
     * @param profundidade The depth of the artwork.
     */
    public void setProfundidade(float profundidade) {
        Profundidade = profundidade;
    }

    /**
     * Returns the diameter of the artwork.
     *
     * @return The diameter of the artwork.
     */
    public float getDiametro() {
        return Diametro;
    }

    /**
     * Sets the diameter of the artwork.
     *
     * @param diametro The diameter of the artwork.
     */
    public void setDiametro(float diametro) {
        Diametro = diametro;
    }

    /**
     * Returns the indicator for the active status of the artwork.
     *
     * @return The indicator for the active status of the artwork.
     */
    public int getIsActive() {
        return IsActive;
    }

    /**
     * Sets the indicator for the active status of the artwork.
     *
     * @param isActive The indicator for the active status of the artwork.
     */
    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    /**
     * Returns the ID of the artist associated with the artwork.
     *
     * @return The ID of the artist associated with the artwork.
     */
    public int getId_artista() {
        return id_artista;
    }

    /**
     * Sets the ID of the artist associated with the artwork.
     *
     * @param id_artista The ID of the artist associated with the artwork.
     */
    public void setId_artista(int id_artista) {
        this.id_artista = id_artista;
    }

    /**
     * Returns the ID of the technique used in the artwork.
     *
     * @return The ID of the technique used in the artwork.
     */
    public int getId_Tecnica() {
        return id_Tecnica;
    }

    /**
     * Sets the ID of the technique used in the artwork.
     *
     * @param id_Tecnica The ID of the technique used in the artwork.
     */
    public void setId_Tecnica(int id_Tecnica) {
        this.id_Tecnica = id_Tecnica;
    }

    /**
     * Returns the ID of the artistic style of the artwork.
     *
     * @return The ID of the artistic style of the artwork.
     */
    public int getId_Estilo() {
        return id_Estilo;
    }

    /**
     * Sets the ID of the artistic style of the artwork.
     *
     * @param id_Estilo The ID of the artistic style of the artwork.
     */
    public void setId_Estilo(int id_Estilo) {
        this.id_Estilo = id_Estilo;
    }

    /**
     * Returns the indicator for the artwork being associated with Artsy.
     *
     * @return The indicator for the artwork being associated with Artsy.
     */
    public int getIsArtsy() {
        return IsArtsy;
    }

    /**
     * Sets the indicator for the artwork being associated with Artsy.
     *
     * @param isArtsy The indicator for the artwork being associated with Artsy.
     */
    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }

    /**
     * Returns the ID of the material used in the artwork.
     *
     * @return The ID of the material used in the artwork.
     */
    public int getId_Material() {
        return id_Material;
    }

    /**
     * Sets the ID of the material used in the artwork.
     *
     * @param id_Material The ID of the material used in the artwork.
     */
    public void setId_Material(int id_Material) {
        this.id_Material = id_Material;
    }

    /**
     * Returns a string representation of the artwork title.
     *
     * @return A string representation of the artwork title.
     */
    @Override
    public String toString() {
        return Titulo;
    }
}
