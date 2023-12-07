package artsymodel;

import java.time.LocalDate;

public class ArtsyShow {
    private String name;
    private String description;
    private LocalDate start_at;
    private LocalDate end_at;


    private Links _links;

    // Add getters for the fields you need to access


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public LocalDate getStart_at() {
        return start_at;
    }

    public LocalDate getEnd_at() {
        return end_at;
    }

    public String getThumbnailHref() {
        return _links != null && _links.thumbnail != null ? _links.thumbnail.href : null;
    }

    public String getImageHref(String imageVersion) {
        if (_links != null && _links.image != null && imageVersion != null) {
            return _links.image.href.replace("{image_version}", imageVersion);
        }
        return null;
    }

    public String getSelfHref() {
        return _links != null && _links.self != null ? _links.self.href : null;
    }

    public String getPermalinkHref() {
        return _links != null && _links.permalink != null ? _links.permalink.href : null;
    }

    public String getPartnerHref() {
        return _links != null && _links.partner != null ? _links.partner.href : null;
    }

    public String getArtworksHref() {
        return _links != null && _links.artworks != null ? _links.artworks.href : null;
    }

    public String getImagesHref() {
        return _links != null && _links.images != null ? _links.images.href : null;
    }

    // Define the Links class
    private static class Links {
        private Thumbnail thumbnail;
        private Image image;
        private Self self;
        private Permalink permalink;
        private Partner partner;
        private Artworks artworks;
        private Images images;

        // Add other link fields as needed

        private static class Thumbnail {
            private String href;
        }

        private static class Image {
            private String href;
            private boolean templated;
        }

        private static class Self {
            private String href;
        }

        private static class Permalink {
            private String href;
        }

        private static class Partner {
            private String href;
        }

        private static class Artworks {
            private String href;
        }

        private static class Images {
            private String href;
        }
    }


}
