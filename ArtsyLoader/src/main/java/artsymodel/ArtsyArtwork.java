package artsymodel;

import java.time.LocalDate;

public class ArtsyArtwork {

    private String title;
    private String category;
    private String medium;
    private LocalDate date;
    private Dimensions dimensions;

    private Links _links;


    // Add getters for the fields you need to access

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getMedium() {
        return medium;
    }

    public LocalDate getDate() {
        return date;
    }

    public Float getHeight() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.height !=null ? dimensions.cm.height : 0.0f;
    }

    public Float getWidth() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.width !=null ? dimensions.cm.width : 0.0f;
    }

    public Float getDepth() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.depth !=null ? dimensions.cm.depth : 0.0f;
    }

    public Float getDiameter() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.diameter !=null  ? dimensions.cm.diameter : 0.0f;
    }

    public String getThumbnailHref() {
        return _links != null && _links.thumbnail != null ? _links.thumbnail.href : null;
    }

    public String getGenesHref() {
        return _links != null && _links.genes != null ? _links.genes.href : null;
    }

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


