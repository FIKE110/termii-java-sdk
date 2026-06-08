package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for sending an OTP token via voice call.
 * <p>
 * The PIN is read aloud to the recipient during the voice call.
 * Use the {@link Builder} to construct.
 * </p>
 */
public class VoiceTokenRequest {

    private final String phoneNumber;
    private final int pinAttempts;
    private final int pinTimeToLive;
    private final int pinLength;

    private VoiceTokenRequest(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.pinAttempts = builder.pinAttempts;
        this.pinTimeToLive = builder.pinTimeToLive;
        this.pinLength = builder.pinLength;
    }

    /**
     * Returns the recipient phone number.
     *
     * @return the phone number to call
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Returns the maximum number of PIN attempts allowed.
     *
     * @return the PIN attempt limit
     */
    @JsonProperty("pin_attempts")
    public int getPinAttempts() { return pinAttempts; }

    /**
     * Returns the PIN time-to-live in minutes.
     *
     * @return the PIN validity duration in minutes
     */
    @JsonProperty("pin_time_to_live")
    public int getPinTimeToLive() { return pinTimeToLive; }

    /**
     * Returns the length of the PIN code.
     *
     * @return the number of digits in the PIN
     */
    @JsonProperty("pin_length")
    public int getPinLength() { return pinLength; }

    /**
     * Creates a new {@link Builder} for constructing a {@link VoiceTokenRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String phoneNumber;
        private int pinAttempts = 3;
        private int pinTimeToLive = 5;
        private int pinLength = 6;

        /**
         * Sets the recipient phone number.
         *
         * @param phoneNumber the phone number to call
         * @return this builder
         */
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }

        /**
         * Sets the maximum number of PIN attempts allowed.
         *
         * @param pinAttempts the PIN attempt limit
         * @return this builder
         */
        public Builder pinAttempts(int pinAttempts) { this.pinAttempts = pinAttempts; return this; }

        /**
         * Sets the PIN time-to-live in minutes.
         *
         * @param pinTimeToLive the PIN validity duration in minutes
         * @return this builder
         */
        public Builder pinTimeToLive(int pinTimeToLive) { this.pinTimeToLive = pinTimeToLive; return this; }

        /**
         * Sets the length of the PIN code.
         *
         * @param pinLength the number of digits in the PIN
         * @return this builder
         */
        public Builder pinLength(int pinLength) { this.pinLength = pinLength; return this; }

        /**
         * Builds the {@link VoiceTokenRequest} after validating required fields.
         *
         * @return a new VoiceTokenRequest instance
         * @throws IllegalArgumentException if {@code phoneNumber} is blank
         */
        public VoiceTokenRequest build() {
            if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("phoneNumber is required");
            return new VoiceTokenRequest(this);
        }
    }
}
