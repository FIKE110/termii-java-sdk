package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for creating a new phonebook.
 * <p>
 * The phonebook name is required; description is optional.
 * </p>
 */
public class CreatePhonebookRequest {

    private final String phonebookName;
    private final String description;

    private CreatePhonebookRequest(Builder builder) {
        this.phonebookName = builder.phonebookName;
        this.description = builder.description;
    }

    /**
     * Returns the name for the new phonebook.
     *
     * @return the phonebook name
     */
    @JsonProperty("phonebook_name")
    public String getPhonebookName() { return phonebookName; }

    /**
     * Returns the description for the new phonebook.
     *
     * @return the description
     */
    @JsonProperty("description")
    public String getDescription() { return description; }

    /**
     * Creates a new builder for constructing a {@link CreatePhonebookRequest}.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link CreatePhonebookRequest}.
     */
    public static class Builder {
        private String phonebookName;
        private String description;

        /**
         * Sets the phonebook name (required).
         *
         * @param val the phonebook name
         * @return this builder
         */
        public Builder phonebookName(String val) { this.phonebookName = val; return this; }
        /**
         * Sets the phonebook description (optional).
         *
         * @param val the description
         * @return this builder
         */
        public Builder description(String val) { this.description = val; return this; }

        /**
         * Builds the {@link CreatePhonebookRequest}, validating required fields.
         *
         * @return the constructed request
         * @throws IllegalArgumentException if phonebookName is blank
         */
        public CreatePhonebookRequest build() {
            if (phonebookName == null || phonebookName.isBlank())
                throw new IllegalArgumentException("phonebookName is required");
            return new CreatePhonebookRequest(this);
        }
    }
}
