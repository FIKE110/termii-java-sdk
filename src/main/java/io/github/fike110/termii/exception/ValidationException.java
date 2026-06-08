package io.github.fike110.termii.exception;

/**
 * Thrown when request validation fails (HTTP 422).
 * <p>
 * Indicates the request payload contains invalid, missing, or malformed fields.
 */
public class ValidationException extends TermiiException {

    public ValidationException(String message, int statusCode) {
        super(message, statusCode);
    }
}
