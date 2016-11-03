package br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sandro on 03/11/16.
 */
public class CreateScheduledReloadRequest {

    @JsonProperty("periodicidade")
    private String periodicity;

    @JsonProperty("valor")
    private String value;

    @JsonProperty("msisdn")
    private String msisdn;

    @JsonProperty("msisdnRecebedor")
    private String recipient;

    @JsonProperty("aniversario")
    private Long birthday;

    @JsonProperty("token")
    private String token;

    public CreateScheduledReloadRequest() {
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
