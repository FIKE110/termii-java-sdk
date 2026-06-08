package io.github.fike110.termii.insight.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response model for an SMS inbox history entry.
 * <p>
 * Contains details about a sent or received SMS message, including sender, receiver,
 * amount, status, and timestamps.
 * </p>
 */
public class HistoryResponse {

    private String sender;
    private String receiver;
    private String message;
    private BigDecimal amount;
    private int reroute;
    private String status;
    private String smsType;
    private String sendBy;
    private String messageId;
    private String createdAt;

    /**
     * Returns the sender ID of the message.
     *
     * @return the sender
     */
    @JsonProperty("sender")
    public String getSender() { return sender; }

    @JsonProperty("sender")
    public void setSender(String sender) { this.sender = sender; }

    /**
     * Returns the receiver phone number.
     *
     * @return the receiver
     */
    @JsonProperty("receiver")
    public String getReceiver() { return receiver; }

    @JsonProperty("receiver")
    public void setReceiver(String receiver) { this.receiver = receiver; }

    /**
     * Returns the message content.
     *
     * @return the message text
     */
    @JsonProperty("message")
    public String getMessage() { return message; }

    @JsonProperty("message")
    public void setMessage(String message) { this.message = message; }

    /**
     * Returns the amount deducted for this message.
     *
     * @return the amount
     */
    @JsonProperty("amount")
    public BigDecimal getAmount() { return amount; }

    @JsonProperty("amount")
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    /**
     * Returns the reroute count for this message.
     *
     * @return the reroute count
     */
    @JsonProperty("reroute")
    public int getReroute() { return reroute; }

    @JsonProperty("reroute")
    public void setReroute(int reroute) { this.reroute = reroute; }

    /**
     * Returns the delivery status of the message.
     *
     * @return the status string
     */
    @JsonProperty("status")
    public String getStatus() { return status; }

    @JsonProperty("status")
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns the type of SMS (e.g. "generic", "flash").
     *
     * @return the SMS type
     */
    @JsonProperty("sms_type")
    public String getSmsType() { return smsType; }

    @JsonProperty("sms_type")
    public void setSmsType(String smsType) { this.smsType = smsType; }

    /**
     * Returns how the message was sent (e.g. "api").
     *
     * @return the send method
     */
    @JsonProperty("send_by")
    public String getSendBy() { return sendBy; }

    @JsonProperty("send_by")
    public void setSendBy(String sendBy) { this.sendBy = sendBy; }

    /**
     * Returns the unique message ID.
     *
     * @return the message identifier
     */
    @JsonProperty("message_id")
    public String getMessageId() { return messageId; }

    @JsonProperty("message_id")
    public void setMessageId(String messageId) { this.messageId = messageId; }

    /**
     * Returns the timestamp when the message was created.
     *
     * @return the creation timestamp
     */
    @JsonProperty("created_at")
    public String getCreatedAt() { return createdAt; }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
