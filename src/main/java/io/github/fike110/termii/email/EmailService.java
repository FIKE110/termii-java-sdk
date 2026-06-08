package io.github.fike110.termii.email;

import io.github.fike110.termii.email.model.EmailTemplatedRequest;
import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.token.model.EmailTokenRequest;
import io.github.fike110.termii.token.model.TokenResponse;

import java.util.concurrent.CompletableFuture;

/**
 * Service for sending emails via Termii's email API.
 * <p>
 * Supports sending OTP tokens via email as well as templated email messages.
 * </p>
 */
public class EmailService {

    private static final String SEND_OTP_PATH = "/api/email/otp/send";
    private static final String SEND_TEMPLATED_PATH = "/api/templates/send-email";

    private final HttpClient httpClient;

    /**
     * Creates a new EmailService with the given HTTP client.
     *
     * @param httpClient the HTTP client used for API communication
     */
    public EmailService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Sends an OTP token to the specified email address.
     *
     * @param request the email OTP request details
     * @return the token response with OTP status
     */
    public TokenResponse sendOtp(EmailTokenRequest request) {
        return httpClient.post(SEND_OTP_PATH, request, TokenResponse.class);
    }

    /**
     * Asynchronously sends an OTP token to the specified email address.
     *
     * @param request the email OTP request details
     * @return a future yielding the token response
     */
    public CompletableFuture<TokenResponse> sendOtpAsync(EmailTokenRequest request) {
        return httpClient.postAsync(SEND_OTP_PATH, request, TokenResponse.class);
    }

    /**
     * Sends a templated email message.
     *
     * @param request the templated email request with variables
     * @return the token response
     */
    public TokenResponse sendTemplated(EmailTemplatedRequest request) {
        return httpClient.post(SEND_TEMPLATED_PATH, request, TokenResponse.class);
    }

    /**
     * Asynchronously sends a templated email message.
     *
     * @param request the templated email request with variables
     * @return a future yielding the token response
     */
    public CompletableFuture<TokenResponse> sendTemplatedAsync(EmailTemplatedRequest request) {
        return httpClient.postAsync(SEND_TEMPLATED_PATH, request, TokenResponse.class);
    }
}
