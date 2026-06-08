package io.github.fike110.termii.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.fike110.termii.model.Media;
import io.github.fike110.termii.model.enums.MessageChannel;
import io.github.fike110.termii.model.enums.SmsType;

/**
 * Request model for sending a single SMS message via the Termii API.
 * <p>
 * Requires the recipient number ({@code to}), sender ID ({@code from}),
 * and message content ({@code sms}). Use the {@link Builder} to construct.
 * </p>
 */
public class SmsRequest {

    private final String to;
    private final String from;
    private final String sms;
    private final SmsType type;
    private final MessageChannel channel;
    private final Media media;

    private SmsRequest(Builder builder) {
        this.to = builder.to;
        this.from = builder.from;
        this.sms = builder.sms;
        this.type = builder.type;
        this.channel = builder.channel;
        this.media = builder.media;
    }

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
     * Returns the SMS message content.
     *
     * @return the message text
     */
    @JsonProperty("sms")
    public String getSms() { return sms; }

    /**
     * Returns the SMS type (e.g. PLAIN, ALPHANUMERIC).
     *
     * @return the message type
     */
    @JsonProperty("type")
    public SmsType getType() { return type; }

    /**
     * Returns the message channel (e.g. GENERIC, DND).
     *
     * @return the message channel
     */
    @JsonProperty("channel")
    public MessageChannel getChannel() { return channel; }

    /**
     * Returns the optional media attachment.
     *
     * @return the media attachment, or {@code null} if not set
     */
    @JsonProperty("media")
    public Media getMedia() { return media; }

    /**
     * Creates a new {@link Builder} for constructing an {@link SmsRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String to;
        private String from;
        private String sms;
        private SmsType type = SmsType.PLAIN;
        private MessageChannel channel = MessageChannel.GENERIC;
        private Media media;

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
         * Sets the sender ID or phone number.
         *
         * @param from the sender identifier
         * @return this builder
         */
        public Builder from(String from) {
            this.from = from;
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
         * Sets the SMS type (e.g. PLAIN, ALPHANUMERIC).
         *
         * @param type the message type
         * @return this builder
         */
        public Builder type(SmsType type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the message channel (e.g. GENERIC, DND).
         *
         * @param channel the message channel
         * @return this builder
         */
        public Builder channel(MessageChannel channel) {
            this.channel = channel;
            return this;
        }

        /**
         * Sets the optional media attachment.
         *
         * @param media the media attachment
         * @return this builder
         */
        public Builder media(Media media) {
            this.media = media;
            return this;
        }

        /**
         * Builds the {@link SmsRequest} after validating required fields.
         *
         * @return a new SmsRequest instance
         * @throws IllegalArgumentException if {@code to}, {@code from}, or {@code sms} is blank
         */
        public SmsRequest build() {
            if (to == null || to.isBlank()) {
                throw new IllegalArgumentException("to is required");
            }
            if (from == null || from.isBlank()) {
                throw new IllegalArgumentException("from is required");
            }
            if (sms == null || sms.isBlank()) {
                throw new IllegalArgumentException("sms is required");
            }
            return new SmsRequest(this);
        }
    }
}
