package io.github.fike110.termii.insight.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response model for the DND (Do-Not-Disturb) check API.
 * <p>
 * Indicates whether a phone number is registered on the DND list along with network details.
 * </p>
 */
public class DndCheckResponse {

    private String number;
    private String networkCode;
    private boolean dndActive;
    private String message;
    private String status;
    private String network;

    /**
     * Returns the phone number that was checked.
     *
     * @return the phone number
     */
    @JsonProperty("number")
    public String getNumber() { return number; }

    @JsonProperty("number")
    public void setNumber(String number) { this.number = number; }

    /**
     * Returns the network code of the mobile operator.
     *
     * @return the network code
     */
    @JsonProperty("network_code")
    public String getNetworkCode() { return networkCode; }

    @JsonProperty("network_code")
    public void setNetworkCode(String networkCode) { this.networkCode = networkCode; }

    /**
     * Indicates whether the number is DND active.
     *
     * @return true if DND is active, false otherwise
     */
    @JsonProperty("dnd_active")
    public boolean isDndActive() { return dndActive; }

    @JsonProperty("dnd_active")
    public void setDndActive(boolean dndActive) { this.dndActive = dndActive; }

    /**
     * Returns a descriptive message about the DND status.
     *
     * @return the message text
     */
    @JsonProperty("message")
    public String getMessage() { return message; }

    @JsonProperty("message")
    public void setMessage(String message) { this.message = message; }

    /**
     * Returns the status of the DND check request.
     *
     * @return the status string
     */
    @JsonProperty("status")
    public String getStatus() { return status; }

    @JsonProperty("status")
    public void setStatus(String status) { this.status = status; }

    /**
     * Returns the network name of the mobile operator.
     *
     * @return the network name
     */
    @JsonProperty("network")
    public String getNetwork() { return network; }

    @JsonProperty("network")
    public void setNetwork(String network) { this.network = network; }
}
