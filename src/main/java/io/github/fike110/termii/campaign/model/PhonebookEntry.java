package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A phonebook entry returned by the phonebook list API.
 * <p>
 * Contains the ID, name, description, contact count, and timestamps for a phonebook.
 * </p>
 */
public class PhonebookEntry {

    private String id;
    private String name;
    private String description;
    private int totalNumberOfContacts;
    private String dateCreated;
    private String lastUpdated;

    /**
     * Returns the unique phonebook ID.
     *
     * @return the ID
     */
    @JsonProperty("id")
    public String getId() { return id; }

    @JsonProperty("id")
    public void setId(String val) { this.id = val; }

    /**
     * Returns the phonebook name.
     *
     * @return the name
     */
    @JsonProperty("name")
    public String getName() { return name; }

    @JsonProperty("name")
    public void setName(String val) { this.name = val; }

    /**
     * Returns the phonebook description.
     *
     * @return the description
     */
    @JsonProperty("description")
    public String getDescription() { return description; }

    @JsonProperty("description")
    public void setDescription(String val) { this.description = val; }

    /**
     * Returns the total number of contacts in this phonebook.
     *
     * @return the contact count
     */
    @JsonProperty("total_number_of_contacts")
    public int getTotalNumberOfContacts() { return totalNumberOfContacts; }

    @JsonProperty("total_number_of_contacts")
    public void setTotalNumberOfContacts(int val) { this.totalNumberOfContacts = val; }

    /**
     * Returns the timestamp when the phonebook was created.
     *
     * @return the creation timestamp
     */
    @JsonProperty("date_created")
    public String getDateCreated() { return dateCreated; }

    @JsonProperty("date_created")
    public void setDateCreated(String val) { this.dateCreated = val; }

    /**
     * Returns the timestamp when the phonebook was last updated.
     *
     * @return the last updated timestamp
     */
    @JsonProperty("last_updated")
    public String getLastUpdated() { return lastUpdated; }

    @JsonProperty("last_updated")
    public void setLastUpdated(String val) { this.lastUpdated = val; }
}
