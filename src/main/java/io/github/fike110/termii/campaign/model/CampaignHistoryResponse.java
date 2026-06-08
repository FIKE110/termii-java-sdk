package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Paginated response model for campaign history.
 * <p>
 * Contains a list of {@link CampaignHistoryEntry} objects along with pagination metadata.
 * </p>
 */
public class CampaignHistoryResponse {

    private List<CampaignHistoryEntry> data;
    private int currentPage;
    private int lastPage;
    private int total;
    private int perPage;

    /**
     * Returns the list of campaign history entries on this page.
     *
     * @return the history entries
     */
    @JsonProperty("data")
    public List<CampaignHistoryEntry> getData() { return data; }

    @JsonProperty("data")
    public void setData(List<CampaignHistoryEntry> val) { this.data = val; }

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
     * Returns the total number of history entries.
     *
     * @return the total count
     */
    @JsonProperty("total")
    public int getTotal() { return total; }

    @JsonProperty("total")
    public void setTotal(int val) { this.total = val; }

    /**
     * Returns the number of entries per page.
     *
     * @return the per-page limit
     */
    @JsonProperty("per_page")
    public int getPerPage() { return perPage; }

    @JsonProperty("per_page")
    public void setPerPage(int val) { this.perPage = val; }
}
