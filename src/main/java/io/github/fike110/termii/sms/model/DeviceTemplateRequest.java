package io.github.fike110.termii.sms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.fike110.termii.model.Media;

import java.util.Map;

/**
 * Request model for sending an SMS using a predefined device template.
 * <p>
 * Requires a phone number, device ID, template ID, and optional data placeholders
 * and media attachments. Use the {@link Builder} to construct.
 * </p>
 */
public class DeviceTemplateRequest {

    private final String phoneNumber;
    private final String deviceId;
    private final String templateId;
    private final Map<String, String> data;
    private final Media media;

    private DeviceTemplateRequest(Builder builder) {
        this.phoneNumber = builder.phoneNumber;
        this.deviceId = builder.deviceId;
        this.templateId = builder.templateId;
        this.data = builder.data;
        this.media = builder.media;
    }

    /**
     * Returns the recipient phone number.
     *
     * @return the destination phone number
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Returns the device ID to send from.
     *
     * @return the device identifier
     */
    @JsonProperty("device_id")
    public String getDeviceId() { return deviceId; }

    /**
     * Returns the template ID to use for the message.
     *
     * @return the template identifier
     */
    @JsonProperty("template_id")
    public String getTemplateId() { return templateId; }

    /**
     * Returns the data placeholders for the template.
     *
     * @return a map of placeholder keys to values, or {@code null} if not set
     */
    @JsonProperty("data")
    public Map<String, String> getData() { return data; }

    /**
     * Returns the optional media attachment.
     *
     * @return the media attachment, or {@code null} if not set
     */
    @JsonProperty("media")
    public Media getMedia() { return media; }

    /**
     * Creates a new {@link Builder} for constructing a {@link DeviceTemplateRequest}.
     *
     * @return a new builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String phoneNumber;
        private String deviceId;
        private String templateId;
        private Map<String, String> data;
        private Media media;

        /**
         * Sets the recipient phone number.
         *
         * @param phoneNumber the destination phone number
         * @return this builder
         */
        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Sets the device ID to send from.
         *
         * @param deviceId the device identifier
         * @return this builder
         */
        public Builder deviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        /**
         * Sets the template ID to use for the message.
         *
         * @param templateId the template identifier
         * @return this builder
         */
        public Builder templateId(String templateId) {
            this.templateId = templateId;
            return this;
        }

        /**
         * Sets the data placeholders for the template.
         *
         * @param data a map of placeholder keys to values
         * @return this builder
         */
        public Builder data(Map<String, String> data) {
            this.data = data;
            return this;
        }

        /**
         * Sets the optional media attachment.
         *
         * @param media the media attachment
         * @return this builder
         */
        public Builder media(Media media) {
            this.media = media;
            return this;
        }

        /**
         * Builds the {@link DeviceTemplateRequest} after validating required fields.
         *
         * @return a new DeviceTemplateRequest instance
         * @throws IllegalArgumentException if {@code phoneNumber}, {@code deviceId}, or
         *                                  {@code templateId} is blank
         */
        public DeviceTemplateRequest build() {
            if (phoneNumber == null || phoneNumber.isBlank()) {
                throw new IllegalArgumentException("phoneNumber is required");
            }
            if (deviceId == null || deviceId.isBlank()) {
                throw new IllegalArgumentException("deviceId is required");
            }
            if (templateId == null || templateId.isBlank()) {
                throw new IllegalArgumentException("templateId is required");
            }
            return new DeviceTemplateRequest(this);
        }
    }
}
