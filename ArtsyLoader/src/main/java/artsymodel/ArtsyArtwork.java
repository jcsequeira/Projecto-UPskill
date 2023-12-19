package artsymodel;

import java.time.LocalDate;

/**
 * Represents an artwork in the Artsy platform.
 */
public class ArtsyArtwork {

    private String title;
    private String category;
    private String medium;
    private LocalDate date;
    private Dimensions dimensions;

    private Links _links;

    /**
     * Gets the title of the artwork.
     *
     * @return the title of the artwork.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the category of the artwork.
     *
     * @return the category of the artwork.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the medium of the artwork.
     *
     * @return the medium of the artwork.
     */
    public String getMedium() {
        return medium;
    }

    /**
     * Gets the creation date of the artwork.
     *
     * @return the creation date of the artwork.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the height of the artwork.
     *
     * @return the height of the artwork.
     */
    public Float getHeight() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.height != null ? dimensions.cm.height : 0.0f;
    }

    /**
     * Gets the width of the artwork.
     *
     * @return the width of the artwork.
     */
    public Float getWidth() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.width != null ? dimensions.cm.width : 0.0f;
    }

    /**
     * Gets the depth of the artwork.
     *
     * @return the depth of the artwork.
     */
    public Float getDepth() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.depth != null ? dimensions.cm.depth : 0.0f;
    }

    /**
     * Gets the diameter of the artwork.
     *
     * @return the diameter of the artwork.
     */
    public Float getDiameter() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.diameter != null ? dimensions.cm.diameter : 0.0f;
    }

    /**
     * Gets the thumbnail href of the artwork.
     *
     * @return the thumbnail href of the artwork.
     */
    public String getThumbnailHref() {
        return _links != null && _links.thumbnail != null ? _links.thumbnail.href : null;
    }

    /**
     * Gets the genes href of the artwork.
     *
     * @return the genes href of the artwork.
     */
    public String getGenesHref() {
        return _links != null && _links.genes != null ? _links.genes.href : null;
    }

    /**
     * Gets the artists href of the artwork.
     *
     * @return the artists href of the artwork.
     */
    public String getArtistsHref() {
        return _links != null && _links.artists != null ? _links.artists.href : null;
    }

    // Define the Dimensions class
    private static class Dimensions {
        private Cm cm;

        private static class Cm {

            private Float height;
            private Float width;
            private Float depth;
            private Float diameter;
        }
    }

    // Define the Links class
    private static class Links {
        private Thumbnail thumbnail;
        private Href genes;
        private Href artists;
        // Add other link fields as needed

        private static class Thumbnail {
            private String href;
        }

        private static class Href {
            private String href;
        }
    }

    @Override
    public String toString() {
        return title;
    }
}
