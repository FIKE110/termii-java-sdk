package io.github.fike110.termii.token;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.token.model.EmailTokenRequest;
import io.github.fike110.termii.token.model.InAppTokenRequest;
import io.github.fike110.termii.token.model.InAppTokenResponse;
import io.github.fike110.termii.token.model.TokenRequest;
import io.github.fike110.termii.token.model.TokenResponse;
import io.github.fike110.termii.token.model.VerifyTokenRequest;
import io.github.fike110.termii.token.model.VerifyTokenResponse;
import io.github.fike110.termii.token.model.VoiceCallRequest;
import io.github.fike110.termii.token.model.VoiceTokenRequest;

import java.util.concurrent.CompletableFuture;

/**
 * Service for OTP token operations via the Termii API.
 * <p>
 * Provides methods for sending, verifying, and generating OTP tokens
 * via SMS, voice, and email, with both synchronous and asynchronous support.
 * </p>
 */
public class TokenService {

    private static final String SEND_PATH = "/api/sms/otp/send";
    private static final String VERIFY_PATH = "/api/sms/otp/verify";
    private static final String IN_APP_PATH = "/api/sms/otp/generate";
    private static final String VOICE_PATH = "/api/sms/otp/send/voice";
    private static final String VOICE_CALL_PATH = "/api/sms/otp/call";
    private static final String EMAIL_PATH = "/api/email/otp/send";

    private final HttpClient httpClient;

    /**
     * Creates a new TokenService with the given HTTP client.
     *
     * @param httpClient the HTTP client used to make API requests
     */
    public TokenService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Sends an OTP token via SMS.
     *
     * @param request the token request details (recipient, sender, PIN config, etc.)
     * @return the API response containing the token status and details
     */
    public TokenResponse send(TokenRequest request) {
        return httpClient.post(SEND_PATH, request, TokenResponse.class);
    }

    /**
     * Asynchronously sends an OTP token via SMS.
     *
     * @param request the token request details
     * @return a future that resolves to the API response
     */
    public CompletableFuture<TokenResponse> sendAsync(TokenRequest request) {
        return httpClient.postAsync(SEND_PATH, request, TokenResponse.class);
    }

    /**
     * Verifies an OTP token.
     *
     * @param request the verification request with the PIN ID and PIN code
     * @return the API response indicating whether the token is verified
     */
    public VerifyTokenResponse verify(VerifyTokenRequest request) {
        return httpClient.post(VERIFY_PATH, request, VerifyTokenResponse.class);
    }

    /**
     * Asynchronously verifies an OTP token.
     *
     * @param request the verification request with the PIN ID and PIN code
     * @return a future that resolves to the API response
     */
    public CompletableFuture<VerifyTokenResponse> verifyAsync(VerifyTokenRequest request) {
        return httpClient.postAsync(VERIFY_PATH, request, VerifyTokenResponse.class);
    }

    /**
     * Generates an in-app OTP token without sending it via SMS.
     *
     * @param request the in-app token request with PIN configuration
     * @return the API response containing the generated OTP and PIN ID
     */
    public InAppTokenResponse inApp(InAppTokenRequest request) {
        return httpClient.post(IN_APP_PATH, request, InAppTokenResponse.class);
    }

    /**
     * Asynchronously generates an in-app OTP token.
     *
     * @param request the in-app token request with PIN configuration
     * @return a future that resolves to the API response
     */
    public CompletableFuture<InAppTokenResponse> inAppAsync(InAppTokenRequest request) {
        return httpClient.postAsync(IN_APP_PATH, request, InAppTokenResponse.class);
    }

    /**
     * Sends an OTP token via voice call.
     *
     * @param request the voice token request with phone number and PIN config
     * @return the API response containing the token status and details
     */
    public TokenResponse voiceToken(VoiceTokenRequest request) {
        return httpClient.post(VOICE_PATH, request, TokenResponse.class);
    }

    /**
     * Asynchronously sends an OTP token via voice call.
     *
     * @param request the voice token request with phone number and PIN config
     * @return a future that resolves to the API response
     */
    public CompletableFuture<TokenResponse> voiceTokenAsync(VoiceTokenRequest request) {
        return httpClient.postAsync(VOICE_PATH, request, TokenResponse.class);
    }

    /**
     * Initiates a voice call with an OTP code to the specified phone number.
     *
     * @param request the voice call request with phone number and code
     * @return the API response containing the call status and details
     */
    public TokenResponse voiceCall(VoiceCallRequest request) {
        return httpClient.post(VOICE_CALL_PATH, request, TokenResponse.class);
    }

    /**
     * Asynchronously initiates a voice call with an OTP code.
     *
     * @param request the voice call request with phone number and code
     * @return a future that resolves to the API response
     */
    public CompletableFuture<TokenResponse> voiceCallAsync(VoiceCallRequest request) {
        return httpClient.postAsync(VOICE_CALL_PATH, request, TokenResponse.class);
    }

    /**
     * Sends an OTP token via email.
     *
     * @param request the email token request with email address and configuration
     * @return the API response containing the token status and details
     */
    public TokenResponse email(EmailTokenRequest request) {
        return httpClient.post(EMAIL_PATH, request, TokenResponse.class);
    }

    /**
     * Asynchronously sends an OTP token via email.
     *
     * @param request the email token request with email address and configuration
     * @return a future that resolves to the API response
     */
    public CompletableFuture<TokenResponse> emailAsync(EmailTokenRequest request) {
        return httpClient.postAsync(EMAIL_PATH, request, TokenResponse.class);
    }
}
