package io.github.fike110.termii.senderid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for a sender ID request.
 * <p>
 * Contains the status code and message returned by the API after requesting a new sender ID.
 * </p>
 */
public class RequestSenderIdResponse {

    private String code;
    private String message;

    /**
     * Returns the response status code.
     *
     * @return the status code
     */
    @JsonProperty("code")
    public String getCode() { return code; }

    @JsonProperty("code")
    public void setCode(String code) { this.code = code; }

    /**
     * Returns the response message.
     *
     * @return the message text
     */
    @JsonProperty("message")
    public String getMessage() { return message; }

    @JsonProperty("message")
    public void setMessage(String message) { this.message = message; }
}
