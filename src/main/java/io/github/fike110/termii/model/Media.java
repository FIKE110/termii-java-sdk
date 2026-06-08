package io.github.fike110.termii.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a media attachment for an SMS message.
 * <p>
 * Use {@link #builder()} to create a new instance.
 */
public class Media {

    private final String url;
    private final String caption;

    private Media(Builder builder) {
        this.url = builder.url;
        this.caption = builder.caption;
    }

    /**
     * Returns the URL of the media file.
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * Returns the caption for the media file.
     */
    @JsonProperty("caption")
    public String getCaption() {
        return caption;
    }

    /**
     * Creates a new {@link Builder} for constructing a {@link Media} instance.
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for constructing {@link Media} instances.
     */
    public static class Builder {
        private String url;
        private String caption;

        /**
         * Sets the URL of the media file.
         */
        public Builder url(String url) {
            this.url = url;
            return this;
        }

        /**
         * Sets the caption for the media file.
         */
        public Builder caption(String caption) {
            this.caption = caption;
            return this;
        }

        /**
         * Builds a new {@link Media} instance.
         */
        public Media build() {
            return new Media(this);
        }
    }
}
