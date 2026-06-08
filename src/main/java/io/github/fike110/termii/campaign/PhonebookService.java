package io.github.fike110.termii.campaign;

import io.github.fike110.termii.campaign.model.AddContactRequest;
import io.github.fike110.termii.campaign.model.ContactEntry;
import io.github.fike110.termii.campaign.model.CreatePhonebookRequest;
import io.github.fike110.termii.campaign.model.PhonebookEntry;
import io.github.fike110.termii.http.HttpClient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Service for managing phonebooks and contacts.
 * <p>
 * Provides methods to create, list, update, and delete phonebooks, as well as
 * manage contacts within a phonebook and import contacts via CSV file.
 * </p>
 */
public class PhonebookService {

    private static final String PHONEBOOKS_PATH = "/api/phonebooks";
    private static final String CONTACTS_UPLOAD_PATH = "/api/phonebooks/contacts/upload";

    private final HttpClient httpClient;

    /**
     * Creates a new PhonebookService with the given HTTP client.
     *
     * @param httpClient the HTTP client used for API communication
     */
    public PhonebookService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Lists all phonebooks for a specific page.
     *
     * @param page the page number to retrieve
     * @return a list of phonebook entries
     */
    public List<PhonebookEntry> list(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        PhonebookEntry[] result = httpClient.get(PHONEBOOKS_PATH, params, PhonebookEntry[].class);
        return Arrays.asList(result);
    }

    /**
     * Asynchronously lists all phonebooks for a specific page.
     *
     * @param page the page number to retrieve
     * @return a future yielding a list of phonebook entries
     */
    public CompletableFuture<List<PhonebookEntry>> listAsync(int page) {
        return CompletableFuture.supplyAsync(() -> list(page));
    }

    /**
     * Creates a new phonebook.
     *
     * @param request the phonebook creation request
     * @return the created phonebook entry
     */
    public PhonebookEntry create(CreatePhonebookRequest request) {
        return httpClient.post(PHONEBOOKS_PATH, request, PhonebookEntry.class);
    }

    /**
     * Asynchronously creates a new phonebook.
     *
     * @param request the phonebook creation request
     * @return a future yielding the created phonebook entry
     */
    public CompletableFuture<PhonebookEntry> createAsync(CreatePhonebookRequest request) {
        return httpClient.postAsync(PHONEBOOKS_PATH, request, PhonebookEntry.class);
    }

    /**
     * Updates an existing phonebook.
     *
     * @param phonebookId the ID of the phonebook to update
     * @param request     the updated phonebook details
     */
    public void update(String phonebookId, CreatePhonebookRequest request) {
        httpClient.patch(PHONEBOOKS_PATH + "/" + phonebookId, request, Map.class);
    }

    /**
     * Asynchronously updates an existing phonebook.
     *
     * @param phonebookId the ID of the phonebook to update
     * @param request     the updated phonebook details
     * @return a future that completes when the update is done
     */
    public CompletableFuture<Void> updateAsync(String phonebookId, CreatePhonebookRequest request) {
        return CompletableFuture.runAsync(() -> update(phonebookId, request));
    }

    /**
     * Deletes a phonebook by ID.
     *
     * @param phonebookId the ID of the phonebook to delete
     */
    public void delete(String phonebookId) {
        httpClient.delete(PHONEBOOKS_PATH + "/" + phonebookId, Map.class);
    }

    /**
     * Asynchronously deletes a phonebook by ID.
     *
     * @param phonebookId the ID of the phonebook to delete
     * @return a future that completes when the deletion is done
     */
    public CompletableFuture<Void> deleteAsync(String phonebookId) {
        return CompletableFuture.runAsync(() -> delete(phonebookId));
    }

