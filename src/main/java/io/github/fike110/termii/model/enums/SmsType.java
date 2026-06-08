package io.github.fike110.termii.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Types of SMS messages supported by Termii.
 */
public enum SmsType {
    PLAIN("plain"),
    UNICODE("unicode"),
    ENCRYPTED("encrypted"),
    VOICE("voice");

    private final String value;

    SmsType(String value) {
        this.value = value;
    }

    /**
     * Returns the JSON value for this SMS type.
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}
