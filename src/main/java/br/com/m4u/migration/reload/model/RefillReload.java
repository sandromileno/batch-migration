package br.com.m4u.migration.reload.model;

/**
 * Created by sandro on 01/11/16.
 */
public class RefillReload extends Reload {

    private Integer minimumBalance;
    private Integer times;
    private String documentNumber;

    public RefillReload(Double valor, String channel, String msisdn, Integer minimumBalance, Integer times, String documentNumber) {
        super(valor, channel, msisdn);
        this.minimumBalance = minimumBalance;
        this.times = times;
        this.documentNumber = documentNumber;
    }

}
