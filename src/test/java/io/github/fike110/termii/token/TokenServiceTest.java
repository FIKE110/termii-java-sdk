package io.github.fike110.termii.token;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.model.enums.MessageChannel;
import io.github.fike110.termii.model.enums.PinType;
import io.github.fike110.termii.token.model.EmailTokenRequest;
import io.github.fike110.termii.token.model.InAppTokenRequest;
import io.github.fike110.termii.token.model.InAppTokenResponse;
import io.github.fike110.termii.token.model.TokenRequest;
import io.github.fike110.termii.token.model.TokenResponse;
import io.github.fike110.termii.token.model.VerifyTokenRequest;
import io.github.fike110.termii.token.model.VerifyTokenResponse;
import io.github.fike110.termii.token.model.VoiceCallRequest;
import io.github.fike110.termii.token.model.VoiceTokenRequest;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenServiceTest {

    private MockWebServer server;
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        HttpClient httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-api-key"
        );
        tokenService = new TokenService(httpClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void send_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"pinId\":\"abc-123\",\"to\":\"2348012345678\",\"smsStatus\":\"Message Sent\"}"));

        TokenRequest request = TokenRequest.builder()
                .to("2348012345678")
                .from("PLAYPICK")
                .messageText("Your pin is < 123456 >")
                .build();

        TokenResponse response = tokenService.send(request);

        assertNotNull(response);
        assertEquals("abc-123", response.getPinId());
        assertEquals("Message Sent", response.getSmsStatus());

        RecordedRequest recorded = server.takeRequest();
        assertTrue(recorded.getPath().contains("/api/sms/otp/send"));
    }

    @Test
    void verify_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"pinId\":\"abc-123\",\"verified\":\"True\",\"msisdn\":\"2348012345678\"}"));

        VerifyTokenRequest request = VerifyTokenRequest.builder()
                .pinId("abc-123")
                .pin("123456")
                .build();

        VerifyTokenResponse response = tokenService.verify(request);

        assertNotNull(response);
        assertTrue(response.isVerified());
        assertEquals("2348012345678", response.getMsisdn());
    }

    @Test
    void inApp_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"pin_id\":\"pin-123\",\"otp\":\"5635\",\"phone_number\":\"2348012345678\"}"));

        InAppTokenRequest request = InAppTokenRequest.builder()
                .phoneNumber("2348012345678")
                .pinLength(4)
                .build();

        InAppTokenResponse response = tokenService.inApp(request);

        assertNotNull(response);
        assertEquals("5635", response.getOtp());
        assertEquals("pin-123", response.getPinId());
    }

    @Test
    void voiceToken_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"message\":\"Successfully Sent\",\"pinId\":\"voice-pin-123\"}"));

        VoiceTokenRequest request = VoiceTokenRequest.builder()
                .phoneNumber("2348012345678")
                .pinLength(6)
                .build();

        TokenResponse response = tokenService.voiceToken(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());
        assertEquals("voice-pin-123", response.getPinId());
    }

    @Test
    void voiceCall_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"message\":\"Successfully Sent\",\"pinId\":\"call-pin-123\"}"));

        VoiceCallRequest request = VoiceCallRequest.builder()
                .phoneNumber("2348012345678")
                .code("55675")
                .build();

        TokenResponse response = tokenService.voiceCall(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());
    }

    @Test
    void email_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"balance\":\"785.57\",\"message\":\"Successfully Sent\"}"));

        EmailTokenRequest request = EmailTokenRequest.builder()
                .emailAddress("test@example.com")
                .code("092471")
                .emailConfigurationId("config-123")
                .build();

        TokenResponse response = tokenService.email(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());
    }
}
