package io.github.fike110.termii.campaign;

import io.github.fike110.termii.campaign.model.CampaignHistoryResponse;
import io.github.fike110.termii.campaign.model.CampaignListResponse;
import io.github.fike110.termii.campaign.model.CampaignRequest;
import io.github.fike110.termii.http.HttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Service for managing SMS campaigns via Termii's campaign API.
 * <p>
 * Provides methods to send, list, and retrieve history for campaigns.
 * Also provides access to {@link PhonebookService} for managing phonebooks and contacts.
 * </p>
 */
public class CampaignService {

    private static final String SEND_PATH = "/api/sms/campaigns/send";
    private static final String LIST_PATH = "/api/sms/campaigns";

    private final HttpClient httpClient;
    private PhonebookService phonebookService;

    /**
     * Creates a new CampaignService with the given HTTP client.
     *
     * @param httpClient the HTTP client used for API communication
     */
    public CampaignService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Sends a new SMS campaign.
     *
     * @param request the campaign request details
     * @return the campaign list response with status
     */
    public CampaignListResponse send(CampaignRequest request) {
        return httpClient.post(SEND_PATH, request, CampaignListResponse.class);
    }

    /**
     * Asynchronously sends a new SMS campaign.
     *
     * @param request the campaign request details
     * @return a future yielding the campaign list response
     */
    public CompletableFuture<CampaignListResponse> sendAsync(CampaignRequest request) {
        return httpClient.postAsync(SEND_PATH, request, CampaignListResponse.class);
    }

    /**
     * Lists campaigns for a specific page.
     *
     * @param page the page number to retrieve
     * @return the paginated list of campaigns
     */
    public CampaignListResponse list(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        return httpClient.get(LIST_PATH, params, CampaignListResponse.class);
    }

    /**
     * Asynchronously lists campaigns for a specific page.
     *
     * @param page the page number to retrieve
     * @return a future yielding the paginated list of campaigns
     */
    public CompletableFuture<CampaignListResponse> listAsync(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        return httpClient.getAsync(LIST_PATH, params, CampaignListResponse.class);
    }

    /**
     * Retrieves the history for a specific campaign.
     *
     * @param campaignId the ID of the campaign
     * @param page       the page number to retrieve
     * @return the paginated campaign history
     */
    public CampaignHistoryResponse getHistory(String campaignId, int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        return httpClient.get(LIST_PATH + "/" + campaignId, params, CampaignHistoryResponse.class);
    }

    /**
     * Asynchronously retrieves the history for a specific campaign.
     *
     * @param campaignId the ID of the campaign
     * @param page       the page number to retrieve
     * @return a future yielding the paginated campaign history
     */
    public CompletableFuture<CampaignHistoryResponse> getHistoryAsync(String campaignId, int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        return httpClient.getAsync(LIST_PATH + "/" + campaignId, params, CampaignHistoryResponse.class);
    }

    /**
     * Returns the {@link PhonebookService} for managing phonebooks and contacts.
     *
     * @return the phonebook service instance
     */
    public PhonebookService phonebook() {
        if (phonebookService == null) {
            phonebookService = new PhonebookService(httpClient);
        }
        return phonebookService;
    }
}
