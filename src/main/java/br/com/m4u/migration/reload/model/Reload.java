package br.com.m4u.migration.reload.model;

/**
 * Created by sandro on 01/11/16.
 */
public abstract class Reload {

    private Double amount;
    private String channel;
    private String msisdn;

    public Reload() {
    }

    public Reload(Double amount, String channel, String msisdn) {
        this.amount = amount;
        this.channel = channel;
        this.msisdn = msisdn;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}
