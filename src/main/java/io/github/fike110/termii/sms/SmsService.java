package io.github.fike110.termii.sms;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.sms.model.AutoNumberRequest;
import io.github.fike110.termii.sms.model.DeviceTemplateRequest;
import io.github.fike110.termii.sms.model.DeviceTemplateResponse;
import io.github.fike110.termii.sms.model.SmsBulkRequest;
import io.github.fike110.termii.sms.model.SmsRequest;
import io.github.fike110.termii.sms.model.SmsResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Service for sending SMS messages via the Termii API.
 * <p>
 * Provides methods for single SMS, bulk SMS, auto-numbered SMS,
 * and device template SMS, with both synchronous and asynchronous support.
 * </p>
 */
public class SmsService {

    private static final String SEND_PATH = "/api/sms/send";
    private static final String SEND_BULK_PATH = "/api/sms/send/bulk";
    private static final String SEND_AUTO_PATH = "/api/sms/number/send";
    private static final String SEND_TEMPLATE_PATH = "/api/send/template";

    private final HttpClient httpClient;

    /**
     * Creates a new SmsService with the given HTTP client.
     *
     * @param httpClient the HTTP client used to make API requests
     */
    public SmsService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Sends a single SMS message.
     *
     * @param request the SMS request details (recipient, sender, message, etc.)
     * @return the API response containing the message status and details
     */
    public SmsResponse send(SmsRequest request) {
        return httpClient.post(SEND_PATH, request, SmsResponse.class);
    }

    /**
     * Sends a bulk SMS message to multiple recipients.
     *
     * @param request the bulk SMS request with a list of recipient numbers
     * @return the API response containing the message status and details
     */
    public SmsResponse sendBulk(SmsBulkRequest request) {
        return httpClient.post(SEND_BULK_PATH, request, SmsResponse.class);
    }

    /**
     * Sends an SMS using Termii's auto-generated numbering.
     *
     * @param request the auto-number request with recipient and message
     * @return the API response containing the message status and details
     */
    public SmsResponse sendFromAutoNumber(AutoNumberRequest request) {
        return httpClient.post(SEND_AUTO_PATH, request, SmsResponse.class);
    }

    /**
     * Sends an SMS using a predefined device template.
     *
     * @param request the device template request with template and device details
     * @return the API response containing the device template send status
     */
    public DeviceTemplateResponse sendDeviceTemplate(DeviceTemplateRequest request) {
        return httpClient.post(SEND_TEMPLATE_PATH, request, DeviceTemplateResponse.class);
    }

    /**
     * Asynchronously sends a single SMS message.
     *
     * @param request the SMS request details
     * @return a future that resolves to the API response
     */
    public CompletableFuture<SmsResponse> sendAsync(SmsRequest request) {
        return httpClient.postAsync(SEND_PATH, request, SmsResponse.class);
    }

    /**
     * Asynchronously sends a bulk SMS message to multiple recipients.
     *
     * @param request the bulk SMS request with a list of recipient numbers
     * @return a future that resolves to the API response
     */
    public CompletableFuture<SmsResponse> sendBulkAsync(SmsBulkRequest request) {
        return httpClient.postAsync(SEND_BULK_PATH, request, SmsResponse.class);
    }

    /**
     * Asynchronously sends an SMS using Termii's auto-generated numbering.
     *
     * @param request the auto-number request with recipient and message
     * @return a future that resolves to the API response
     */
    public CompletableFuture<SmsResponse> sendFromAutoNumberAsync(AutoNumberRequest request) {
        return httpClient.postAsync(SEND_AUTO_PATH, request, SmsResponse.class);
    }

    /**
     * Asynchronously sends an SMS using a predefined device template.
     *
     * @param request the device template request with template and device details
     * @return a future that resolves to the API response
     */
    public CompletableFuture<DeviceTemplateResponse> sendDeviceTemplateAsync(DeviceTemplateRequest request) {
        return httpClient.postAsync(SEND_TEMPLATE_PATH, request, DeviceTemplateResponse.class);
    }
}
