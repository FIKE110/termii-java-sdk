package io.github.fike110.termii.senderid;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.senderid.model.ListSenderIdsResponse;
import io.github.fike110.termii.senderid.model.RequestSenderIdRequest;
import io.github.fike110.termii.senderid.model.RequestSenderIdResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Service for managing Termii Sender IDs.
 * <p>
 * Provides methods to list registered sender IDs and request new alphanumeric IDs.
 * </p>
 */
public class SenderIdService {

    private static final String LIST_PATH = "/api/sender-id";
    private static final String REQUEST_PATH = "/api/sender-id/request";

    private final HttpClient httpClient;

    /**
     * Creates a new SenderIdService with the given HTTP client.
     *
     * @param httpClient the HTTP client used for API communication
     */
    public SenderIdService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Lists all registered sender IDs (page 1).
     *
     * @return the paginated list of sender IDs
     */
    public ListSenderIdsResponse list() {
        return list(1);
    }

    /**
     * Lists registered sender IDs for a specific page.
     *
     * @param page the page number to retrieve
     * @return the paginated list of sender IDs
     */
    public ListSenderIdsResponse list(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        return httpClient.get(LIST_PATH, params, ListSenderIdsResponse.class);
    }

    /**
     * Asynchronously lists all registered sender IDs (page 1).
     *
     * @return a future yielding the paginated list of sender IDs
     */
    public CompletableFuture<ListSenderIdsResponse> listAsync() {
        return listAsync(1);
    }

    /**
     * Asynchronously lists registered sender IDs for a specific page.
     *
     * @param page the page number to retrieve
     * @return a future yielding the paginated list of sender IDs
     */
    public CompletableFuture<ListSenderIdsResponse> listAsync(int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        return httpClient.getAsync(LIST_PATH, params, ListSenderIdsResponse.class);
    }

    /**
     * Requests a new alphanumeric sender ID.
     *
     * @param request the sender ID request details
     * @return the API response containing status code and message
     */
    public RequestSenderIdResponse request(RequestSenderIdRequest request) {
        return httpClient.post(REQUEST_PATH, request, RequestSenderIdResponse.class);
    }

    /**
     * Asynchronously requests a new alphanumeric sender ID.
     *
     * @param request the sender ID request details
     * @return a future yielding the API response
     */
    public CompletableFuture<RequestSenderIdResponse> requestAsync(RequestSenderIdRequest request) {
        return httpClient.postAsync(REQUEST_PATH, request, RequestSenderIdResponse.class);
    }
}