    /**
     * Retrieves the contacts in a phonebook.
     *
     * @param phonebookId the ID of the phonebook
     * @param page        the page number to retrieve
     * @return a list of contact entries
     */
    public List<ContactEntry> getContacts(String phonebookId, int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        ContactEntry[] result = httpClient.get(
                PHONEBOOKS_PATH + "/" + phonebookId + "/contacts",
                params,
                ContactEntry[].class
        );
        return Arrays.asList(result);
    }

    /**
     * Asynchronously retrieves the contacts in a phonebook.
     *
     * @param phonebookId the ID of the phonebook
     * @param page        the page number to retrieve
     * @return a future yielding a list of contact entries
     */
    public CompletableFuture<List<ContactEntry>> getContactsAsync(String phonebookId, int page) {
        return CompletableFuture.supplyAsync(() -> getContacts(phonebookId, page));
    }

    /**
     * Adds a single contact to a phonebook.
     *
     * @param phonebookId the ID of the phonebook
     * @param request     the contact details
     * @return the created contact entry
     */
    public ContactEntry addContact(String phonebookId, AddContactRequest request) {
        return httpClient.post(
                PHONEBOOKS_PATH + "/" + phonebookId + "/contacts",
                request,
                ContactEntry.class
        );
    }

    /**
     * Asynchronously adds a single contact to a phonebook.
     *
     * @param phonebookId the ID of the phonebook
     * @param request     the contact details
     * @return a future yielding the created contact entry
     */
    public CompletableFuture<ContactEntry> addContactAsync(String phonebookId, AddContactRequest request) {
        return httpClient.postAsync(
                PHONEBOOKS_PATH + "/" + phonebookId + "/contacts",
                request,
                ContactEntry.class
        );
    }

    /**
     * Deletes a contact by contact ID.
     *
     * @param contactId the ID of the contact to delete
     */
    public void deleteContact(String contactId) {
        httpClient.delete("/api/phonebook/contact/" + contactId, Map.class);
    }

    /**
     * Asynchronously deletes a contact by contact ID.
     *
     * @param contactId the ID of the contact to delete
     * @return a future that completes when the deletion is done
     */
    public CompletableFuture<Void> deleteContactAsync(String contactId) {
        return CompletableFuture.runAsync(() -> deleteContact(contactId));
    }

    /**
     * Imports contacts into a phonebook from a CSV file.
     *
     * @param phonebookId the ID of the target phonebook
     * @param countryCode the country code for the contacts
     * @param fileBytes   the raw bytes of the CSV file
     * @param fileName    the filename of the CSV file
     * @throws RuntimeException if the import request fails
     */
    public void importContact(String phonebookId, String countryCode, byte[] fileBytes, String fileName) {
        var contactJson = "{\"pid\":\"" + phonebookId + "\",\"country_code\":\"" + countryCode + "\",\"api_key\":\"" + httpClient.getApiKey() + "\"}";
        var body = new okhttp3.MultipartBody.Builder()
                .setType(okhttp3.MultipartBody.FORM)
                .addFormDataPart("file", fileName, okhttp3.RequestBody.create(fileBytes, okhttp3.MediaType.parse("text/csv")))
                .addFormDataPart("contact", contactJson)
                .build();
        var request = new okhttp3.Request.Builder()
                .url(httpClient.getBaseUrl() + CONTACTS_UPLOAD_PATH)
                .post(body)
                .build();
        try {
            httpClient.getOkClient().newCall(request).execute();
        } catch (java.io.IOException e) {
            throw new RuntimeException("Import contact failed", e);
        }
    }

    /**
     * Asynchronously imports contacts into a phonebook from a CSV file.
     *
     * @param phonebookId the ID of the target phonebook
     * @param countryCode the country code for the contacts
     * @param fileBytes   the raw bytes of the CSV file
     * @param fileName    the filename of the CSV file
     * @return a future that completes when the import is done
     */
    public CompletableFuture<Void> importContactAsync(String phonebookId, String countryCode, byte[] fileBytes, String fileName) {
        return CompletableFuture.runAsync(() -> importContact(phonebookId, countryCode, fileBytes, fileName));
    }
}
