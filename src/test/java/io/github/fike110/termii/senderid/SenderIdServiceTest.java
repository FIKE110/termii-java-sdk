package io.github.fike110.termii.senderid;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.senderid.model.ListSenderIdsResponse;
import io.github.fike110.termii.senderid.model.RequestSenderIdRequest;
import io.github.fike110.termii.senderid.model.RequestSenderIdResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SenderIdServiceTest {

    private MockWebServer server;
    private SenderIdService senderIdService;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        HttpClient httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-api-key"
        );
        senderIdService = new SenderIdService(httpClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void list_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"content\":[{\"country\":\"Nigeria\",\"status\":\"active\",\"sender_id\":\"PLAYPICK\"}],\"totalElements\":1,\"last\":true,\"totalPages\":1}"));

        ListSenderIdsResponse response = senderIdService.list();

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals("PLAYPICK", response.getContent().get(0).getSenderId());

        RecordedRequest recorded = server.takeRequest();
        assertTrue(recorded.getPath().contains("/api/sender-id"));
        assertTrue(recorded.getPath().contains("api_key=test-api-key"));
    }

    @Test
    void request_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"message\":\"Sender Id requested.\"}"));

        RequestSenderIdRequest request = RequestSenderIdRequest.builder()
                .senderId("PLAYPICK")
                .useCase("OTP messages")
                .company("Playpick Inc")
                .build();

        RequestSenderIdResponse response = senderIdService.request(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/sender-id/request"));
    }
}
