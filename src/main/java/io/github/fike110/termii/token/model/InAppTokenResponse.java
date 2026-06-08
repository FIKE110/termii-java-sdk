package io.github.fike110.termii.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for in-app token generation.
 * <p>
 * Contains the generated OTP, PIN ID, and phone number information
 * from the Termii API.
 * </p>
 */
public class InAppTokenResponse {

    private String phoneNumberOther;
    private String phoneNumber;
    private String otp;
    private String pinId;

    /**
     * Returns an alternative phone number associated with the token.
     *
     * @return the alternative phone number
     */
    @JsonProperty("phone_number_other")
    public String getPhoneNumberOther() { return phoneNumberOther; }

    /**
     * Sets an alternative phone number associated with the token.
     *
     * @param phoneNumberOther the alternative phone number
     */
    @JsonProperty("phone_number_other")
    public void setPhoneNumberOther(String phoneNumberOther) { this.phoneNumberOther = phoneNumberOther; }

    /**
     * Returns the phone number for which the token was generated.
     *
     * @return the phone number
     */
    @JsonProperty("phone_number")
    public String getPhoneNumber() { return phoneNumber; }

    /**
     * Sets the phone number for which the token was generated.
     *
     * @param phoneNumber the phone number
     */
    @JsonProperty("phone_number")
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     * Returns the generated OTP code.
     *
     * @return the one-time password
     */
    @JsonProperty("otp")
    public String getOtp() { return otp; }

    /**
     * Sets the generated OTP code.
     *
     * @param otp the one-time password
     */
    @JsonProperty("otp")
    public void setOtp(String otp) { this.otp = otp; }

    /**
     * Returns the PIN ID associated with the generated token.
     *
     * @return the PIN identifier
     */
    @JsonProperty("pin_id")
    public String getPinId() { return pinId; }

    /**
     * Sets the PIN ID associated with the generated token.
     *
     * @param pinId the PIN identifier
     */
    @JsonProperty("pin_id")
    public void setPinId(String pinId) { this.pinId = pinId; }
}
