package br.com.m4u.migration.reload.builder;

import br.com.m4u.migration.integration.multirecarga.tim.customer.CreditCard;
import br.com.m4u.migration.integration.multirecarga.tim.customer.FindCustomerResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.CreateScheduledReloadRequest;
import br.com.m4u.migration.reload.model.ScheduledReload;

/**
 * Created by sandro on 07/11/16.
 */
public class ScheduledReloadBuilder {

    public static CreateScheduledReloadRequest build(FindCustomerResponse response, ScheduledReload scheduledReload) {
        CreateScheduledReloadRequest request = new CreateScheduledReloadRequest();
        request.setAnniversary(scheduledReload.getAnniversary());
        request.setMsisdn(response.getCustomer().getMsisdn());
        request.setRecipient(response.getCustomer().getMsisdn());
        request.setPeriodicity(scheduledReload.getPeriodicity());
        request.setValue(String.valueOf(scheduledReload.getAmount().intValue()));
        for (CreditCard creditCard : response.getCreditCards()) {
            if (creditCard.isFavourite()) {
                request.setToken(creditCard.getToken());
            }
        }
        return request;
    }
}