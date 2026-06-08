package io.github.fike110.termii.insight;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.insight.model.BalanceResponse;
import io.github.fike110.termii.insight.model.DndCheckResponse;
import io.github.fike110.termii.insight.model.HistoryResponse;
import io.github.fike110.termii.insight.model.NumberQueryResponse;

/**
 * Service for Termii insight and monitoring APIs.
 * <p>
 * Provides methods to check account balance, verify DND status, query phone number details,
 * and retrieve SMS history.
 * </p>
 */
public class InsightService {

    private static final String BALANCE_PATH = "/api/get-balance";
    private static final String DND_CHECK_PATH = "/api/check/dnd";
    private static final String NUMBER_QUERY_PATH = "/api/insight/number/query";
    private static final String HISTORY_PATH = "/api/sms/inbox";

    private final HttpClient httpClient;

    /**
     * Creates a new InsightService with the given HTTP client.
     *
     * @param httpClient the HTTP client used for API communication
     */
    public InsightService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Retrieves the account balance.
     *
     * @return the balance response with application, balance amount, and currency
     */
    public BalanceResponse getBalance() {
        Map<String, String> params = Collections.emptyMap();
        return httpClient.get(BALANCE_PATH, params, BalanceResponse.class);
    }

    /**
     * Asynchronously retrieves the account balance.
     *
     * @return a future yielding the balance response
     */
    public CompletableFuture<BalanceResponse> getBalanceAsync() {
        Map<String, String> params = Collections.emptyMap();
        return httpClient.getAsync(BALANCE_PATH, params, BalanceResponse.class);
    }

    /**
     * Checks whether a phone number is registered on the Do-Not-Disturb (DND) list.
     *
     * @param phoneNumber the phone number to check
     * @return the DND check response with network info and DND status
     */
    public DndCheckResponse checkDnd(String phoneNumber) {
        Map<String, String> params = new HashMap<>();
        params.put("phone_number", phoneNumber);
        return httpClient.get(DND_CHECK_PATH, params, DndCheckResponse.class);
    }

    /**
     * Asynchronously checks whether a phone number is registered on the DND list.
     *
     * @param phoneNumber the phone number to check
     * @return a future yielding the DND check response
     */
    public CompletableFuture<DndCheckResponse> checkDndAsync(String phoneNumber) {
        Map<String, String> params = new HashMap<>();
        params.put("phone_number", phoneNumber);
        return httpClient.getAsync(DND_CHECK_PATH, params, DndCheckResponse.class);
    }

    /**
     * Queries detailed information about a phone number.
     *
     * @param phoneNumber the phone number to query
     * @param countryCode the ISO country code
     * @return the number query response with routing, country, and operator details
     */
    public NumberQueryResponse queryNumber(String phoneNumber, String countryCode) {
        Map<String, String> params = new HashMap<>();
        params.put("phone_number", phoneNumber);
        params.put("country_code", countryCode);
        return httpClient.get(NUMBER_QUERY_PATH, params, NumberQueryResponse.class);
    }

    /**
     * Asynchronously queries detailed information about a phone number.
     *
     * @param phoneNumber the phone number to query
     * @param countryCode the ISO country code
     * @return a future yielding the number query response
     */
    public CompletableFuture<NumberQueryResponse> queryNumberAsync(String phoneNumber, String countryCode) {
        Map<String, String> params = new HashMap<>();
        params.put("phone_number", phoneNumber);
        params.put("country_code", countryCode);
        return httpClient.getAsync(NUMBER_QUERY_PATH, params, NumberQueryResponse.class);
    }

    /**
     * Retrieves the SMS inbox history.
     *
     * @return a list of history entries
     */
    public List<HistoryResponse> getHistory() {
        HistoryResponse[] result = httpClient.get(HISTORY_PATH, Collections.emptyMap(), HistoryResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Asynchronously retrieves the SMS inbox history.
     *
     * @return a future yielding a list of history entries
     */
    public CompletableFuture<List<HistoryResponse>> getHistoryAsync() {
        return CompletableFuture.supplyAsync(this::getHistory);
    }

    /**
     * Retrieves SMS inbox history filtered by a specific message ID.
     *
     * @param messageId the message ID to filter by
     * @return a list of matching history entries
     */
    public List<HistoryResponse> getHistoryByMessageId(String messageId) {
        Map<String, String> params = new HashMap<>();
        params.put("message_id", messageId);
        HistoryResponse[] result = httpClient.get(HISTORY_PATH, params, HistoryResponse[].class);
        return Arrays.asList(result);
    }
}
