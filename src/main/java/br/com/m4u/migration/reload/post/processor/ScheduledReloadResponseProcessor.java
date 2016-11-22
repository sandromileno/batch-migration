package br.com.m4u.migration.reload.post.processor;

/**
 * Created by sandro on 07/11/16.
 */
public class ScheduledReloadResponseProcessor {

    private String status;
    private String externalId;
    private String periodicity;
    private Integer amount;
    private String recipient;
    private Long anniversary;
    private String responseBody;

    public ScheduledReloadResponseProcessor() {
    }

    public ScheduledReloadResponseProcessor(String status, String responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }

    public ScheduledReloadResponseProcessor(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
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