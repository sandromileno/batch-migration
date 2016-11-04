package br.com.m4u.migration.reload.processor;

import br.com.m4u.migration.integration.multirecarga.tim.customer.CustomerService;
import br.com.m4u.migration.integration.multirecarga.tim.customer.FindCustomerResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.ScheduledReloadService;
import br.com.m4u.migration.reload.model.ScheduledReload;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sandro on 01/11/16.
 */
public class ScheduledReloadItemProcessor implements ItemProcessor<ScheduledReload, ScheduledReload> {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScheduledReloadService scheduledReloadService;

    @Override
    public ScheduledReload process(ScheduledReload scheduledReload) throws Exception {
        FindCustomerResponse customerResponse = customerService.findCustomer(scheduledReload.getMsisdn());
        if (customerResponse.wasSuccessful()) {
            scheduledReloadService.createScheduledReload(null);
        } else {

        }
        return scheduledReload;
    }
}
