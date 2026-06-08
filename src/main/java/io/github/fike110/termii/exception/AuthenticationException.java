package io.github.fike110.termii.exception;

/**
 * Thrown when authentication fails (HTTP 401).
 * <p>
 * Indicates the API key is missing, invalid, or expired.
 */
public class AuthenticationException extends TermiiException {

    public AuthenticationException(String message, int statusCode) {
        super(message, statusCode);
    }
}
