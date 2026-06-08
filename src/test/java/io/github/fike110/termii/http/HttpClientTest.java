package io.github.fike110.termii.http;

import io.github.fike110.termii.exception.ApiException;
import io.github.fike110.termii.exception.AuthenticationException;
import io.github.fike110.termii.exception.RateLimitException;
import io.github.fike110.termii.exception.ValidationException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpClientTest {

    private MockWebServer server;
    private HttpClient httpClient;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-key"
        );
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void post_AddsApiKeyToBody() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"status\":\"ok\"}"));

        httpClient.post("/test", Map.of("foo", "bar"), Map.class);

        RecordedRequest request = server.takeRequest();
        String body = request.getBody().readUtf8();
        assertTrue(body.contains("\"api_key\":\"test-key\""));
        assertTrue(body.contains("\"foo\":\"bar\""));
    }

    @Test
    void get_AddsApiKeyToQuery() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"status\":\"ok\"}"));

        httpClient.get("/test", Map.of("phone", "123"), Map.class);

        RecordedRequest request = server.takeRequest();
        String path = request.getPath();
        assertTrue(path.contains("api_key=test-key"));
        assertTrue(path.contains("phone=123"));
    }

    @Test
    void post_ReturnsDeserializedResponse() {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"message\":\"sent\"}"));

        Map<String, Object> request = Map.of("to", "2348000000000");
        Map<String, Object> response = httpClient.post("/send", request, Map.class);

        assertNotNull(response);
        assertEquals("ok", response.get("code"));
        assertEquals("sent", response.get("message"));
    }

    @Test
    void post_ThrowsAuthenticationExceptionOn401() {
        server.enqueue(new MockResponse().setResponseCode(401));

        Map<String, Object> request = Map.of("to", "2348000000000");
        assertThrows(AuthenticationException.class,
                () -> httpClient.post("/send", request, Map.class));
    }

    @Test
    void post_ThrowsValidationExceptionOn422() {
        server.enqueue(new MockResponse()
                .setResponseCode(422)
                .setBody("{\"message\":\"Invalid sender ID\"}"));

        Map<String, Object> request = Map.of("to", "2348000000000");
        ValidationException ex = assertThrows(ValidationException.class,
                () -> httpClient.post("/send", request, Map.class));
        assertTrue(ex.getMessage().contains("Invalid sender ID"));
    }

    @Test
    void post_ThrowsRateLimitExceptionOn429() {
        server.enqueue(new MockResponse().setResponseCode(429));

        Map<String, Object> request = Map.of("to", "2348000000000");
        assertThrows(RateLimitException.class,
                () -> httpClient.post("/send", request, Map.class));
    }

    @Test
    void post_ThrowsApiExceptionOn500() {
        server.enqueue(new MockResponse().setResponseCode(500));

        Map<String, Object> request = Map.of("to", "2348000000000");
        assertThrows(ApiException.class,
                () -> httpClient.post("/send", request, Map.class));
    }
}
