package io.github.fike110.termii.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response model for SMS send operations.
 * <p>
 * Contains the status, balance, message ID, and other metadata
 * returned by the Termii API after sending an SMS.
 * </p>
 */
public class SmsResponse {

    private String code;
    private BigDecimal balance;
    private String messageId;
    private String message;
    private String user;
    private String messageIdStr;

    /**
     * Returns the response code indicating the status of the SMS send.
     *
     * @return the response code
     */
    @JsonProperty("code")
    public String getCode() { return code; }

    /**
     * Sets the response code.
     *
     * @param code the response code
     */
    @JsonProperty("code")
    public void setCode(String code) { this.code = code; }

    /**
     * Returns the remaining account balance after the SMS send.
     *
     * @return the account balance
     */
    @JsonProperty("balance")
    public BigDecimal getBalance() { return balance; }

    /**
     * Sets the remaining account balance.
     *
     * @param balance the account balance
     */
    @JsonProperty("balance")
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    /**
     * Returns the unique message ID assigned to the sent SMS.
     *
     * @return the message identifier
     */
    @JsonProperty("message_id")
    public String getMessageId() { return messageId; }

    /**
     * Sets the unique message ID.
     *
     * @param messageId the message identifier
     */
    @JsonProperty("message_id")
    public void setMessageId(String messageId) { this.messageId = messageId; }

    /**
     * Returns the response message from the API.
     *
     * @return the response message
     */
    @JsonProperty("message")
    public String getMessage() { return message; }

    /**
     * Sets the response message.
     *
     * @param message the response message
     */
    @JsonProperty("message")
    public void setMessage(String message) { this.message = message; }

    /**
     * Returns the user associated with the SMS send.
     *
     * @return the user identifier
     */
    @JsonProperty("user")
    public String getUser() { return user; }

    /**
     * Sets the user associated with the SMS send.
     *
     * @param user the user identifier
     */
    @JsonProperty("user")
    public void setUser(String user) { this.user = user; }

    /**
     * Returns the string representation of the message ID.
     *
     * @return the message ID as a string
     */
    @JsonProperty("message_id_str")
    public String getMessageIdStr() { return messageIdStr; }

    /**
     * Sets the string representation of the message ID.
     *
     * @param messageIdStr the message ID as a string
     */
    @JsonProperty("message_id_str")
    public void setMessageIdStr(String messageIdStr) { this.messageIdStr = messageIdStr; }
}
