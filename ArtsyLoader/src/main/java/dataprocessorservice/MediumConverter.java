package dataprocessorservice;

import artsymodel.ArtsyArtwork;
import model.Materiais;

/**
 * The {@code MediumConverter} class is responsible for converting an {@link ArtsyArtwork} object
 * to a {@link Materiais} object representing the medium of the artwork.
 */
public class MediumConverter {

    /**
     * Converts an {@link ArtsyArtwork} object to a {@link Materiais} object representing the medium of the artwork.
     *
     * @param artsyArtwork The Artsy artwork for which the medium is to be converted.
     * @return A {@link Materiais} object representing the converted medium of the artwork.
     */
    public static Materiais process(ArtsyArtwork artsyArtwork) {
        Materiais material = new Materiais();
        material.setTipo_Material(artsyArtwork.getMedium());
        return material;
    }
}
