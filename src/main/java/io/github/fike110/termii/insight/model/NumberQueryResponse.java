package io.github.fike110.termii.insight.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Response model for the number insight query API.
 * <p>
 * Contains routing, country, and operator details for a queried phone number.
 * </p>
 */
public class NumberQueryResponse {

    private List<NumberQueryResult> result;
    private boolean status;
    private String message;

    /**
     * Returns the list of query results for the number.
     *
     * @return the query results
     */
    @JsonProperty("result")
    public List<NumberQueryResult> getResult() { return result; }

    @JsonProperty("result")
    public void setResult(List<NumberQueryResult> result) { this.result = result; }

    /**
     * Indicates whether the query was successful.
     *
     * @return true if successful, false otherwise
     */
    @JsonProperty("status")
    public boolean isStatus() { return status; }

    @JsonProperty("status")
    public void setStatus(boolean status) { this.status = status; }

    /**
     * Returns a message describing the query result.
     *
     * @return the message text
     */
    @JsonProperty("message")
    public String getMessage() { return message; }

    @JsonProperty("message")
    public void setMessage(String message) { this.message = message; }

    /**
     * Detailed result for a single number query containing route, country, and operator info.
     */
    public static class NumberQueryResult {
        private RouteDetail routeDetail;
        private CountryDetail countryDetail;
        private OperatorDetail operatorDetail;
        private int status;

        /**
         * Returns the route detail for the number.
         *
         * @return the route detail
         */
        @JsonProperty("routeDetail")
        public RouteDetail getRouteDetail() { return routeDetail; }

        @JsonProperty("routeDetail")
        public void setRouteDetail(RouteDetail routeDetail) { this.routeDetail = routeDetail; }

        /**
         * Returns the country detail for the number.
         *
         * @return the country detail
         */
        @JsonProperty("countryDetail")
        public CountryDetail getCountryDetail() { return countryDetail; }

        @JsonProperty("countryDetail")
        public void setCountryDetail(CountryDetail countryDetail) { this.countryDetail = countryDetail; }

        /**
         * Returns the operator detail for the number.
         *
         * @return the operator detail
         */
        @JsonProperty("operatorDetail")
        public OperatorDetail getOperatorDetail() { return operatorDetail; }

        @JsonProperty("operatorDetail")
        public void setOperatorDetail(OperatorDetail operatorDetail) { this.operatorDetail = operatorDetail; }

        /**
         * Returns the status code for this query result.
         *
         * @return the status code
         */
        @JsonProperty("status")
        public int getStatus() { return status; }

        @JsonProperty("status")
        public void setStatus(int status) { this.status = status; }
    }

    /**
     * Routing details for the queried phone number.
     */
    public static class RouteDetail {
        private String number;
        private int ported;

        /**
         * Returns the phone number.
         *
         * @return the phone number
         */
        @JsonProperty("number")
        public String getNumber() { return number; }

        @JsonProperty("number")
        public void setNumber(String number) { this.number = number; }

        /**
         * Indicates whether the number has been ported (1) or not (0).
         *
         * @return the ported status
         */
        @JsonProperty("ported")
        public int getPorted() { return ported; }

        @JsonProperty("ported")
        public void setPorted(int ported) { this.ported = ported; }
    }

    /**
     * Country details for the queried phone number.
     */
    public static class CountryDetail {
        private String countryCode;
        private String mobileCountryCode;
        private String iso;

        /**
         * Returns the country code (e.g. "NG").
         *
         * @return the country code
         */
        @JsonProperty("countryCode")
        public String getCountryCode() { return countryCode; }

        @JsonProperty("countryCode")
        public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

        /**
         * Returns the mobile country code (MCC).
         *
         * @return the mobile country code
         */
        @JsonProperty("mobileCountryCode")
        public String getMobileCountryCode() { return mobileCountryCode; }

        @JsonProperty("mobileCountryCode")
        public void setMobileCountryCode(String mobileCountryCode) { this.mobileCountryCode = mobileCountryCode; }

        /**
         * Returns the ISO code of the country.
         *
         * @return the ISO code
         */
        @JsonProperty("iso")
        public String getIso() { return iso; }

        @JsonProperty("iso")
        public void setIso(String iso) { this.iso = iso; }
    }

    /**
     * Operator details for the queried phone number.
     */
    public static class OperatorDetail {
        private String operatorCode;
        private String operatorName;
        private String mobileNumberCode;
        private String mobileRoutingCode;
        private String carrierIdentificationCode;
        private String lineType;

        /**
         * Returns the operator code.
         *
         * @return the operator code
         */
        @JsonProperty("operatorCode")
        public String getOperatorCode() { return operatorCode; }

        @JsonProperty("operatorCode")
        public void setOperatorCode(String operatorCode) { this.operatorCode = operatorCode; }

        /**
         * Returns the operator name.
         *
         * @return the operator name
         */
        @JsonProperty("operatorName")
        public String getOperatorName() { return operatorName; }

        @JsonProperty("operatorName")
        public void setOperatorName(String operatorName) { this.operatorName = operatorName; }

        /**
         * Returns the mobile number code (MNC).
         *
         * @return the mobile number code
         */
        @JsonProperty("mobileNumberCode")
        public String getMobileNumberCode() { return mobileNumberCode; }

        @JsonProperty("mobileNumberCode")
        public void setMobileNumberCode(String mobileNumberCode) { this.mobileNumberCode = mobileNumberCode; }

        /**
         * Returns the mobile routing code.
         *
         * @return the mobile routing code
         */
        @JsonProperty("mobileRoutingCode")
        public String getMobileRoutingCode() { return mobileRoutingCode; }

        @JsonProperty("mobileRoutingCode")
        public void setMobileRoutingCode(String mobileRoutingCode) { this.mobileRoutingCode = mobileRoutingCode; }

        /**
         * Returns the carrier identification code.
         *
         * @return the carrier identification code
         */
        @JsonProperty("carrierIdentificationCode")
        public String getCarrierIdentificationCode() { return carrierIdentificationCode; }

        @JsonProperty("carrierIdentificationCode")
        public void setCarrierIdentificationCode(String carrierIdentificationCode) { this.carrierIdentificationCode = carrierIdentificationCode; }

        /**
         * Returns the line type (e.g. "mobile", "fixed").
         *
         * @return the line type
         */
        @JsonProperty("lineType")
        public String getLineType() { return lineType; }

        @JsonProperty("lineType")
        public void setLineType(String lineType) { this.lineType = lineType; }
    }
}
