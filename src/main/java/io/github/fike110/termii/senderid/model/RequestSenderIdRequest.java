package io.github.fike110.termii.senderid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for requesting a new alphanumeric sender ID.
 * <p>
 * The sender ID, use case, and company name are all required fields.
 * </p>
 */
public class RequestSenderIdRequest {

    private final String senderId;
    private final String useCase;
    private final String company;

    private RequestSenderIdRequest(Builder builder) {
        this.senderId = builder.senderId;
        this.useCase = builder.useCase;
        this.company = builder.company;
    }

    /**
     * Returns the requested alphanumeric sender ID.
     *
     * @return the sender ID string
     */
    @JsonProperty("sender_id")
    public String getSenderId() { return senderId; }

    /**
     * Returns the use case description for the sender ID.
     *
     * @return the use case
     */
    @JsonProperty("use_case")
    public String getUseCase() { return useCase; }

    /**
     * Returns the company name associated with the request.
     *
     * @return the company name
     */
    @JsonProperty("company")
    public String getCompany() { return company; }

    /**
     * Creates a new builder for constructing a {@link RequestSenderIdRequest}.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link RequestSenderIdRequest}.
     */
    public static class Builder {
        private String senderId;
        private String useCase;
        private String company;

        /**
         * Sets the alphanumeric sender ID to request.
         *
         * @param senderId the sender ID
         * @return this builder
         */
        public Builder senderId(String senderId) { this.senderId = senderId; return this; }
        /**
         * Sets the use case for this sender ID.
         *
         * @param useCase the use case description
         * @return this builder
         */
        public Builder useCase(String useCase) { this.useCase = useCase; return this; }
        /**
         * Sets the company name for this sender ID request.
         *
         * @param company the company name
         * @return this builder
         */
        public Builder company(String company) { this.company = company; return this; }

        /**
         * Builds the {@link RequestSenderIdRequest}, validating that all required fields are set.
         *
         * @return the constructed request
         * @throws IllegalArgumentException if any required field is blank
         */
        public RequestSenderIdRequest build() {
            if (senderId == null || senderId.isBlank()) throw new IllegalArgumentException("senderId is required");
            if (useCase == null || useCase.isBlank()) throw new IllegalArgumentException("useCase is required");
            if (company == null || company.isBlank()) throw new IllegalArgumentException("company is required");
            return new RequestSenderIdRequest(this);
        }
    }
}
