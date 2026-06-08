package io.github.fike110.termii.campaign;

import io.github.fike110.termii.campaign.model.AddContactRequest;
import io.github.fike110.termii.campaign.model.ContactEntry;
import io.github.fike110.termii.campaign.model.CreatePhonebookRequest;
import io.github.fike110.termii.campaign.model.PhonebookEntry;
import io.github.fike110.termii.http.HttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PhonebookServiceTest {

    private MockWebServer server;
    private PhonebookService phonebookService;

    @BeforeEach
    void setUp() {
        server = new MockWebServer();
        HttpClient httpClient = new HttpClient(
                server.url("/").toString().replaceAll("/$", ""),
                "test-api-key"
        );
        phonebookService = new PhonebookService(httpClient);
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

    @Test
    void list_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"id\":\"pb-1\",\"name\":\"Customers\",\"description\":\"VIP customers\",\"total_number_of_contacts\":15,\"date_created\":\"2025-01-01\",\"last_updated\":\"2025-06-01\"}]"));

        List<PhonebookEntry> result = phonebookService.list(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Customers", result.get(0).getName());
        assertEquals(15, result.get(0).getTotalNumberOfContacts());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("GET", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/phonebooks"));
        assertTrue(recorded.getPath().contains("page=1"));
    }

    @Test
    void listAsync_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"id\":\"pb-2\",\"name\":\"Leads\",\"description\":\"Sales leads\",\"total_number_of_contacts\":5}]"));

        CompletableFuture<List<PhonebookEntry>> future = phonebookService.listAsync(1);
        List<PhonebookEntry> result = future.get();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Leads", result.get(0).getName());
    }

    @Test
    void create_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\":\"pb-new\",\"name\":\"New Book\",\"description\":\"New phonebook\",\"total_number_of_contacts\":0}"));

        CreatePhonebookRequest request = CreatePhonebookRequest.builder()
                .phonebookName("New Book")
                .description("New phonebook")
                .build();

        PhonebookEntry entry = phonebookService.create(request);

        assertNotNull(entry);
        assertEquals("pb-new", entry.getId());
        assertEquals("New Book", entry.getName());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/phonebooks"));
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"phonebook_name\":\"New Book\""));
        assertTrue(body.contains("\"api_key\":\"test-api-key\""));
    }

    @Test
    void update_Success() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));

        CreatePhonebookRequest request = CreatePhonebookRequest.builder()
                .phonebookName("Updated Book")
                .description("Updated desc")
                .build();

        phonebookService.update("pb-1", request);

        RecordedRequest recorded = server.takeRequest();
        assertEquals("PATCH", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/phonebooks/pb-1"));
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"api_key\":\"test-api-key\""));
    }

    @Test
    void delete_Success() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(204));

        phonebookService.delete("pb-1");

        RecordedRequest recorded = server.takeRequest();
        assertEquals("DELETE", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/phonebooks/pb-1"));
        assertTrue(recorded.getPath().contains("api_key=test-api-key"));
    }

    @Test
    void getContacts_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("[{\"id\":1,\"phone_number\":\"2348012345678\",\"email_address\":\"test@example.com\",\"first_name\":\"John\",\"last_name\":\"Doe\",\"company\":\"Acme\",\"country_code\":\"NG\"}]"));

        List<ContactEntry> contacts = phonebookService.getContacts("pb-1", 1);

        assertNotNull(contacts);
        assertEquals(1, contacts.size());
        assertEquals("2348012345678", contacts.get(0).getPhoneNumber());
        assertEquals("John", contacts.get(0).getFirstName());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("GET", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/phonebooks/pb-1/contacts"));
    }

    @Test
    void addContact_Success() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody("{\"id\":42,\"phone_number\":\"2348012345678\",\"first_name\":\"Jane\"}"));

        AddContactRequest request = AddContactRequest.builder()
                .phoneNumber("2348012345678")
                .firstName("Jane")
                .lastName("Doe")
                .company("Acme")
                .build();

        ContactEntry entry = phonebookService.addContact("pb-1", request);

        assertNotNull(entry);
        assertEquals(42, entry.getId());
        assertEquals("2348012345678", entry.getPhoneNumber());

        RecordedRequest recorded = server.takeRequest();
        assertEquals("POST", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/phonebooks/pb-1/contacts"));
        String body = recorded.getBody().readUtf8();
        assertTrue(body.contains("\"api_key\":\"test-api-key\""));
    }

    @Test
    void deleteContact_Success() throws Exception {
        server.enqueue(new MockResponse().setResponseCode(204));

        phonebookService.deleteContact("contact-42");

        RecordedRequest recorded = server.takeRequest();
        assertEquals("DELETE", recorded.getMethod());
        assertTrue(recorded.getPath().contains("/api/phonebook/contact/contact-42"));
        assertTrue(recorded.getPath().contains("api_key=test-api-key"));
    }
}
