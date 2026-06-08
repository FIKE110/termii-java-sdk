package io.github.fike110.termii.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * PIN type options for OTP token generation.
 */
public enum PinType {
    NUMERIC("NUMERIC"),
    ALPHANUMERIC("ALPHANUMERIC");

    private final String value;

    PinType(String value) {
        this.value = value;
    }

    /**
     * Returns the JSON value for this PIN type.
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}
