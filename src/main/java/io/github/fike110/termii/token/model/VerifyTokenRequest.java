package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for verifying an OTP token.
 * <p>
 * Requires the PIN ID (returned from a token send request) and the PIN code
 * entered by the user. Use the {@link Builder} to construct.
 * </p>
 */
public class VerifyTokenRequest {

    private final String pinId;
    private final String pin;

    private VerifyTokenRequest(Builder builder) {
        this.pinId = builder.pinId;
        this.pin = builder.pin;
    }

    /**
     * Returns the PIN ID associated with the token to verify.
     *
     * @return the PIN identifier
     */
    @JsonProperty("pin_id")
    public String getPinId() { return pinId; }

    /**
     * Returns the PIN code entered by the user.
     *
     * @return the PIN code
     */
    @JsonProperty("pin")
    public String getPin() { return pin; }

    /**
     * Creates a new {@link Builder} for constructing a {@link VerifyTokenRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String pinId;
        private String pin;

        /**
         * Sets the PIN ID associated with the token to verify.
         *
         * @param pinId the PIN identifier
         * @return this builder
         */
        public Builder pinId(String pinId) { this.pinId = pinId; return this; }

        /**
         * Sets the PIN code entered by the user.
         *
         * @param pin the PIN code
         * @return this builder
         */
        public Builder pin(String pin) { this.pin = pin; return this; }

        /**
         * Builds the {@link VerifyTokenRequest} after validating required fields.
         *
         * @return a new VerifyTokenRequest instance
         * @throws IllegalArgumentException if {@code pinId} or {@code pin} is blank
         */
        public VerifyTokenRequest build() {
            if (pinId == null || pinId.isBlank()) throw new IllegalArgumentException("pinId is required");
            if (pin == null || pin.isBlank()) throw new IllegalArgumentException("pin is required");
            return new VerifyTokenRequest(this);
        }
    }
}
