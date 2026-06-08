package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single campaign history entry representing a message sent as part of a campaign.
 * <p>
 * Contains details about the sender, receiver, message content, amount, status, and timestamps.
 * </p>
 */
public class CampaignHistoryEntry {

    private String id;
    private String sender;
    private String channel;
    private String receiver;
    private String message;
    private String amount;
    private String smsType;
    private String messageId;
    private String status;
    private String createdAt;
    private String updatedAt;

    /**
     * Returns the unique ID of this history entry.
     *
     * @return the entry ID
     */
    @JsonProperty("id")
    public String getId() { return id; }

    @JsonProperty("id")
    public void setId(String val) { this.id = val; }

    /**
     * Returns the sender ID or number.
     *
     * @return the sender
     */
    @JsonProperty("sender")
    public String getSender() { return sender; }

    @JsonProperty("sender")
    public void setSender(String val) { this.sender = val; }

    /**
     * Returns the channel used for this message.
     *
     * @return the channel
     */
    @JsonProperty("channel")
    public String getChannel() { return channel; }

    @JsonProperty("channel")
    public void setChannel(String val) { this.channel = val; }

    /**
     * Returns the receiver phone number.
     *
     * @return the receiver
     */
    @JsonProperty("receiver")
    public String getReceiver() { return receiver; }

    @JsonProperty("receiver")
    public void setReceiver(String val) { this.receiver = val; }

    /**
     * Returns the message content.
     *
     * @return the message text
     */
    @JsonProperty("message")
    public String getMessage() { return message; }

    @JsonProperty("message")
    public void setMessage(String val) { this.message = val; }

    /**
     * Returns the amount deducted for this message.
     *
     * @return the amount
     */
    @JsonProperty("amount")
    public String getAmount() { return amount; }

    @JsonProperty("amount")
    public void setAmount(String val) { this.amount = val; }

    /**
     * Returns the SMS type (e.g. "generic", "flash").
     *
     * @return the SMS type
     */
    @JsonProperty("sms_type")
    public String getSmsType() { return smsType; }

    @JsonProperty("sms_type")
    public void setSmsType(String val) { this.smsType = val; }

    /**
     * Returns the unique message ID.
     *
     * @return the message identifier
     */
    @JsonProperty("message_id")
    public String getMessageId() { return messageId; }

    @JsonProperty("message_id")
    public void setMessageId(String val) { this.messageId = val; }

    /**
     * Returns the delivery status of the message.
     *
     * @return the status string
     */
    @JsonProperty("status")
    public String getStatus() { return status; }

    @JsonProperty("status")
    public void setStatus(String val) { this.status = val; }

    /**
     * Returns the timestamp when the message was created.
     *
     * @return the creation timestamp
     */
    @JsonProperty("date_created")
    public String getCreatedAt() { return createdAt; }

    @JsonProperty("date_created")
    public void setCreatedAt(String val) { this.createdAt = val; }

    /**
     * Returns the timestamp when the message was last updated.
     *
     * @return the last updated timestamp
     */
    @JsonProperty("last_updated")
    public String getUpdatedAt() { return updatedAt; }

    @JsonProperty("last_updated")
    public void setUpdatedAt(String val) { this.updatedAt = val; }
}
