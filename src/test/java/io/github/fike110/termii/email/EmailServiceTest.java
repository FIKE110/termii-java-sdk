package io.github.fike110.termii.email;

import io.github.fike110.termii.email.model.EmailTemplatedRequest;
import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.token.model.EmailTokenRequest;
import io.github.fike110.termii.token.model.TokenResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailServiceTest {

    private MockWebServer server;
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        HttpClient httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-api-key"
        );
        emailService = new EmailService(httpClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void sendOtp_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"balance\":\"785.57\",\"message\":\"Successfully Sent\",\"user\":\"Frech\"}"));

        EmailTokenRequest request = EmailTokenRequest.builder()
                .emailAddress("test@example.com")
                .code("092471")
                .emailConfigurationId("config-123")
                .build();

        TokenResponse response = emailService.sendOtp(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());

        RecordedRequest recorded = server.takeRequest();
        assertTrue(recorded.getPath().contains("/api/email/otp/send"));
    }

    @Test
    void sendTemplated_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"message\":\"Successfully Sent\"}"));

        EmailTemplatedRequest request = EmailTemplatedRequest.builder()
                .email("test@example.com")
                .subject("Welcome")
                .emailConfigurationId("config-123")
                .templateId("template-456")
                .variables(Map.of("name", "John", "balance", "5000"))
                .build();

        TokenResponse response = emailService.sendTemplated(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());

        RecordedRequest recorded = server.takeRequest();
        assertTrue(recorded.getPath().contains("/api/templates/send-email"));
    }
}
