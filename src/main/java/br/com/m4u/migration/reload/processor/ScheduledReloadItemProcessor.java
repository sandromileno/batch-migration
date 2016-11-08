package br.com.m4u.migration.reload.processor;

import br.com.m4u.migration.integration.multirecarga.tim.customer.CustomerService;
import br.com.m4u.migration.integration.multirecarga.tim.customer.FindCustomerResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.ScheduledReloadService;
import br.com.m4u.migration.reload.builder.ScheduledReloadBuilder;
import br.com.m4u.migration.reload.enums.MigrationStatusEnum;
import br.com.m4u.migration.reload.model.ScheduledReload;
import br.com.m4u.migration.reload.post.processor.ResponseProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Created by sandro on 01/11/16.
 */
public class ScheduledReloadItemProcessor implements ItemProcessor<ScheduledReload, ResponseProcessor> {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScheduledReloadService scheduledReloadService;

    @Override
    public ResponseProcessor process(ScheduledReload scheduledReload) throws Exception {
        FindCustomerResponse customerResponse = customerService.findCustomer(scheduledReload.getMsisdn());
        ResponseProcessor responseProcessor;
        if (customerResponse.wasSuccessful()) {
            ResponseEntity createScheduledReloadResponse = scheduledReloadService.createScheduledReload(ScheduledReloadBuilder.build(customerResponse, scheduledReload), scheduledReload.getChannel());
            if (createScheduledReloadResponse.getStatusCode().is2xxSuccessful()) {
                responseProcessor = new ResponseProcessor(MigrationStatusEnum.MIGRATED.getStatus(), createScheduledReloadResponse.getBody().toString());
            } else {
                responseProcessor = new ResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), createScheduledReloadResponse.getBody().toString());
            }
        } else {
            responseProcessor = new ResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), "Customer not registered");
        }
        return responseProcessor;
    }
}
