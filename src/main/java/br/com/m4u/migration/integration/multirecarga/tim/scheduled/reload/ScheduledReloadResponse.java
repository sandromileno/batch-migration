package br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sandro on 22/11/16.
 */
public class ScheduledReloadResponse {

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("periodicidade")
    private String periodicity;

    @JsonProperty("valor")
    private Integer amount;

    @JsonProperty("msisdnRecebedor")
    private String recipient;

    @JsonProperty("aniversario")
    private Long anniversary;

    public ScheduledReloadResponse() {
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(Long anniversary) {
        this.anniversary = anniversary;
    }
}
