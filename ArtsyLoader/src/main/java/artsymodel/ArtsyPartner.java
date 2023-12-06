package artsymodel;

public class ArtsyPartner {
    private String name;
    private String email;
    private String region;

    private Links _links;


    // Add getters for the fields you need to access
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRegion() {
        return region;
    }

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
}