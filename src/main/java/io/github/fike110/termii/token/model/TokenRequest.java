package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.fike110.termii.model.enums.MessageChannel;
import io.github.fike110.termii.model.enums.PinType;

/**
 * Request model for sending an OTP token via the Termii API.
 * <p>
 * Supports configurable PIN type, length, attempts, and TTL.
 * Use the {@link Builder} to construct.
 * </p>
 */
public class TokenRequest {

    private final PinType messageType;
    private final String to;
    private final String from;
    private final MessageChannel channel;
    private final int pinAttempts;
    private final int pinTimeToLive;
    private final int pinLength;
    private final String pinPlaceholder;
    private final String messageText;
    private final PinType pinType;

    private TokenRequest(Builder builder) {
        this.messageType = builder.messageType;
        this.to = builder.to;
        this.from = builder.from;
        this.channel = builder.channel;
        this.pinAttempts = builder.pinAttempts;
        this.pinTimeToLive = builder.pinTimeToLive;
        this.pinLength = builder.pinLength;
        this.pinPlaceholder = builder.pinPlaceholder;
        this.messageText = builder.messageText;
        this.pinType = builder.pinType;
    }

    /**
     * Returns the message type (e.g. NUMERIC, ALPHANUMERIC).
     *
     * @return the type of PIN used in the message
     */
    @JsonProperty("message_type")
    public PinType getMessageType() { return messageType; }

    /**
     * Returns the recipient phone number.
     *
     * @return the destination phone number
     */
    @JsonProperty("to")
    public String getTo() { return to; }

    /**
     * Returns the sender ID or phone number.
     *
     * @return the sender identifier
     */
    @JsonProperty("from")
    public String getFrom() { return from; }

    /**
     * Returns the message channel (e.g. DND, GENERIC).
     *
     * @return the message channel
     */
    @JsonProperty("channel")
    public MessageChannel getChannel() { return channel; }

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
     * Returns the placeholder text for the PIN in the message.
     *
     * @return the PIN placeholder string
     */
    @JsonProperty("pin_placeholder")
    public String getPinPlaceholder() { return pinPlaceholder; }

    /**
     * Returns the custom message text with the PIN placeholder.
     *
     * @return the message text
     */
    @JsonProperty("message_text")
    public String getMessageText() { return messageText; }

    /**
     * Returns the type of PIN (e.g. NUMERIC, ALPHANUMERIC).
     *
     * @return the PIN type
     */
    @JsonProperty("pin_type")
    public PinType getPinType() { return pinType; }

    /**
     * Creates a new {@link Builder} for constructing a {@link TokenRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PinType messageType = PinType.NUMERIC;
        private String to;
        private String from;
        private MessageChannel channel = MessageChannel.DND;
        private int pinAttempts = 3;
        private int pinTimeToLive = 5;
        private int pinLength = 6;
        private String pinPlaceholder = "< 123456 >";
        private String messageText;
        private PinType pinType = PinType.NUMERIC;

        /**
         * Sets the message type (e.g. NUMERIC, ALPHANUMERIC).
         *
         * @param messageType the type of PIN used in the message
         * @return this builder
         */
        public Builder messageType(PinType messageType) { this.messageType = messageType; return this; }

        /**
         * Sets the recipient phone number.
         *
         * @param to the destination phone number
         * @return this builder
         */
        public Builder to(String to) { this.to = to; return this; }

        /**
         * Sets the sender ID or phone number.
         *
         * @param from the sender identifier
         * @return this builder
         */
        public Builder from(String from) { this.from = from; return this; }

        /**
         * Sets the message channel (e.g. DND, GENERIC).
         *
         * @param channel the message channel
         * @return this builder
         */
        public Builder channel(MessageChannel channel) { this.channel = channel; return this; }

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
         * Sets the placeholder text for the PIN in the message.
         *
         * @param pinPlaceholder the PIN placeholder string
         * @return this builder
         */
        public Builder pinPlaceholder(String pinPlaceholder) { this.pinPlaceholder = pinPlaceholder; return this; }

        /**
         * Sets the custom message text with the PIN placeholder.
         *
         * @param messageText the message text
         * @return this builder
         */
        public Builder messageText(String messageText) { this.messageText = messageText; return this; }

        /**
         * Sets the type of PIN (e.g. NUMERIC, ALPHANUMERIC).
         *
         * @param pinType the PIN type
         * @return this builder
         */
        public Builder pinType(PinType pinType) { this.pinType = pinType; return this; }

        /**
         * Builds the {@link TokenRequest} after validating required fields.
         *
         * @return a new TokenRequest instance
         * @throws IllegalArgumentException if {@code to}, {@code from}, or {@code messageText} is blank
         */
        public TokenRequest build() {
            if (to == null || to.isBlank()) throw new IllegalArgumentException("to is required");
            if (from == null || from.isBlank()) throw new IllegalArgumentException("from is required");
            if (messageText == null || messageText.isBlank()) throw new IllegalArgumentException("messageText is required");
            return new TokenRequest(this);
        }
    }
}
