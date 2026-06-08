package io.github.fike110.termii.campaign.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A contact entry within a phonebook.
 * <p>
 * Contains the contact's ID, phone number, email, name, company, country code, and timestamps.
 * </p>
 */
public class ContactEntry {

    private int id;
    private String pid;
    private String phoneNumber;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String company;
    private String countryCode;
    private String createdAt;
    private String updatedAt;

    /**
     * Returns the unique ID of this contact.
     *
     * @return the contact ID
     */
    @JsonProperty("id")
    public int getId() { return id; }

    @JsonProperty("id")
    public void setId(int val) { this.id = val; }

    /**
     * Returns the phonebook ID this contact belongs to.
     *
     * @return the phonebook ID
     */
    @JsonProperty("pid")
    public String getPid() { return pid; }

    @JsonProperty("pid")
    public void setPid(String val) { this.pid = val; }

    /**
     * Returns the phone number of the contact.
     *
     * @return the phone number
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() { return phoneNumber; }

    @JsonProperty("phone_number")
    public void setPhoneNumber(String val) { this.phoneNumber = val; }

    /**
     * Returns the email address of the contact.
     *
     * @return the email address
     */
    @JsonProperty("email_address")
    public String getEmailAddress() { return emailAddress; }

    @JsonProperty("email_address")
    public void setEmailAddress(String val) { this.emailAddress = val; }

    /**
     * Returns the first name of the contact.
     *
     * @return the first name
     */
    @JsonProperty("first_name")
    public String getFirstName() { return firstName; }

    @JsonProperty("first_name")
    public void setFirstName(String val) { this.firstName = val; }

    /**
     * Returns the last name of the contact.
     *
     * @return the last name
     */
    @JsonProperty("last_name")
    public String getLastName() { return lastName; }

    @JsonProperty("last_name")
    public void setLastName(String val) { this.lastName = val; }

    /**
     * Returns the company name of the contact.
     *
     * @return the company name
     */
    @JsonProperty("company")
    public String getCompany() { return company; }

    @JsonProperty("company")
    public void setCompany(String val) { this.company = val; }

    /**
     * Returns the country code of the contact.
     *
     * @return the country code
     */
    @JsonProperty("country_code")
    public String getCountryCode() { return countryCode; }

    @JsonProperty("country_code")
    public void setCountryCode(String val) { this.countryCode = val; }

    /**
     * Returns the timestamp when the contact was created.
     *
     * @return the creation timestamp
     */
    @JsonProperty("create_at")
    public String getCreatedAt() { return createdAt; }

    @JsonProperty("create_at")
    public void setCreatedAt(String val) { this.createdAt = val; }

    /**
     * Returns the timestamp when the contact was last updated.
     *
     * @return the last updated timestamp
     */
    @JsonProperty("updated_at")
    public String getUpdatedAt() { return updatedAt; }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String val) { this.updatedAt = val; }
}
