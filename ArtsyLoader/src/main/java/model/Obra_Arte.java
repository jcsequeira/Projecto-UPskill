package model;

import java.time.LocalDate;
/**
 * Represents a work of art.
 */
public class Obra_Arte {
    private int id_Obra_Arte;
    private String Titulo;
    private String Link_Imagem;
    private LocalDate Ano_Criacao;
    private float Preco;
    private float altura;
    private float Largura;
    private float Profundidade;
    private float Diametro;
    private int IsActive;
    private int id_artista;
    private int id_Tecnica;
    private int id_Estilo;
    private int IsArtsy;
    private int id_Material;

    /**
     * Constructs a new instance of {@code Obra_Arte} with specified parameters.
     *
     * @param id_Obra_Arte   The unique identifier for the artwork.
     * @param titulo         The title of the artwork.
     * @param link_Imagem    The link to the artwork image.
     * @param ano_Criacao    The creation year of the artwork.
     * @param preco          The price of the artwork.
     * @param altura         The height of the artwork.
     * @param largura        The width of the artwork.
     * @param profundidade   The depth of the artwork.
     * @param diametro       The diameter of the artwork.
     * @param isActive       The status of the artwork.
     * @param id_artista     The unique identifier for the artist.
     * @param id_Tecnica     The unique identifier for the technique used.
     * @param id_Estilo      The unique identifier for the artistic style.
     * @param isArtsy        Indicates if the artwork is listed on Artsy.
     * @param id_Material    The unique identifier for the material used.
     */
    public Obra_Arte(int id_Obra_Arte, String titulo, String link_Imagem, LocalDate ano_Criacao,
                     float preco, float altura, float largura, float profundidade, float diametro, int isActive,
                     int id_artista, int id_Tecnica, int id_Estilo, int isArtsy, int id_Material) {
        this.id_Obra_Arte = id_Obra_Arte;
        Titulo = titulo;
        Link_Imagem = link_Imagem;
        Ano_Criacao = ano_Criacao;
        Preco = preco;
        this.altura=altura;
        Largura = largura;
        Profundidade = profundidade;
        Diametro = diametro;
        this.IsActive = isActive;
        this.id_artista = id_artista;
        this.id_Tecnica = id_Tecnica;
        this.id_Estilo = id_Estilo;
        this.IsArtsy = isArtsy;
        this.id_Material = id_Material;
    }
    /**
     * Constructs a new instance of {@code Obra_Arte} with default parameters.
     */
    public Obra_Arte() {
    }

    /**
     * Gets the unique identifier for the artwork.
     *
     * @return The unique identifier for the artwork.
     */
    public int getId_Obra_Arte() {
        return id_Obra_Arte;
    }

    /**
     * Sets the unique identifier for the artwork.
     *
     * @param id_Obra_Arte The unique identifier for the artwork.
     */
    public void setId_Obra_Arte(int id_Obra_Arte) {
        this.id_Obra_Arte = id_Obra_Arte;
    }

    /**
     * Gets the title of the artwork.
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
     * Gets the link to the artwork image.
     *
     * @return The link to the artwork image.
     */
    public String getLink_Imagem() {
        return Link_Imagem;
    }

    /**
     * Sets the link to the artwork image.
     *
     * @param link_Imagem The link to the artwork image.
     */
    public void setLink_Imagem(String link_Imagem) {
        Link_Imagem = link_Imagem;
    }

    /**
     * Gets the creation year of the artwork.
     *
     * @return The creation year of the artwork.
     */
    public LocalDate getAno_Criacao() {
        return Ano_Criacao;
    }

    /**
     * Sets the creation year of the artwork.
     *
     * @param ano_Criacao The creation year of the artwork.
     */
    public void setAno_Criacao(LocalDate ano_Criacao) {
        Ano_Criacao = ano_Criacao;
    }
    /**
     * Gets the price of the artwork.
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
     * Gets the width of the artwork.
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
     * Gets the depth of the artwork.
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
     * Gets the diameter of the artwork.
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
     * Gets the status of the artwork (active or inactive).
     *
     * @return The status of the artwork.
     */
    public int getIsActive() {
        return IsActive;
    }

    /**
     * Sets the status of the artwork (active or inactive).
     *
     * @param isActive The status of the artwork.
     */
    public void setIsActive(int isActive) {
        IsActive = isActive;
    }

    /**
     * Gets the unique identifier of the artist associated with the artwork.
     *
     * @return The unique identifier of the artist.
     */
    public int getId_artista() {
        return id_artista;
    }

    /**
     * Sets the unique identifier of the artist associated with the artwork.
     *
     * @param id_artista The unique identifier of the artist.
     */
    public void setId_artista(int id_artista) {
        this.id_artista = id_artista;
    }

    /**
     * Gets the unique identifier of the technique associated with the artwork.
     *
     * @return The unique identifier of the technique.
     */
    public int getId_Tecnica() {
        return id_Tecnica;
    }

    /**
     * Sets the unique identifier of the technique associated with the artwork.
     *
     * @param id_Tecnica The unique identifier of the technique.
     */
    public void setId_Tecnica(int id_Tecnica) {
        this.id_Tecnica = id_Tecnica;
    }

    /**
     * Gets the unique identifier of the style associated with the artwork.
     *
     * @return The unique identifier of the style.
     */
    public int getId_Estilo() {
        return id_Estilo;
    }

    /**
     * Sets the unique identifier of the style associated with the artwork.
     *
     * @param id_Estilo The unique identifier of the style.
     */
    public void setId_Estilo(int id_Estilo) {
        this.id_Estilo = id_Estilo;
    }

    /**
     * Gets the flag indicating whether the artwork is associated with Artsy.
     *
     * @return 1 if associated with Artsy, 0 otherwise.
     */
    public int getIsArtsy() {
        return IsArtsy;
    }

    /**
     * Sets the flag indicating whether the artwork is associated with Artsy.
     *
     * @param isArtsy 1 if associated with Artsy, 0 otherwise.
     */
    public void setIsArtsy(int isArtsy) {
        IsArtsy = isArtsy;
    }

    /**
     * Gets the unique identifier of the material associated with the artwork.
     *
     * @return The unique identifier of the material.
     */
    public int getId_Material() {
        return id_Material;
    }

    /**
     * Sets the unique identifier of the material associated with the artwork.
     *
     * @param id_Material The unique identifier of the material.
     */
    public void setId_Material(int id_Material) {
        this.id_Material = id_Material;
    }

    /**
     * Gets the height of the artwork.
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
}
