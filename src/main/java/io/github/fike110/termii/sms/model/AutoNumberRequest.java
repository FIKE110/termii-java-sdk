package io.github.fike110.termii.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for sending an SMS via Termii's auto-generated numbering.
 * <p>
 * The sender number is automatically assigned by Termii. Only the recipient
 * number and message content are required. Use the {@link Builder} to construct.
 * </p>
 */
public class AutoNumberRequest {

    private final String to;
    private final String sms;

    private AutoNumberRequest(Builder builder) {
        this.to = builder.to;
        this.sms = builder.sms;
    }

    /**
     * Returns the recipient phone number.
     *
     * @return the destination phone number
     */
    @JsonProperty("to")
    public String getTo() { return to; }

    /**
     * Returns the SMS message content.
     *
     * @return the message text
     */
    @JsonProperty("sms")
    public String getSms() { return sms; }

    /**
     * Creates a new {@link Builder} for constructing an {@link AutoNumberRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String to;
        private String sms;

        /**
         * Sets the recipient phone number.
         *
         * @param to the destination phone number
         * @return this builder
         */
        public Builder to(String to) {
            this.to = to;
            return this;
        }

        /**
         * Sets the SMS message content.
         *
         * @param sms the message text
         * @return this builder
         */
        public Builder sms(String sms) {
            this.sms = sms;
            return this;
        }

        /**
         * Builds the {@link AutoNumberRequest} after validating required fields.
         *
         * @return a new AutoNumberRequest instance
         * @throws IllegalArgumentException if {@code to} or {@code sms} is blank
         */
        public AutoNumberRequest build() {
            if (to == null || to.isBlank()) {
                throw new IllegalArgumentException("to is required");
            }
            if (sms == null || sms.isBlank()) {
                throw new IllegalArgumentException("sms is required");
            }
            return new AutoNumberRequest(this);
        }
    }
}
