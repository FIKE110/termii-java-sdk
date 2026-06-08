package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Paginated response model for listing campaigns.
 * <p>
 * Contains a list of {@link CampaignEntry} objects along with pagination metadata.
 * </p>
 */
public class CampaignListResponse {

    private List<CampaignEntry> data;
    private int currentPage;
    private int lastPage;
    private int total;
    private int perPage;
    private int from;
    private int to;

    /**
     * Returns the list of campaign entries on this page.
     *
     * @return the campaign entries
     */
    @JsonProperty("data")
    public List<CampaignEntry> getData() { return data; }

    @JsonProperty("data")
    public void setData(List<CampaignEntry> val) { this.data = val; }

    /**
     * Returns the current page number.
     *
     * @return the current page
     */
    @JsonProperty("current_page")
    public int getCurrentPage() { return currentPage; }

    @JsonProperty("current_page")
    public void setCurrentPage(int val) { this.currentPage = val; }

    /**
     * Returns the last available page number.
     *
     * @return the last page
     */
    @JsonProperty("last_page")
    public int getLastPage() { return lastPage; }

    @JsonProperty("last_page")
    public void setLastPage(int val) { this.lastPage = val; }

    /**
     * Returns the total number of campaigns.
     *
     * @return the total count
     */
    @JsonProperty("total")
    public int getTotal() { return total; }

    @JsonProperty("total")
    public void setTotal(int val) { this.total = val; }

    /**
     * Returns the number of campaigns per page.
     *
     * @return the per-page limit
     */
    @JsonProperty("per_page")
    public int getPerPage() { return perPage; }

    @JsonProperty("per_page")
    public void setPerPage(int val) { this.perPage = val; }

    /**
     * Returns the starting item index on this page.
     *
     * @return the from index
     */
    @JsonProperty("from")
    public int getFrom() { return from; }

    @JsonProperty("from")
    public void setFrom(int val) { this.from = val; }

    /**
     * Returns the ending item index on this page.
     *
     * @return the to index
     */
    @JsonProperty("to")
    public int getTo() { return to; }

    @JsonProperty("to")
    public void setTo(int val) { this.to = val; }
}
