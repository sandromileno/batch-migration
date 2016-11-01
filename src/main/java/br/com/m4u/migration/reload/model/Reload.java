package br.com.m4u.migration.reload.model;

/**
 * Created by sandro on 01/11/16.
 */
public abstract class Reload {

    private Double valor;
    private String channel;
    private String msisdn;

    public Reload(Double valor, String channel, String msisdn) {
        this.valor = valor;
        this.channel = channel;
        this.msisdn = msisdn;
    }

}
