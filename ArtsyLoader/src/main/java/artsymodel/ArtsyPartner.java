package artsymodel;

/**
 * Represents a partner in the Artsy platform.
 */
public class ArtsyPartner {

    private String name;
    private String email;
    private String region;

    private Links _links;

    /**
     * Gets the name of the partner.
     *
     * @return the name of the partner.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the email of the partner.
     *
     * @return the email of the partner.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the region of the partner.
     *
     * @return the region of the partner.
     */
    public String getRegion() {
        return region;
    }

    /**
     * Gets the website href of the partner.
     *
     * @return the website href of the partner.
     */
    public String getWebsiteHref() {
        return _links != null && _links.website != null ? _links.website.href : null;
    }

    // Define the Links class
    private static class Links {
        private Href website;

        // Add other link fields as needed

        private static class Self {
            private String href;
        }

        private static class Href {
            private String href;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
