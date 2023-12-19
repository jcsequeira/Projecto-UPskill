package dataprocessorservice;

import artsymodel.ArtsyArtwork;
import model.Tecnica;

/**
 * The {@code CategoryConverter} class is responsible for converting an {@link ArtsyArtwork} object
 * to a {@link Tecnica} object representing the artwork category.
 */
public class CategoryConverter {

    /**
     * Converts an {@link ArtsyArtwork} object to a {@link Tecnica} object representing the artwork category.
     *
     * @param artsyArtwork The Artsy artwork to be converted.
     * @return A {@link Tecnica} object representing the converted category.
     */
    public static Tecnica process(ArtsyArtwork artsyArtwork) {
        Tecnica tecnica = new Tecnica();
        tecnica.setTipo_Tecnica(artsyArtwork.getCategory());

        return tecnica;
    }
}
