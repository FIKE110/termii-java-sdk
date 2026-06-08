package io.github.fike110.termii.email.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Request model for sending a templated email.
 * <p>
 * Contains the recipient email, subject, email configuration ID, template ID,
 * and template variables. All fields are required.
 * </p>
 */
public class EmailTemplatedRequest {

    private final String email;
    private final String subject;
    private final String emailConfigurationId;
    private final String templateId;
    private final Map<String, String> variables;

    private EmailTemplatedRequest(Builder builder) {
        this.email = builder.email;
        this.subject = builder.subject;
        this.emailConfigurationId = builder.emailConfigurationId;
        this.templateId = builder.templateId;
        this.variables = builder.variables;
    }

    /**
     * Returns the recipient email address.
     *
     * @return the email address
     */
    @JsonProperty("email")
    public String getEmail() { return email; }

    /**
     * Returns the subject line of the email.
     *
     * @return the subject
     */
    @JsonProperty("subject")
    public String getSubject() { return subject; }

    /**
     * Returns the email configuration ID for the sender.
     *
     * @return the email configuration ID
     */
    @JsonProperty("email_configuration_id")
    public String getEmailConfigurationId() { return emailConfigurationId; }

    /**
     * Returns the template ID to use for the email.
     *
     * @return the template ID
     */
    @JsonProperty("template_id")
    public String getTemplateId() { return templateId; }

    /**
     * Returns the template variable substitutions.
     *
     * @return a map of variable names to values
     */
    @JsonProperty("variables")
    public Map<String, String> getVariables() { return variables; }

    /**
     * Creates a new builder for constructing an {@link EmailTemplatedRequest}.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link EmailTemplatedRequest}.
     */
    public static class Builder {
        private String email;
        private String subject;
        private String emailConfigurationId;
        private String templateId;
        private Map<String, String> variables;

        /**
         * Sets the recipient email address.
         *
         * @param email the email address
         * @return this builder
         */
        public Builder email(String email) { this.email = email; return this; }
        /**
         * Sets the email subject line.
         *
         * @param subject the subject
         * @return this builder
         */
        public Builder subject(String subject) { this.subject = subject; return this; }
        /**
         * Sets the email configuration ID.
         *
         * @param emailConfigurationId the configuration ID
         * @return this builder
         */
        public Builder emailConfigurationId(String emailConfigurationId) { this.emailConfigurationId = emailConfigurationId; return this; }
        /**
         * Sets the template ID.
         *
         * @param templateId the template ID
         * @return this builder
         */
        public Builder templateId(String templateId) { this.templateId = templateId; return this; }
        /**
         * Sets the template variable substitutions.
         *
         * @param variables a map of variable names to values
         * @return this builder
         */
        public Builder variables(Map<String, String> variables) { this.variables = variables; return this; }

        /**
         * Builds the {@link EmailTemplatedRequest}, validating that all required fields are set.
         *
         * @return the constructed request
         * @throws IllegalArgumentException if any required field is missing
         */
        public EmailTemplatedRequest build() {
            if (email == null || email.isBlank()) throw new IllegalArgumentException("email is required");
            if (subject == null || subject.isBlank()) throw new IllegalArgumentException("subject is required");
            if (emailConfigurationId == null || emailConfigurationId.isBlank())
                throw new IllegalArgumentException("emailConfigurationId is required");
            if (templateId == null || templateId.isBlank())
                throw new IllegalArgumentException("templateId is required");
            if (variables == null || variables.isEmpty())
                throw new IllegalArgumentException("variables is required");
            return new EmailTemplatedRequest(this);
        }
    }
}
