package io.github.fike110.termii.campaign;

import io.github.fike110.termii.campaign.model.CampaignHistoryResponse;
import io.github.fike110.termii.campaign.model.CampaignListResponse;
import io.github.fike110.termii.campaign.model.CampaignRequest;
import io.github.fike110.termii.http.HttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CampaignServiceTest {

    private MockWebServer server;
    private CampaignService campaignService;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        HttpClient httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-api-key"
        );
        campaignService = new CampaignService(httpClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void send_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"data\":[{\"campaign_id\":\"cmp-123\",\"sender\":\"PLAYPICK\",\"phone_book\":\"pb-1\",\"channel\":\"dnd\",\"camp_type\":\"sms\",\"status\":\"active\",\"total_recipients\":\"100\",\"created_at\":\"2025-01-01\"}],\"current_page\":1,\"last_page\":1,\"total\":1,\"per_page\":50,\"from\":1,\"to\":1}"));

        CampaignRequest request = CampaignRequest.builder()
                .senderId("PLAYPICK")
                .phonebookId("pb-1")
                .campaignType("sms")
                .message("Hello from campaign")
                .build();

        CampaignListResponse response = campaignService.send(request);

        assertNotNull(response);
        assertEquals(1, response.getTotal());
        assertEquals("cmp-123", response.getData().get(0).getCampaignId());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/sms/campaigns/send"));
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"api_key\":\"test-api-key\""));
        assertTrue(body.contains("\"sender_id\":\"PLAYPICK\""));
    }

    @Test
    void sendAsync_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"data\":[],\"current_page\":1,\"last_page\":1,\"total\":0,\"per_page\":50,\"from\":0,\"to\":0}"));

        CampaignRequest request = CampaignRequest.builder()
                .senderId("PLAYPICK")
                .phonebookId("pb-1")
                .campaignType("sms")
                .build();

        CompletableFuture<CampaignListResponse> future = campaignService.sendAsync(request);
        CampaignListResponse response = future.get();

        assertNotNull(response);
        assertEquals(0, response.getTotal());
    }

    @Test
    void list_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"data\":[{\"campaign_id\":\"cmp-1\",\"sender\":\"PLAYPICK\",\"camp_type\":\"sms\",\"status\":\"active\",\"total_recipients\":\"50\",\"created_at\":\"2025-06-01\"}],\"current_page\":1,\"last_page\":2,\"total\":2,\"per_page\":1,\"from\":1,\"to\":1}"));

        CampaignListResponse response = campaignService.list(1);

        assertNotNull(response);
        assertEquals(1, response.getCurrentPage());
        assertEquals(2, response.getLastPage());
        assertEquals(2, response.getTotal());
        assertEquals("cmp-1", response.getData().get(0).getCampaignId());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("GET", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/sms/campaigns"));
        assertTrue(recorded.getPath().contains("page=1"));
    }

    @Test
    void getHistory_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"data\":[{\"id\":\"1\",\"sender\":\"PLAYPICK\",\"receiver\":\"2348012345678\",\"message\":\"Your OTP is 1234\",\"amount\":\"1\",\"sms_type\":\"generic\",\"status\":\"Delivered\",\"message_id\":\"msg-1\",\"date_created\":\"2025-06-01\",\"last_updated\":\"2025-06-01\"}],\"current_page\":1,\"last_page\":1,\"total\":1,\"per_page\":50}"));

        CampaignHistoryResponse response = campaignService.getHistory("cmp-123", 1);

        assertNotNull(response);
        assertEquals(1, response.getTotal());
        assertEquals("PLAYPICK", response.getData().get(0).getSender());
        assertEquals("Delivered", response.getData().get(0).getStatus());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("GET", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/sms/campaigns/cmp-123"));
    }

    @Test
    void phonebook_ReturnsService() {
        PhonebookService pb = campaignService.phonebook();
        assertNotNull(pb);
        PhonebookService pb2 = campaignService.phonebook();
        assertEquals(pb, pb2);
    }
}
