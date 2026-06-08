package io.github.fike110.termii.insight.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Response model for the account balance API.
 * <p>
 * Contains the application name, current balance, currency, and user identifier.
 * </p>
 */
public class BalanceResponse {

    private String application;
    private BigDecimal balance;
    private String currency;
    private String user;

    /**
     * Returns the name of the application associated with the API key.
     *
     * @return the application name
     */
    @JsonProperty("application")
    public String getApplication() { return application; }

    @JsonProperty("application")
    public void setApplication(String application) { this.application = application; }

    /**
     * Returns the current account balance.
     *
     * @return the balance amount
     */
    @JsonProperty("balance")
    public BigDecimal getBalance() { return balance; }

    @JsonProperty("balance")
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    /**
     * Returns the currency of the balance (e.g. "NGN").
     *
     * @return the currency code
     */
    @JsonProperty("currency")
    public String getCurrency() { return currency; }

    @JsonProperty("currency")
    public void setCurrency(String currency) { this.currency = currency; }

    /**
     * Returns the name of the user or account holder.
     *
     * @return the user name
     */
    @JsonProperty("user")
    public String getUser() { return user; }

    @JsonProperty("user")
    public void setUser(String user) { this.user = user; }
}
