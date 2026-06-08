package io.github.fike110.termii.senderid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for listing registered sender IDs.
 * <p>
 * Contains a paginated list of {@link SenderIdEntry} objects along with
 * pagination metadata.
 * </p>
 */
public class ListSenderIdsResponse {

    private List<SenderIdEntry> content;
    private int totalElements;
    private boolean last;
    private int totalPages;

    /**
     * Returns the list of sender ID entries on this page.
     *
     * @return the sender ID entries
     */
    @JsonProperty("content")
    public List<SenderIdEntry> getContent() { return content; }

    @JsonProperty("content")
    public void setContent(List<SenderIdEntry> content) { this.content = content; }

    /**
     * Returns the total number of sender ID entries across all pages.
     *
     * @return the total element count
     */
    @JsonProperty("totalElements")
    public int getTotalElements() { return totalElements; }

    @JsonProperty("totalElements")
    public void setTotalElements(int totalElements) { this.totalElements = totalElements; }

    /**
     * Indicates whether this is the last page.
     *
     * @return true if this is the last page, false otherwise
     */
    @JsonProperty("last")
    public boolean isLast() { return last; }

    @JsonProperty("last")
    public void setLast(boolean last) { this.last = last; }

    /**
     * Returns the total number of pages available.
     *
     * @return the total page count
     */
    @JsonProperty("totalPages")
    public int getTotalPages() { return totalPages; }

    @JsonProperty("totalPages")
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }
}
