package br.com.m4u.migration.reload.model;

/**
 * Created by sandro on 01/11/16.
 */
public class ScheduledReload extends Reload {

    private Long anniversary;
    private String periodicity;

    public ScheduledReload() {
        this.periodicity = "MENSAL";
    }

    public ScheduledReload(Integer valor, String channel, String msisdn, Long anniversary, String periodicity) {
        super(valor, channel, msisdn);
        this.anniversary = anniversary;
        this.periodicity = periodicity;
    }

    public Long getAnniversary() {
        return anniversary;
    }

    public void setAnniversary(Long anniversary) {
        this.anniversary = anniversary;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }
}
