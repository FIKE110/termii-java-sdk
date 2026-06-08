package io.github.fike110.termii.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Message delivery channels supported by Termii.
 */
public enum MessageChannel {
    GENERIC("generic"),
    DND("dnd"),
    WHATSAPP("whatsapp"),
    WHATSAPP_OTP("whatsapp_otp"),
    VOICE("voice");

    private final String value;

    MessageChannel(String value) {
        this.value = value;
    }

    /**
     * Returns the JSON value for this message channel.
     */
    @JsonValue
    public String getValue() {
        return value;
    }
}
