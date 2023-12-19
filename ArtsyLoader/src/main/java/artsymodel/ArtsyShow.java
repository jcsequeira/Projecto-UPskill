package artsymodel;

import java.time.LocalDate;

/**
 * Represents a show in the Artsy platform.
 */
public class ArtsyShow {

    private String name;
    private String description;
    private LocalDate start_at;
    private LocalDate end_at;

    private Links _links;

    /**
     * Gets the name of the show.
     *
     * @return the name of the show.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the show.
     *
     * @return the description of the show.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the start date of the show.
     *
     * @return the start date of the show.
     */
    public LocalDate getStart_at() {
        return start_at;
    }

    /**
     * Gets the end date of the show.
     *
     * @return the end date of the show.
     */
    public LocalDate getEnd_at() {
        return end_at;
    }

    /**
     * Gets the partner href of the show.
     *
     * @return the partner href of the show.
     */
    public String getPartnerHref() {
        return _links != null && _links.partner != null ? _links.partner.href : null;
    }

    // Define the Links class
    private static class Links {
        private Partner partner;

        // Add other link fields as needed

        private static class Partner {
            private String href;
        }

    }

    @Override
    public String toString() {
        return name;
    }
}
