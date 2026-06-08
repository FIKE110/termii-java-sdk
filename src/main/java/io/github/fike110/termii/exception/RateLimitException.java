package io.github.fike110.termii.exception;

/**
 * Thrown when the API rate limit has been exceeded (HTTP 429).
 * <p>
 * Retry the request after the time specified in the API response headers.
 */
public class RateLimitException extends TermiiException {

    public RateLimitException(String message, int statusCode) {
        super(message, statusCode);
    }
}
