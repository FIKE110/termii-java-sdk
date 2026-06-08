package io.github.fike110.termii.senderid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single sender ID entry returned by the list sender IDs API.
 * <p>
 * Contains metadata about a registered sender ID such as the country, status, creation date, and the ID itself.
 * </p>
 */
public class SenderIdEntry {

    private String country;
    private String status;
    private String createdAt;
    private String senderId;

    /**
     * Returns the country associated with this sender ID.
     *
     * @return the country code
     */
    @JsonProperty("country")
    public String getCountry() { return country; }

    @JsonProperty("country")
    public void setCountry(String country) { this.country = country; }

    /**
     * Returns the status of this sender ID (e.g. "active", "pending").
     *
     * @return the status string
     */
    @JsonProperty("status")
    public String getStatus() { return status; }

    @JsonProperty("status")
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns the date and time this sender ID was created.
     *
     * @return the creation timestamp
     */
    @JsonProperty("createdAt")
    public String getCreatedAt() { return createdAt; }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    /**
     * Returns the alphanumeric sender ID.
     *
     * @return the sender ID string
     */
    @JsonProperty("sender_id")
    public String getSenderId() { return senderId; }

    @JsonProperty("sender_id")
    public void setSenderId(String senderId) { this.senderId = senderId; }
}
