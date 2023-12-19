package artsymodel;

import java.time.LocalDate;

/**
 * Represents an artist in the Artsy system.
 */
public class ArtsyArtist {

    private String name;
    private String biography;
    private LocalDate birthday;
    private LocalDate deathday;
    private String nationality;

    /**
     * Gets the name of the artist.
     *
     * @return The name of the artist.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the biography of the artist.
     *
     * @return The biography of the artist.
     */
    public String getBiography() {
        return biography;
    }

    /**
     * Gets the birthday of the artist.
     *
     * @return The birthday of the artist.
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Gets the death day of the artist.
     *
     * @return The death day of the artist.
     */
    public LocalDate getDeathday() {
        return deathday;
    }

    /**
     * Gets the nationality of the artist.
     *
     * @return The nationality of the artist.
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Returns a string representation of the artist.
     * Must not be deleted, used in hashmaps and matching algoritms
     * to find the specific artwork.
     * @return A string representation of the artist.
     */
    @Override
    public String toString() {
        return name;
    }
}
