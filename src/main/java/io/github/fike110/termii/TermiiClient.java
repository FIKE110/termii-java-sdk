package io.github.fike110.termii;

import io.github.fike110.termii.campaign.CampaignService;
import io.github.fike110.termii.email.EmailService;
import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.insight.InsightService;
import io.github.fike110.termii.senderid.SenderIdService;
import io.github.fike110.termii.sms.SmsService;
import io.github.fike110.termii.token.TokenService;

/**
 * Main entry point for the Termii Java SDK.
 * <p>
 * Create an instance using {@link #builder()}, configure with {@link Builder},
 * then access API services via {@link #sms()}, {@link #token()}, etc.
 */
public class TermiiClient {

    private static final String DEFAULT_BASE_URL = "https://api.termii.com";

    private final HttpClient httpClient;
    private final SmsService smsService;
    private final TokenService tokenService;
    private final SenderIdService senderIdService;
    private final InsightService insightService;
    private final EmailService emailService;
    private final CampaignService campaignService;

    private TermiiClient(Builder builder) {
        String baseUrl = builder.baseUrl != null ? builder.baseUrl : DEFAULT_BASE_URL;
        this.httpClient = new HttpClient(
                baseUrl, builder.apiKey,
                builder.connectTimeout,
                builder.readTimeout,
                builder.writeTimeout
        );
        this.smsService = new SmsService(httpClient);
        this.tokenService = new TokenService(httpClient);
        this.senderIdService = new SenderIdService(httpClient);
        this.insightService = new InsightService(httpClient);
        this.emailService = new EmailService(httpClient);
        this.campaignService = new CampaignService(httpClient);
    }

    /**
     * Returns the SMS service for sending and managing SMS messages.
     */
    public SmsService sms() {
        return smsService;
    }

    /**
     * Returns the Token service for OTP and verification workflows.
     */
    public TokenService token() {
        return tokenService;
    }

    /**
     * Returns the Sender ID service for registering and managing sender IDs.
     */
    public SenderIdService senderId() {
        return senderIdService;
    }

    /**
     * Returns the Insight service for number intelligence and lookups.
     */
    public InsightService insight() {
        return insightService;
    }

    /**
     * Returns the Email service for sending email messages.
     */
    public EmailService email() {
        return emailService;
    }

    /**
     * Returns the Campaign service for managing phonebook campaigns.
     */
    public CampaignService campaign() {
        return campaignService;
    }

    /**
     * Creates a new {@link Builder} for constructing a {@link TermiiClient}.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for constructing {@link TermiiClient} instances.
     */
    public static class Builder {
        private String apiKey;
        private String baseUrl;
        private long connectTimeout = 30;
        private long readTimeout = 60;
        private long writeTimeout = 60;

        /**
         * Sets the Termii API key.
         */
        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        /**
         * Sets the base URL for the Termii API. Defaults to {@code https://api.termii.com}.
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /**
         * Sets the connect timeout in seconds. Default is 30.
         */
        public Builder connectTimeout(long seconds) {
            this.connectTimeout = seconds;
            return this;
        }

        /**
         * Sets the read timeout in seconds. Default is 60.
         */
        public Builder readTimeout(long seconds) {
            this.readTimeout = seconds;
            return this;
        }

        /**
         * Sets the write timeout in seconds. Default is 60.
         */
        public Builder writeTimeout(long seconds) {
            this.writeTimeout = seconds;
            return this;
        }

        /**
         * Builds a new {@link TermiiClient} instance.
         *
         * @throws IllegalArgumentException if apiKey is null or blank
         */
        public TermiiClient build() {
            if (apiKey == null || apiKey.isBlank()) {
                throw new IllegalArgumentException("apiKey is required");
            }
            return new TermiiClient(this);
        }
    }
}
