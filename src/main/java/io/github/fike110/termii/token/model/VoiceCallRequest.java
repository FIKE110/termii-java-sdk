package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request model for initiating a voice call with an OTP code.
 * <p>
 * The API will call the specified phone number and deliver the
 * provided code via text-to-speech. Use the {@link Builder} to construct.
 * </p>
 */
public class VoiceCallRequest {

    private final String phoneNumber;
    private final String code;

    private VoiceCallRequest(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.code = builder.code;
    }

    /**
     * Returns the phone number to call.
     *
     * @return the recipient phone number
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Returns the code to be spoken during the call.
     *
     * @return the OTP code
     */
    @JsonProperty("code")
    public String getCode() { return code; }

    /**
     * Creates a new {@link Builder} for constructing a {@link VoiceCallRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String phoneNumber;
        private String code;

        /**
         * Sets the phone number to call.
         *
         * @param phoneNumber the recipient phone number
         * @return this builder
         */
        public Builder phoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; return this; }

        /**
         * Sets the code to be spoken during the call.
         *
         * @param code the OTP code
         * @return this builder
         */
        public Builder code(String code) { this.code = code; return this; }

        /**
         * Builds the {@link VoiceCallRequest} after validating required fields.
         *
         * @return a new VoiceCallRequest instance
         * @throws IllegalArgumentException if {@code phoneNumber} or {@code code} is blank
         */
        public VoiceCallRequest build() {
            if (phoneNumber == null || phoneNumber.isBlank()) throw new IllegalArgumentException("phoneNumber is required");
            if (code == null || code.isBlank()) throw new IllegalArgumentException("code is required");
            return new VoiceCallRequest(this);
        }
    }
}
