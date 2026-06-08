package io.github.fike110.termii.sms;

import io.github.fike110.termii.http.HttpClient;
import io.github.fike110.termii.model.Media;
import io.github.fike110.termii.model.enums.MessageChannel;
import io.github.fike110.termii.model.enums.SmsType;
import io.github.fike110.termii.sms.model.AutoNumberRequest;
import io.github.fike110.termii.sms.model.DeviceTemplateRequest;
import io.github.fike110.termii.sms.model.SmsBulkRequest;
import io.github.fike110.termii.sms.model.SmsRequest;
import io.github.fike110.termii.sms.model.SmsResponse;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SmsServiceTest {

    private MockWebServer server;
    private SmsService smsService;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        HttpClient httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-api-key"
        );
        smsService = new SmsService(httpClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void send_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"balance\":1047.57,\"message_id\":\"msg123\",\"message\":\"Successfully Sent\",\"user\":\"Tester\"}"));

        SmsRequest request = SmsRequest.builder()
                .to("2348012345678")
                .from("PLAYPICK")
                .sms("Hello, World!")
                .type(SmsType.PLAIN)
                .channel(MessageChannel.GENERIC)
                .build();

        SmsResponse response = smsService.send(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());
        assertEquals(BigDecimal.valueOf(1047.57), response.getBalance());
        assertEquals("msg123", response.getMessageId());
        assertEquals("Successfully Sent", response.getMessage());
        assertEquals("Tester", response.getUser());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/sms/send"));
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"api_key\":\"test-api-key\""));
        assertTrue(body.contains("\"to\":\"2348012345678\""));
        assertTrue(body.contains("\"from\":\"PLAYPICK\""));
        assertTrue(body.contains("\"sms\":\"Hello, World!\""));
    }

    @Test
    void send_WithMedia_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"balance\":100.0,\"message_id\":\"msg456\",\"message\":\"Successfully Sent\",\"user\":\"Tester\"}"));

        Media media = Media.builder()
                .url("https://example.com/image.jpg")
                .caption("Test image")
                .build();

        SmsRequest request = SmsRequest.builder()
                .to("2348012345678")
                .from("PLAYPICK")
                .sms("Check this out")
                .channel(MessageChannel.WHATSAPP)
                .media(media)
                .build();

        SmsResponse response = smsService.send(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());

        RecordedRequest recorded = server.takeRequest();
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"media\":{"));
        assertTrue(body.contains("\"url\":\"https://example.com/image.jpg\""));
        assertTrue(body.contains("\"caption\":\"Test image\""));
    }

    @Test
    void sendBulk_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"balance\":500.0,\"message_id\":\"bulk123\",\"message\":\"Successfully Sent\",\"user\":\"Tester\"}"));

        SmsBulkRequest request = SmsBulkRequest.builder()
                .to(List.of("2348012345678", "2348098765432"))
                .from("PLAYPICK")
                .sms("Bulk message")
                .type(SmsType.PLAIN)
                .channel(MessageChannel.DND)
                .build();

        SmsResponse response = smsService.sendBulk(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/sms/send/bulk"));
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"to\":[\"2348012345678\",\"2348098765432\"]"));
    }

    @Test
    void sendFromAutoNumber_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"message_id\":\"auto123\",\"message\":\"Successfully Sent\",\"balance\":8,\"user\":\"Tester\"}"));

        AutoNumberRequest request = AutoNumberRequest.builder()
                .to("2348012345678")
                .sms("Auto number test")
                .build();

        SmsResponse response = smsService.sendFromAutoNumber(request);

        assertNotNull(response);
        assertEquals("ok", response.getCode());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/sms/number/send"));
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"api_key\":\"test-api-key\""));
    }

    @Test
    void sendAsync_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"code\":\"ok\",\"balance\":100.0,\"message_id\":\"async123\",\"message\":\"Successfully Sent\",\"user\":\"Tester\"}"));

        SmsRequest request = SmsRequest.builder()
                .to("2348012345678")
                .from("PLAYPICK")
                .sms("Async test")
                .build();

        CompletableFuture<SmsResponse> future = smsService.sendAsync(request);
        SmsResponse response = future.get();

        assertNotNull(response);
        assertEquals("ok", response.getCode());
        assertEquals("async123", response.getMessageId());
    }

    @Test
    void sendDeviceTemplate_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"status\":\"success\",\"message\":\"Template sent successfully\"}"));

        DeviceTemplateRequest request = DeviceTemplateRequest.builder()
                .phoneNumber("2348012345678")
                .deviceId("device-123")
                .templateId("template-456")
                .data(Map.of("name", "John", "amount", "5000"))
                .build();

        var response = smsService.sendDeviceTemplate(request);

        assertNotNull(response);
        assertEquals("success", response.getStatus());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/send/template"));
    }
}
