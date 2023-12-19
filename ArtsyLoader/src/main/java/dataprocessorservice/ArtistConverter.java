package dataprocessorservice;

import artsymodel.ArtsyArtist;
import model.Artista;

/**
 * The {@code ArtistConverter} class is responsible for converting an {@link ArtsyArtist} object
 * to an {@link Artista} object.
 */
public class ArtistConverter {

    /**
     * The constant value indicating that the artist is associated with Artsy.
     */
    private static final int IS_ARTSY = 1;

    /**
     * Converts an {@link ArtsyArtist} object to an {@link Artista} object.
     *
     * @param artsyArtist The Artsy artist to be converted.
     * @return An {@link Artista} object representing the converted artist.
     */
    public static Artista process(ArtsyArtist artsyArtist) {
        Artista artista = new Artista();

        // Populate the Artista fields
        artista.setNome_artista(artsyArtist.getName());
        artista.setData_Nascimento(artsyArtist.getBirthday());
        artista.setBiografia(artsyArtist.getBiography());
        artista.setData_Morte(artsyArtist.getDeathday());
        artista.setNacionalidade(artsyArtist.getNationality());
        artista.setIsArtsy(IS_ARTSY);

        return artista;
    }
}
