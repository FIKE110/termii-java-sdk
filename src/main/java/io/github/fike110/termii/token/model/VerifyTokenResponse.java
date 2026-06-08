package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for token verification requests.
 * <p>
 * Contains the PIN ID, verification status, and the MSISDN
 * (mobile number) associated with the verification.
 * </p>
 */
public class VerifyTokenResponse {

    private String pinId;
    private String verified;
    private String msisdn;

    /**
     * Returns the PIN ID of the verified token.
     *
     * @return the PIN identifier
     */
    @JsonProperty("pinId")
    public String getPinId() { return pinId; }

    /**
     * Sets the PIN ID of the verified token.
     *
     * @param pinId the PIN identifier
     */
    @JsonProperty("pinId")
    public void setPinId(String pinId) { this.pinId = pinId; }

    /**
     * Returns the raw verification status string.
     *
     * @return {@code "true"} if verified, otherwise {@code "false"}
     */
    @JsonProperty("verified")
    public String getVerified() { return verified; }

    /**
     * Sets the raw verification status.
     *
     * @param verified {@code "true"} if verified, otherwise {@code "false"}
     */
    @JsonProperty("verified")
    public void setVerified(String verified) { this.verified = verified; }

    /**
     * Returns the MSISDN (mobile number) associated with this verification.
     *
     * @return the mobile number
     */
    @JsonProperty("msisdn")
    public String getMsisdn() { return msisdn; }

    /**
     * Sets the MSISDN (mobile number) associated with this verification.
     *
     * @param msisdn the mobile number
     */
    @JsonProperty("msisdn")
    public void setMsisdn(String msisdn) { this.msisdn = msisdn; }

    /**
     * Returns whether the token was successfully verified.
     *
     * @return {@code true} if the verification status is {@code "true"}
     */
    public boolean isVerified() {
        return "true".equalsIgnoreCase(verified);
    }
}
