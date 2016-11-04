package br.com.m4u.migration.integration.multirecarga.tim.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sandro on 04/11/16.
 */
public class FindCustomerResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("cliente")
    private Customer customer;

    @JsonProperty("cartaoDeCredito")
    private List<CreditCard> creditCards;

    public FindCustomerResponse() {
    }

    public CreditCard getFavouriteCreditCard() {

        for(CreditCard creditCard : creditCards) {
            if (creditCard.isFavourite()) {
                return creditCard;
            }
        }

        return new CreditCard();
    }

    public boolean wasSuccessful() {
        return "OK".equals(status);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
