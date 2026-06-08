package io.github.fike110.termii.exception;

/**
 * Thrown for general API errors (non-401, non-422, non-429 status codes).
 * <p>
 * Covers server errors (5xx) and other client errors (4xx) not handled by
 * more specific exception types.
 */
public class ApiException extends TermiiException {

    public ApiException(String message, int statusCode) {
        super(message, statusCode);
    }
}
