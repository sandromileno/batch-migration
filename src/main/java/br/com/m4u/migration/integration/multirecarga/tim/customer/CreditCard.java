package br.com.m4u.migration.integration.multirecarga.tim.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sandro on 04/11/16.
 */
public class CreditCard {

    @JsonProperty("tokenGWPagamento")
    private String token;

    @JsonProperty("favorito")
    private boolean favourite;

    public CreditCard() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
