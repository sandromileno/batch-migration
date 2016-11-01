package br.com.m4u.migration.reload.model;

/**
 * Created by sandro on 01/11/16.
 */
public class ScheduledReload extends Reload {

    private Long anniversary;
    private String periodicity;

    public ScheduledReload(Double valor, String channel, String msisdn, Long anniversary, String periodicity) {
        super(valor, channel, msisdn);
        this.anniversary = anniversary;
        this.periodicity = periodicity;
        this.periodicity = "MONTHLY";
    }

}
