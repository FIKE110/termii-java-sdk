package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for sending an OTP token via email.
 * <p>
 * Requires the recipient email address, the OTP code, and an email
 * configuration ID. Use the {@link Builder} to construct.
 * </p>
 */
public class EmailTokenRequest {

    private final String emailAddress;
    private final String code;
    private final String emailConfigurationId;

    private EmailTokenRequest(Builder builder) {
        this.emailAddress = builder.emailAddress;
        this.code = builder.code;
        this.emailConfigurationId = builder.emailConfigurationId;
    }

    /**
     * Returns the recipient email address.
     *
     * @return the email address
     */
    @JsonProperty("email_address")
    public String getEmailAddress() { return emailAddress; }

    /**
     * Returns the OTP code to send.
     *
     * @return the OTP code
     */
    @JsonProperty("code")
    public String getCode() { return code; }

    /**
     * Returns the email configuration ID to use for sending.
     *
     * @return the email configuration identifier
     */
    @JsonProperty("email_configuration_id")
    public String getEmailConfigurationId() { return emailConfigurationId; }

    /**
     * Creates a new {@link Builder} for constructing an {@link EmailTokenRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String emailAddress;
        private String code;
        private String emailConfigurationId;

        /**
         * Sets the recipient email address.
         *
         * @param emailAddress the email address
         * @return this builder
         */
        public Builder emailAddress(String emailAddress) { this.emailAddress = emailAddress; return this; }

        /**
         * Sets the OTP code to send.
         *
         * @param code the OTP code
         * @return this builder
         */
        public Builder code(String code) { this.code = code; return this; }

        /**
         * Sets the email configuration ID to use for sending.
         *
         * @param emailConfigurationId the email configuration identifier
         * @return this builder
         */
        public Builder emailConfigurationId(String emailConfigurationId) { this.emailConfigurationId = emailConfigurationId; return this; }

        /**
         * Builds the {@link EmailTokenRequest} after validating required fields.
         *
         * @return a new EmailTokenRequest instance
         * @throws IllegalArgumentException if any required field is blank
         */
        public EmailTokenRequest build() {
            if (emailAddress == null || emailAddress.isBlank()) throw new IllegalArgumentException("emailAddress is required");
            if (code == null || code.isBlank()) throw new IllegalArgumentException("code is required");
            if (emailConfigurationId == null || emailConfigurationId.isBlank()) throw new IllegalArgumentException("emailConfigurationId is required");
            return new EmailTokenRequest(this);
        }
    }
}
