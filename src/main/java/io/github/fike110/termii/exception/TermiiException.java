package io.github.fike110.termii.exception;

/**
 * Base exception for all Termii API errors.
 * <p>
 * Contains the HTTP status code returned by the API. Specific error types
 * ({@link AuthenticationException}, {@link ValidationException},
 * {@link RateLimitException}, {@link ApiException}) extend this class.
 */
public class TermiiException extends RuntimeException {

    private final int statusCode;

    /**
     * Creates a TermiiException with a detail message and HTTP status code.
     */
    public TermiiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    /**
     * Creates a TermiiException with a detail message, HTTP status code, and underlying cause.
     */
    public TermiiException(String message, int statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    /**
     * Returns the HTTP status code associated with this error.
     */
    public int getStatusCode() {
        return statusCode;
    }
}
