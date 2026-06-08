package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.fike110.termii.model.enums.MessageChannel;
import io.github.fike110.termii.model.enums.SmsType;

/**
 * Request model for sending an SMS campaign.
 * <p>
 * Contains campaign configuration including the message, target phonebook, sender ID,
 * channel, and optional scheduling. The sender ID, phonebook ID, and campaign type are required.
 * </p>
 */
public class CampaignRequest {

    private final String countryCode;
    private final String senderId;
    private final String message;
    private final MessageChannel channel;
    private final SmsType messageType;
    private final String phonebookId;
    private final String delimiter;
    private final String removeDuplicate;
    private final String campaignType;
    private final String scheduleTime;

    private CampaignRequest(Builder builder) {
        this.countryCode = builder.countryCode;
        this.senderId = builder.senderId;
        this.message = builder.message;
        this.channel = builder.channel;
        this.messageType = builder.messageType;
        this.phonebookId = builder.phonebookId;
        this.delimiter = builder.delimiter;
        this.removeDuplicate = builder.removeDuplicate;
        this.campaignType = builder.campaignType;
        this.scheduleTime = builder.scheduleTime;
    }

    /**
     * Returns the destination country code.
     *
     * @return the country code
     */
    @JsonProperty("country_code")
    public String getCountryCode() { return countryCode; }

    /**
     * Returns the sender ID for the campaign.
     *
     * @return the sender ID
     */
    @JsonProperty("sender_id")
    public String getSenderId() { return senderId; }

    /**
     * Returns the campaign message content.
     *
     * @return the message text
     */
    @JsonProperty("message")
    public String getMessage() { return message; }

    /**
     * Returns the message channel (e.g. "generic", "dnd").
     *
     * @return the channel
     */
    @JsonProperty("channel")
    public MessageChannel getChannel() { return channel; }

    /**
     * Returns the message type (e.g. plain text, flash).
     *
     * @return the message type
     */
    @JsonProperty("message_type")
    public SmsType getMessageType() { return messageType; }

    /**
     * Returns the target phonebook ID.
     *
     * @return the phonebook ID
     */
    @JsonProperty("phonebook_id")
    public String getPhonebookId() { return phonebookId; }

    /**
     * Returns the CSV delimiter used for contact data.
     *
     * @return the delimiter
     */
    @JsonProperty("delimiter")
    public String getDelimiter() { return delimiter; }

    /**
     * Returns the duplicate removal preference.
     *
     * @return the remove duplicate flag
     */
    @JsonProperty("remove_duplicate")
    public String getRemoveDuplicate() { return removeDuplicate; }

    /**
     * Returns the campaign type (e.g. "personalised", "broadcast").
     *
     * @return the campaign type
     */
    @JsonProperty("campaign_type")
    public String getCampaignType() { return campaignType; }

    /**
     * Returns the scheduled send time for the campaign.
     *
     * @return the schedule time
     */
    @JsonProperty("schedule_sms_status")
    public String getScheduleTime() { return scheduleTime; }

    /**
     * Creates a new builder for constructing a {@link CampaignRequest}.
     *
     * @return a new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for {@link CampaignRequest}.
     */
    public static class Builder {
        private String countryCode;
        private String senderId;
        private String message;
        private MessageChannel channel;
        private SmsType messageType = SmsType.PLAIN;
        private String phonebookId;
        private String delimiter;
        private String removeDuplicate = "yes";
        private String campaignType;
        private String scheduleTime;

        /**
         * Sets the destination country code.
         *
         * @param val the country code
         * @return this builder
         */
        public Builder countryCode(String val) { this.countryCode = val; return this; }
        /**
         * Sets the sender ID for the campaign.
         *
         * @param val the sender ID
         * @return this builder
         */
        public Builder senderId(String val) { this.senderId = val; return this; }
        /**
         * Sets the campaign message content.
         *
         * @param val the message text
         * @return this builder
         */
        public Builder message(String val) { this.message = val; return this; }
        /**
         * Sets the message channel.
         *
         * @param val the channel
         * @return this builder
         */
        public Builder channel(MessageChannel val) { this.channel = val; return this; }
        /**
         * Sets the message type (defaults to {@link SmsType#PLAIN}).
         *
         * @param val the message type
         * @return this builder
         */
        public Builder messageType(SmsType val) { this.messageType = val; return this; }
        /**
         * Sets the target phonebook ID.
         *
         * @param val the phonebook ID
         * @return this builder
         */
        public Builder phonebookId(String val) { this.phonebookId = val; return this; }
        /**
         * Sets the CSV delimiter for contact data.
         *
         * @param val the delimiter
         * @return this builder
         */
        public Builder delimiter(String val) { this.delimiter = val; return this; }
        /**
         * Sets whether to remove duplicate recipients (defaults to "yes").
         *
         * @param val the remove duplicate flag
         * @return this builder
         */
        public Builder removeDuplicate(String val) { this.removeDuplicate = val; return this; }
        /**
         * Sets the campaign type.
         *
         * @param val the campaign type
         * @return this builder
         */
        public Builder campaignType(String val) { this.campaignType = val; return this; }
        /**
         * Sets the scheduled send time.
         *
         * @param val the schedule time
         * @return this builder
         */
        public Builder scheduleTime(String val) { this.scheduleTime = val; return this; }

        /**
         * Builds the {@link CampaignRequest}, validating required fields.
         *
         * @return the constructed request
         * @throws IllegalArgumentException if senderId, phonebookId, or campaignType is missing
         */
        public CampaignRequest build() {
            if (senderId == null || senderId.isBlank())
                throw new IllegalArgumentException("senderId is required");
            if (phonebookId == null || phonebookId.isBlank())
                throw new IllegalArgumentException("phonebookId is required");
            if (campaignType == null || campaignType.isBlank())
                throw new IllegalArgumentException("campaignType is required");
            return new CampaignRequest(this);
        }
    }
}
