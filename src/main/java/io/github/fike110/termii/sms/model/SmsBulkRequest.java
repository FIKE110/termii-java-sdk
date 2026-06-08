package io.github.fike110.termii.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.fike110.termii.model.enums.MessageChannel;
import io.github.fike110.termii.model.enums.SmsType;

import java.util.List;

/**
 * Request model for sending bulk SMS messages to multiple recipients.
 * <p>
 * Contains a list of phone numbers, a common sender ID, and message content.
 * Use the {@link Builder} to construct.
 * </p>
 */
public class SmsBulkRequest {

    private final List<String> to;
    private final String from;
    private final String sms;
    private final SmsType type;
    private final MessageChannel channel;

    private SmsBulkRequest(Builder builder) {
        this.to = builder.to;
        this.from = builder.from;
        this.sms = builder.sms;
        this.type = builder.type;
        this.channel = builder.channel;
    }

    /**
     * Returns the list of recipient phone numbers.
     *
     * @return the list of destination phone numbers
     */
    @JsonProperty("to")
    public List<String> getTo() { return to; }

    /**
     * Returns the common sender ID or phone number.
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
     * Creates a new {@link Builder} for constructing an {@link SmsBulkRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<String> to;
        private String from;
        private String sms;
        private SmsType type = SmsType.PLAIN;
        private MessageChannel channel = MessageChannel.GENERIC;

        /**
         * Sets the list of recipient phone numbers.
         *
         * @param to the list of destination phone numbers
         * @return this builder
         */
        public Builder to(List<String> to) {
            this.to = to;
            return this;
        }

        /**
         * Sets the common sender ID or phone number.
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
         * Builds the {@link SmsBulkRequest} after validating required fields.
         *
         * @return a new SmsBulkRequest instance
         * @throws IllegalArgumentException if {@code to}, {@code from}, or {@code sms} is blank
         */
        public SmsBulkRequest build() {
            if (to == null || to.isEmpty()) {
                throw new IllegalArgumentException("to is required");
            }
            if (from == null || from.isBlank()) {
                throw new IllegalArgumentException("from is required");
            }
            if (sms == null || sms.isBlank()) {
                throw new IllegalArgumentException("sms is required");
            }
            return new SmsBulkRequest(this);
        }
    }
}
