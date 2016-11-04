package br.com.m4u.migration.integration.multirecarga.tim.customer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by sandro on 04/11/16.
 */
public class Customer {

    @JsonProperty("msisdn")
    private String msisdn;

    public Customer() {
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}
