package io.github.fike110.termii.http;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import io.github.fike110.termii.exception.ApiException;
import io.github.fike110.termii.exception.AuthenticationException;
import io.github.fike110.termii.exception.RateLimitException;
import io.github.fike110.termii.exception.TermiiException;
import io.github.fike110.termii.exception.ValidationException;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Internal HTTP client that handles communication with the Termii API.
 * <p>
 * Manages base URL resolution, authentication header injection, JSON
 * serialization/deserialization, and maps HTTP error responses to typed exceptions.
 */
public class HttpClient {

    private static final MediaType JSON = MediaType.parse("application/json");
    private static final String USER_AGENT = "termii-java-sdk/1.0.0-SNAPSHOT";

    private final OkHttpClient client;
    private final ObjectMapper mapper;
    private final String baseUrl;
    private final String apiKey;

    /**
     * Creates an HttpClient with default timeout values (30s connect, 60s read/write).
     */
    public HttpClient(String baseUrl, String apiKey) {
        this(baseUrl, apiKey, 30, 60, 60);
    }

    /**
     * Creates an HttpClient with custom timeout values.
     *
     * @param baseUrl        the Termii API base URL
     * @param apiKey         the API key for authentication
     * @param connectTimeout connect timeout in seconds
     * @param readTimeout    read timeout in seconds
     * @param writeTimeout   write timeout in seconds
     */
    public HttpClient(String baseUrl, String apiKey,
                      long connectTimeout, long readTimeout, long writeTimeout) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
        this.apiKey = apiKey;
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .build();
        this.mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    /**
     * Sends a POST request to the given path.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param body         the request body object
     * @param responseType the expected response class
     * @return the deserialized response object
     */
    public <T> T post(String path, Object body, Class<T> responseType) {
        return executeSync("POST", path, body, responseType);
    }

    /**
     * Sends a PATCH request to the given path.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param body         the request body object
     * @param responseType the expected response class
     * @return the deserialized response object
     */
    public <T> T patch(String path, Object body, Class<T> responseType) {
        return executeSync("PATCH", path, body, responseType);
    }

    /**
     * Sends a GET request to the given path with optional query parameters.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param queryParams  optional query parameters (may be null)
     * @param responseType the expected response class
     * @return the deserialized response object
     */
    public <T> T get(String path, Map<String, String> queryParams, Class<T> responseType) {
        return executeSync("GET", path, queryParams, responseType);
    }

    /**
     * Sends a DELETE request to the given path.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param responseType the expected response class
     * @return the deserialized response object
     */
    public <T> T delete(String path, Class<T> responseType) {
        return executeSync("DELETE", path, null, responseType);
    }

    /**
     * Returns the base URL used by this client.
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * Returns the API key used for authentication.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Returns the underlying OkHttpClient instance for advanced use.
     */
    public OkHttpClient getOkClient() {
        return client;
    }

    /**
     * Sends an asynchronous POST request.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param body         the request body object
     * @param responseType the expected response class
     * @return a future that completes with the deserialized response
     */
    public <T> CompletableFuture<T> postAsync(String path, Object body, Class<T> responseType) {
        return CompletableFuture.supplyAsync(() -> post(path, body, responseType));
    }

    /**
     * Sends an asynchronous PATCH request.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param body         the request body object
     * @param responseType the expected response class
     * @return a future that completes with the deserialized response
     */
    public <T> CompletableFuture<T> patchAsync(String path, Object body, Class<T> responseType) {
        return CompletableFuture.supplyAsync(() -> patch(path, body, responseType));
    }

    /**
     * Sends an asynchronous GET request.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param queryParams  optional query parameters (may be null)
     * @param responseType the expected response class
     * @return a future that completes with the deserialized response
     */
    public <T> CompletableFuture<T> getAsync(String path, Map<String, String> queryParams, Class<T> responseType) {
        return CompletableFuture.supplyAsync(() -> get(path, queryParams, responseType));
    }

    /**
     * Sends an asynchronous DELETE request.
     *
     * @param <T>          the response type
     * @param path         the API endpoint path
     * @param responseType the expected response class
     * @return a future that completes with the deserialized response
     */
    public <T> CompletableFuture<T> deleteAsync(String path, Class<T> responseType) {
        return CompletableFuture.supplyAsync(() -> delete(path, responseType));
    }

    @SuppressWarnings("unchecked")
    private <T> T executeSync(String method, String path, Object data, Class<T> responseType) {
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(baseUrl + path).newBuilder();

            RequestBody requestBody = null;
            if ("POST".equals(method) || "PATCH".equals(method)) {
                Map<String, Object> bodyMap = convertToMap(data);
                bodyMap.put("api_key", apiKey);
                String json = mapper.writeValueAsString(bodyMap);
                requestBody = RequestBody.create(json, JSON);
            } else if ("GET".equals(method) || "DELETE".equals(method)) {
                urlBuilder.addQueryParameter("api_key", apiKey);
                if (data instanceof Map) {
                    ((Map<String, String>) data).forEach(urlBuilder::addQueryParameter);
                }
            }

            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .method(method, requestBody)
                    .addHeader("User-Agent", USER_AGENT)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                int statusCode = response.code();
                String responseBody = response.body() != null ? response.body().string() : "";

                if (statusCode >= 200 && statusCode < 300) {
                    if (responseType == String.class) {
                        return (T) responseBody;
                    }
                    if (responseBody.isEmpty() || responseBody.isBlank()) {
                        return null;
                    }
                    return mapper.readValue(responseBody, responseType);
                }

                throw mapError(statusCode, responseBody);
            }
        } catch (IOException e) {
            throw new TermiiException("HTTP request failed: " + e.getMessage(), 0, e);
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> convertToMap(Object object) {
        return mapper.convertValue(object, Map.class);
    }

    private TermiiException mapError(int statusCode, String responseBody) {
        String message = "HTTP " + statusCode;
        if (responseBody != null && !responseBody.isEmpty()) {
            try {
                var node = mapper.readTree(responseBody);
                if (node.has("message")) {
                    message = node.get("message").asText();
                } else if (node.has("error")) {
                    message = node.get("error").asText();
                }
            } catch (JsonProcessingException ignored) {
                message = responseBody.length() < 200 ? responseBody : message;
            }
        }

        return switch (statusCode) {
            case 401 -> new AuthenticationException(message, statusCode);
            case 422 -> new ValidationException(message, statusCode);
            case 429 -> new RateLimitException(message, statusCode);
            default -> new ApiException(message, statusCode);
        };
    }
}
