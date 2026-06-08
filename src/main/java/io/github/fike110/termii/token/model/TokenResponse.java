package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response model for OTP token operations.
 * <p>
 * Contains the SMS status, phone number, PIN ID, balance, and other metadata
 * returned by the Termii API after sending or verifying a token.
 * </p>
 */
public class TokenResponse {

    private String smsStatus;
    private String phoneNumber;
    private String to;
    private String pinId;
    private String messageIdStr;
    private String status;
    private String code;
    private BigDecimal balance;
    private String messageId;
    private String message;
    private String user;

    /**
     * Returns the SMS delivery status.
     *
     * @return the SMS status
     */
    @JsonProperty("smsStatus")
    public String getSmsStatus() { return smsStatus; }

    /**
     * Sets the SMS delivery status.
     *
     * @param smsStatus the SMS status
     */
    @JsonProperty("smsStatus")
    public void setSmsStatus(String smsStatus) { this.smsStatus = smsStatus; }

    /**
     * Returns the recipient phone number.
     *
     * @return the phone number
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Sets the recipient phone number.
     *
     * @param phoneNumber the phone number
     */
    @JsonProperty("phone_number")
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Returns the recipient identifier.
     *
     * @return the recipient
     */
    @JsonProperty("to")
    public String getTo() { return to; }

    /**
     * Sets the recipient identifier.
     *
     * @param to the recipient
     */
    @JsonProperty("to")
    public void setTo(String to) { this.to = to; }

    /**
     * Returns the PIN ID associated with the token.
     *
     * @return the PIN identifier
     */
    @JsonProperty("pinId")
    public String getPinId() { return pinId; }

    /**
     * Sets the PIN ID associated with the token.
     *
     * @param pinId the PIN identifier
     */
    @JsonProperty("pinId")
    public void setPinId(String pinId) { this.pinId = pinId; }

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

    /**
     * Returns the overall status of the token operation.
     *
     * @return the operation status
     */
    @JsonProperty("status")
    public String getStatus() { return status; }

    /**
     * Sets the overall status of the token operation.
     *
     * @param status the operation status
     */
    @JsonProperty("status")
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns the response code.
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
     * Returns the remaining account balance.
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
     * Returns the unique message ID.
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
     * Returns the user associated with the operation.
     *
     * @return the user identifier
     */
    @JsonProperty("user")
    public String getUser() { return user; }

    /**
     * Sets the user associated with the operation.
     *
     * @param user the user identifier
     */
    @JsonProperty("user")
    public void setUser(String user) { this.user = user; }
}
