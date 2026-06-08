package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.fike110.termii.model.enums.PinType;

/**
 * Request model for generating an in-app OTP token without sending it via SMS.
 * <p>
 * The generated OTP is returned directly for use within the application.
 * Use the {@link Builder} to construct.
 * </p>
 */
public class InAppTokenRequest {

    private final PinType pinType;
    private final String phoneNumber;
    private final int pinAttempts;
    private final int pinTimeToLive;
    private final int pinLength;

    private InAppTokenRequest(Builder builder) {
        this.pinType = builder.pinType;
        this.phoneNumber = builder.phoneNumber;
        this.pinAttempts = builder.pinAttempts;
        this.pinTimeToLive = builder.pinTimeToLive;
        this.pinLength = builder.pinLength;
    }

    /**
     * Returns the type of PIN (e.g. NUMERIC, ALPHANUMERIC).
     *
     * @return the PIN type
     */
    @JsonProperty("pin_type")
    public PinType getPinType() { return pinType; }

    /**
     * Returns the phone number for which the token is generated.
     *
     * @return the phone number
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
     * Creates a new {@link Builder} for constructing an {@link InAppTokenRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PinType pinType = PinType.NUMERIC;
        private String phoneNumber;
        private int pinAttempts = 3;
        private int pinTimeToLive = 5;
        private int pinLength = 6;

        /**
         * Sets the type of PIN (e.g. NUMERIC, ALPHANUMERIC).
         *
         * @param pinType the PIN type
         * @return this builder
         */
        public Builder pinType(PinType pinType) { this.pinType = pinType; return this; }

        /**
         * Sets the phone number for which the token is generated.
         *
         * @param phoneNumber the phone number
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
         * Builds the {@link InAppTokenRequest} after validating required fields.
         *
         * @return a new InAppTokenRequest instance
         * @throws IllegalArgumentException if {@code phoneNumber} is blank
         */
        public InAppTokenRequest build() {
            if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("phoneNumber is required");
            return new InAppTokenRequest(this);
        }
    }
}
