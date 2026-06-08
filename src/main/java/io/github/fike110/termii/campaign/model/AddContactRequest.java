package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for adding a single contact to a phonebook.
 * <p>
 * The phone number is required; all other fields are optional.
 * </p>
 */
public class AddContactRequest {

    private final String phoneNumber;
    private final String emailAddress;
    private final String firstName;
    private final String lastName;
    private final String company;
    private final String countryCode;

    private AddContactRequest(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.emailAddress = builder.emailAddress;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.company = builder.company;
        this.countryCode = builder.countryCode;
    }

    /**
     * Returns the phone number of the contact.
     *
     * @return the phone number
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Returns the email address of the contact.
     *
     * @return the email address
     */
    @JsonProperty("email_address")
    public String getEmailAddress() { return emailAddress; }

    /**
     * Returns the first name of the contact.
     *
     * @return the first name
     */
    @JsonProperty("first_name")
    public String getFirstName() { return firstName; }

    /**
     * Returns the last name of the contact.
     *
     * @return the last name
     */
    @JsonProperty("last_name")
    public String getLastName() { return lastName; }

    /**
     * Returns the company name of the contact.
     *
     * @return the company name
     */
    @JsonProperty("company")
    public String getCompany() { return company; }

    /**
     * Returns the country code of the contact.
     *
     * @return the country code
     */
    @JsonProperty("country_code")
    public String getCountryCode() { return countryCode; }

    /**
     * Creates a new builder for constructing an {@link AddContactRequest}.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link AddContactRequest}.
     */
    public static class Builder {
        private String phoneNumber;
        private String emailAddress;
        private String firstName;
        private String lastName;
        private String company;
        private String countryCode;

        /**
         * Sets the phone number (required).
         *
         * @param val the phone number
         * @return this builder
         */
        public Builder phoneNumber(String val) { this.phoneNumber = val; return this; }
        /**
         * Sets the email address.
         *
         * @param val the email address
         * @return this builder
         */
        public Builder emailAddress(String val) { this.emailAddress = val; return this; }
        /**
         * Sets the first name.
         *
         * @param val the first name
         * @return this builder
         */
        public Builder firstName(String val) { this.firstName = val; return this; }
        /**
         * Sets the last name.
         *
         * @param val the last name
         * @return this builder
         */
        public Builder lastName(String val) { this.lastName = val; return this; }
        /**
         * Sets the company name.
         *
         * @param val the company name
         * @return this builder
         */
        public Builder company(String val) { this.company = val; return this; }
        /**
         * Sets the country code.
         *
         * @param val the country code
         * @return this builder
         */
        public Builder countryCode(String val) { this.countryCode = val; return this; }

        /**
         * Builds the {@link AddContactRequest}, validating required fields.
         *
         * @return the constructed request
         * @throws IllegalArgumentException if phoneNumber is blank
         */
        public AddContactRequest build() {
            if (phoneNumber == null || phoneNumber.isBlank())
                throw new IllegalArgumentException("phoneNumber is required");
            return new AddContactRequest(this);
        }
    }
}
