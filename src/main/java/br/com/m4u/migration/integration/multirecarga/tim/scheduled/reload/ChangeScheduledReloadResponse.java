package br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * Created by sandro on 22/11/16.
 */
public class ChangeScheduledReloadResponse {

    private HttpStatus statusCode;

    private String message;

    @JsonProperty("externalId")
    private String externalId;

    @JsonProperty("periodicidade")
    private String periodicity;

    @JsonProperty("valor")
    private Integer amount;

    @JsonProperty("msisdnRecebedor")
    private String recipient;

    @JsonProperty("aniversario")
    private Long aniversary;

    public ChangeScheduledReloadResponse() {
    }

    public ChangeScheduledReloadResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public boolean wasSuccessful() {
        return !StringUtils.isEmpty(externalId) && amount != 0;
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

    public Long getAniversary() {
        return aniversary;
    }

    public void setAniversary(Long aniversary) {
        this.aniversary = aniversary;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
