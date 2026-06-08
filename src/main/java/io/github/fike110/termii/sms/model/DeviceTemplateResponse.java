package io.github.fike110.termii.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for device template SMS operations.
 * <p>
 * Contains the status and message returned by the Termii API
 * when sending an SMS using a device template.
 * </p>
 */
public class DeviceTemplateResponse {

    private String status;
    private String message;

    /**
     * Returns the status of the device template send operation.
     *
     * @return the operation status
     */
    @JsonProperty("status")
    public String getStatus() { return status; }

    /**
     * Sets the status of the device template send operation.
     *
     * @param status the operation status
     */
    @JsonProperty("status")
    public void setStatus(String status) { this.status = status; }

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
}
