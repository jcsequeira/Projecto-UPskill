package artsymodel;

import com.google.gson.annotations.SerializedName;

public class ArtsyArtwork {

    private String title;
    private String category;
    private String medium;
    private String date;
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

    public String getDate() {
        return date;
    }

    public double getHeight() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.height !=null ? dimensions.cm.height : 0.0;
    }

    public double getWidth() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.width !=null ? dimensions.cm.width : 0.0;
    }

    public Double getDepth() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.depth !=null ? dimensions.cm.depth : null;
    }

    public Double getDiameter() {
        return dimensions != null && dimensions.cm != null && dimensions.cm.diameter !=null  ? dimensions.cm.diameter : null;
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

            private Double height;
            private Double width;
            private Double depth;
            private Double diameter;
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

}


