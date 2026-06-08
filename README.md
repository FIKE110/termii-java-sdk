# Termii Java SDK

A strongly-typed, Stripe-style Java SDK for the [Termii](https://termii.com) messaging API. Send SMS, OTP tokens, and more with a clean builder pattern.

## Requirements

- Java 17+
- Maven 3.6+

## Installation

Add to your `pom.xml`:

```xml
<dependency>
  <groupId>io.github.fike110.termii</groupId>
  <artifactId>termii-java-sdk</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Quick Start

```java
TermiiClient client = TermiiClient.builder()
    .apiKey("YOUR_API_KEY")
    .build();

SmsResponse response = client.sms().send(
    SmsRequest.builder()
        .to("2348012345678")
        .from("PLAYPICK")
        .sms("Hello from Termii!")
        .build()
);

System.out.println(response.getMessageId());
```

## Services

| Client Method   | Service            | Domain                        |
|-----------------|--------------------|-------------------------------|
| `client.sms()`  | `SmsService`       | Send SMS, bulk, auto-number   |
| `client.token()`| `TokenService`     | OTP tokens, verify, voice, email |
| `client.senderId()` | `SenderIdService` | List & request sender IDs     |
| `client.insight()`  | `InsightService`  | Balance, DND check, number query, history |
| `client.email()`    | `EmailService`    | Email OTP and templated emails |
| `client.campaign()` | `CampaignService` | Campaigns + phonebooks        |

---

## SMS

### Send a single message

```java
SmsResponse response = client.sms().send(
    SmsRequest.builder()
        .to("2348012345678")
        .from("PLAYPICK")
        .sms("Your OTP is 123456")
        .type(SmsType.PLAIN)
        .channel(MessageChannel.DND)
        .build()
);

String messageId = response.getMessageId();
```

### Send via WhatsApp with media

```java
SmsResponse response = client.sms().send(
    SmsRequest.builder()
        .to("2348012345678")
        .from("talert")
        .sms("Check this out")
        .channel(MessageChannel.WHATSAPP)
        .media(Media.builder()
            .url("https://example.com/image.jpg")
            .caption("Product photo")
            .build())
        .build()
);
```

### Send bulk message

```java
SmsResponse response = client.sms().sendBulk(
    SmsBulkRequest.builder()
        .to(List.of("2348012345678", "2348098765432"))
        .from("PLAYPICK")
        .sms("Bulk promotion message")
        .channel(MessageChannel.GENERIC)
        .build()
);
```

### Send from auto-generated number

```java
SmsResponse response = client.sms().sendFromAutoNumber(
    AutoNumberRequest.builder()
        .to("2348012345678")
        .sms("Message from auto number")
        .build()
);
```

### Send WhatsApp template

```java
var response = client.sms().sendDeviceTemplate(
    DeviceTemplateRequest.builder()
        .phoneNumber("2348012345678")
        .deviceId("device-abc123")
        .templateId("template-xyz789")
        .data(Map.of("name", "John", "amount", "5000"))
        .build()
);
```

---

## Token / OTP

### Send OTP via SMS

```java
TokenResponse response = client.token().send(
    TokenRequest.builder()
        .to("2348012345678")
        .from("PLAYPICK")
        .messageText("Your pin is < 123456 >")
        .pinLength(6)
        .pinType(PinType.NUMERIC)
        .channel(MessageChannel.DND)
        .build()
);

String pinId = response.getPinId();
```

### Verify OTP

```java
VerifyTokenResponse result = client.token().verify(
    VerifyTokenRequest.builder()
        .pinId("b1f2242a-44c5-4eed-94bc-8d37b67ef219")
        .pin("123456")
        .build()
);

if (result.isVerified()) {
    // OTP confirmed
}
```

### Generate in-app token (OTP returned in response, not sent)

```java
InAppTokenResponse token = client.token().inApp(
    InAppTokenRequest.builder()
        .phoneNumber("2348012345678")
        .pinLength(4)
        .build()
);

String otp = token.getOtp();
```

### Voice token (OTP read via phone call)

```java
TokenResponse response = client.token().voiceToken(
    VoiceTokenRequest.builder()
        .phoneNumber("2348012345678")
        .pinLength(6)
        .build()
);
```

### Voice call (custom numeric code)

```java
TokenResponse response = client.token().voiceCall(
    VoiceCallRequest.builder()
        .phoneNumber("2348012345678")
        .code("55675")
        .build()
);
```

### Email OTP

```java
TokenResponse response = client.token().email(
    EmailTokenRequest.builder()
        .emailAddress("user@example.com")
        .code("092471")
        .emailConfigurationId("cfg-abc123")
        .build()
);
```

---

## Sender ID

### List sender IDs

```java
ListSenderIdsResponse ids = client.senderId().list();
// or with pagination:
ListSenderIdsResponse ids = client.senderId().list(2);

ids.getContent().forEach(entry -> {
    System.out.println(entry.getSenderId() + " - " + entry.getStatus());
});
```

### Request a new sender ID

```java
RequestSenderIdResponse result = client.senderId().request(
    RequestSenderIdRequest.builder()
        .senderId("YOURBRAND")
        .useCase("OTP verification messages")
        .company("Your Company Ltd")
        .build()
);
```

---

## Insight

### Get account balance

```java
BalanceResponse balance = client.insight().getBalance();
System.out.println(balance.getBalance() + " " + balance.getCurrency());
```

### Check DND status

```java
DndCheckResponse dnd = client.insight().checkDnd("2348012345678");

if (dnd.isDndActive()) {
    // Number is on DND, use dnd channel
}
```

### Query number porting status

```java
NumberQueryResponse info = client.insight()
    .queryNumber("2348012345678", "NG");

String operator = info.getResult().get(0)
    .getOperatorDetail().getOperatorName();
```

### Get message history

```java
List<HistoryResponse> history = client.insight().getHistory();

// By message ID:
List<HistoryResponse> history = client.insight()
    .getHistoryByMessageId("msg-abc123");
```

---

## Email

### Send OTP via email

```java
TokenResponse response = client.email().sendOtp(
    EmailTokenRequest.builder()
        .emailAddress("user@example.com")
        .code("092471")
        .emailConfigurationId("cfg-abc123")
        .build()
);
```

### Send templated email notification

```java
TokenResponse response = client.email().sendTemplated(
    EmailTemplatedRequest.builder()
        .email("user@example.com")
        .subject("Your Account Balance")
        .emailConfigurationId("cfg-abc123")
        .templateId("tmpl-xyz789")
        .variables(Map.of("name", "Jane", "balance", "5000"))
        .build()
);
```

---

## Campaign & Phonebook

### Send a campaign

```java
CampaignListResponse result = client.campaign().send(
    CampaignRequest.builder()
        .senderId("PLAYPICK")
        .phonebookId("pb-123")
        .campaignType("sms")
        .message("Campaign message here")
        .channel(MessageChannel.GENERIC)
        .build()
);
```

### List campaigns

```java
CampaignListResponse campaigns = client.campaign().list(1);
```

### Get campaign history

```java
CampaignHistoryResponse history = client.campaign()
    .getHistory("cmp-123", 1);
```

### List phonebooks

```java
List<PhonebookEntry> books = client.campaign()
    .phonebook()
    .list(1);
```

### Create a phonebook

```java
PhonebookEntry book = client.campaign()
    .phonebook()
    .create(CreatePhonebookRequest.builder()
        .phonebookName("VIP Customers")
        .description("Our best customers")
        .build());
```

### Manage contacts

```java
// Add a contact
ContactEntry contact = client.campaign().phonebook()
    .addContact("pb-123", AddContactRequest.builder()
        .phoneNumber("2348012345678")
        .firstName("John")
        .lastName("Doe")
        .emailAddress("john@example.com")
        .company("Acme")
        .build());

// List contacts
List<ContactEntry> contacts = client.campaign().phonebook()
    .getContacts("pb-123", 1);

// Delete a contact
client.campaign().phonebook().deleteContact("contact-42");
```

---

## Async / Non-blocking

Every service method has an `*Async()` variant returning `CompletableFuture`:

```java
CompletableFuture<SmsResponse> future = client.sms().sendAsync(request);
SmsResponse response = future.get(); // blocks

// Or chain:
client.sms().sendAsync(request)
    .thenAccept(resp -> System.out.println(resp.getMessageId()))
    .exceptionally(err -> { System.err.println(err); return null; });
```

Available for all services: `sms`, `token`, `senderId`, `insight`, `email`, `campaign`, `phonebook`.

---

## Error Handling

The SDK throws typed exceptions for every failure:

| Exception              | HTTP Status | When                 |
|------------------------|-------------|----------------------|
| `AuthenticationException` | 401     | Invalid API key      |
| `ValidationException`     | 422     | Invalid parameters   |
| `RateLimitException`      | 429     | Rate limit exceeded  |
| `ApiException`            | 4xx/5xx | All other errors     |
| `TermiiException`         | —       | Base exception / network error |

```java
try {
    SmsResponse response = client.sms().send(request);
} catch (AuthenticationException e) {
    System.err.println("Check your API key: " + e.getMessage());
} catch (ValidationException e) {
    System.err.println("Invalid request: " + e.getMessage());
} catch (RateLimitException e) {
    Thread.sleep(1000);
    // retry
}
```

---

## Configuration

```java
TermiiClient client = TermiiClient.builder()
    .apiKey("YOUR_API_KEY")
    .baseUrl("https://api.termii.com") // optional, defaults to this
    .build();
```

Your API key is obtained from the [Termii Dashboard](https://app.termii.com) → Settings → API Token.

---

## Models

All request models use the **Builder pattern**:

```java
SmsRequest request = SmsRequest.builder()
    .to("2348012345678")
    .from("PLAYPICK")
    .sms("Hello")
    .type(SmsType.PLAIN)         // optional, defaults to PLAIN
    .channel(MessageChannel.GENERIC) // optional, defaults to GENERIC
    .build();                    // validates required fields
```

Response models use standard Java getters:

```java
response.getMessageId();   // String
response.getBalance();     // BigDecimal
response.getCode();        // String
```

Available enums:

| Enum           | Values                                          |
|----------------|--------------------------------------------------|
| `SmsType`      | `PLAIN`, `UNICODE`, `ENCRYPTED`, `VOICE`          |
| `MessageChannel` | `GENERIC`, `DND`, `WHATSAPP`, `WHATSAPP_OTP`, `VOICE` |
| `PinType`      | `NUMERIC`, `ALPHANUMERIC`                         |

---

## Structure

```
io.github.fike110.termii
├── TermiiClient             # Entry point with Builder
├── http/HttpClient          # HTTP layer (OkHttp)
├── exception/               # Typed exception hierarchy
├── model/                   # Shared enums and models
│   ├── enums/
│   └── Media.java
├── sms/                     # SmsService + models
├── token/                   # TokenService + models
├── senderid/                # SenderIdService + models
├── insight/                 # InsightService + models
├── email/                   # EmailService + models
└── campaign/                # CampaignService + PhonebookService
```

## Testing

```bash
mvn clean test
```

Uses JUnit 5 + OkHttp MockWebServer. No network calls occur during tests.

## License

MIT
