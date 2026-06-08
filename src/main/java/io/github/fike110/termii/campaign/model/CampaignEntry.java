package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A single campaign entry returned by the campaign list API.
 * <p>
 * Contains metadata about a campaign including its ID, target phonebook, sender,
 * channel, status, and timestamps.
 * </p>
 */
public class CampaignEntry {

    private String campaignId;
    private String phoneBook;
    private String sender;
    private String channel;
    private String campType;
    private String status;
    private String totalRecipients;
    private String createdAt;

    /**
     * Returns the unique campaign ID.
     *
     * @return the campaign ID
     */
    @JsonProperty("campaign_id")
    public String getCampaignId() { return campaignId; }

    @JsonProperty("campaign_id")
    public void setCampaignId(String val) { this.campaignId = val; }

    /**
     * Returns the name of the target phonebook.
     *
     * @return the phonebook name
     */
    @JsonProperty("phone_book")
    public String getPhoneBook() { return phoneBook; }

    @JsonProperty("phone_book")
    public void setPhoneBook(String val) { this.phoneBook = val; }

    /**
     * Returns the sender ID used for the campaign.
     *
     * @return the sender
     */
    @JsonProperty("sender")
    public String getSender() { return sender; }

    @JsonProperty("sender")
    public void setSender(String val) { this.sender = val; }

    /**
     * Returns the channel used for the campaign.
     *
     * @return the channel
     */
    @JsonProperty("channel")
    public String getChannel() { return channel; }

    @JsonProperty("channel")
    public void setChannel(String val) { this.channel = val; }

    /**
     * Returns the campaign type.
     *
     * @return the campaign type
     */
    @JsonProperty("camp_type")
    public String getCampType() { return campType; }

    @JsonProperty("camp_type")
    public void setCampType(String val) { this.campType = val; }

    /**
     * Returns the campaign status.
     *
     * @return the status string
     */
    @JsonProperty("status")
    public String getStatus() { return status; }

    @JsonProperty("status")
    public void setStatus(String val) { this.status = val; }

    /**
     * Returns the total number of recipients.
     *
     * @return the recipient count
     */
    @JsonProperty("total_recipients")
    public String getTotalRecipients() { return totalRecipients; }

    @JsonProperty("total_recipients")
    public void setTotalRecipients(String val) { this.totalRecipients = val; }

    /**
     * Returns the timestamp when the campaign was created.
     *
     * @return the creation timestamp
     */
    @JsonProperty("created_at")
    public String getCreatedAt() { return createdAt; }

    @JsonProperty("created_at")
    public void setCreatedAt(String val) { this.createdAt = val; }
}
