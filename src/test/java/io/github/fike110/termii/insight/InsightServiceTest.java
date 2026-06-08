package io.github.fike110.termii.insight;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.insight.model.BalanceResponse;
import io.github.fike110.termii.insight.model.DndCheckResponse;
import io.github.fike110.termii.insight.model.HistoryResponse;
import io.github.fike110.termii.insight.model.NumberQueryResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InsightServiceTest {

    private MockWebServer server;
    private InsightService insightService;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        HttpClient httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-api-key"
        );
        insightService = new InsightService(httpClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void getBalance_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"user\":\"Tester\",\"balance\":785.57,\"currency\":\"NGN\"}"));

        BalanceResponse response = insightService.getBalance();

        assertNotNull(response);
        assertEquals("Tester", response.getUser());
        assertEquals(BigDecimal.valueOf(785.57), response.getBalance());
        assertEquals("NGN", response.getCurrency());

        RecordedRequest recorded = server.takeRequest();
        assertTrue(recorded.getPath().contains("/api/get-balance"));
    }

    @Test
    void checkDnd_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"number\":\"2347065250817\",\"network_code\":\"MTN Nigeria\",\"dnd_active\":false,\"message\":\"Phone number not on DND\"}"));

        DndCheckResponse response = insightService.checkDnd("2347065250817");

        assertNotNull(response);
        assertEquals("2347065250817", response.getNumber());
        assertFalse(response.isDndActive());

        RecordedRequest recorded = server.takeRequest();
        assertTrue(recorded.getPath().contains("/api/check/dnd"));
        assertTrue(recorded.getPath().contains("phone_number=2347065250817"));
    }

    @Test
    void queryNumber_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"result\":[{\"routeDetail\":{\"number\":\"2347065250817\",\"ported\":0},\"countryDetail\":{\"countryCode\":\"234\",\"mobileCountryCode\":\"621\",\"iso\":\"NG\"},\"operatorDetail\":{\"operatorCode\":\"MTN\",\"operatorName\":\"MTN Nigeria\",\"lineType\":\"Mobile\"},\"status\":200}],\"status\":true,\"message\":\"Completed Successfully.\"}"));

        NumberQueryResponse response = insightService.queryNumber("2347065250817", "NG");

        assertNotNull(response);
        assertTrue(response.isStatus());
        assertNotNull(response.getResult());
        assertEquals(1, response.getResult().size());
        assertEquals("MTN Nigeria", response.getResult().get(0).getOperatorDetail().getOperatorName());
    }

    @Test
    void getHistory_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"receiver\":\"2347065250817\",\"message\":\"55675\",\"amount\":1.0,\"status\":\"Delivered\",\"sms_type\":\"voice\",\"send_by\":\"api\",\"message_id\":\"msg123\",\"created_at\":\"2025-08-07 14:05:53\"}]"));

        List<HistoryResponse> history = insightService.getHistory();

        assertNotNull(history);
        assertEquals(1, history.size());
        assertEquals("2347065250817", history.get(0).getReceiver());
        assertEquals("Delivered", history.get(0).getStatus());
    }
}
