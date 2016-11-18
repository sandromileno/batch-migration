package br.com.m4u.migration.reload.processor;

import br.com.m4u.migration.integration.multirecarga.tim.customer.CustomerService;
import br.com.m4u.migration.integration.multirecarga.tim.customer.FindCustomerResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.CreateScheduledReloadResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.ScheduledReloadService;
import br.com.m4u.migration.reload.builder.ScheduledReloadBuilder;
import br.com.m4u.migration.reload.enums.MigrationStatusEnum;
import br.com.m4u.migration.reload.model.ScheduledReload;
import br.com.m4u.migration.reload.post.processor.ResponseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sandro on 01/11/16.
 */
public class ScheduledReloadItemProcessor implements ItemProcessor<ScheduledReload, ResponseProcessor> {

    private static final Logger log = LoggerFactory.getLogger(ScheduledReloadItemProcessor.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScheduledReloadService scheduledReloadService;

    @Override
    public ResponseProcessor process(ScheduledReload scheduledReload) throws Exception {
        log.info("Processando cliente {} com valor {} no canal {}", scheduledReload.getMsisdn(), scheduledReload.getAmount(), scheduledReload.getChannel());
        FindCustomerResponse customerResponse = customerService.findCustomer(scheduledReload.getMsisdn());
        ResponseProcessor responseProcessor;
        if (customerResponse.wasSuccessful()) {
            CreateScheduledReloadResponse createScheduledReloadResponse = scheduledReloadService.createScheduledReload(ScheduledReloadBuilder.build(customerResponse, scheduledReload), scheduledReload.getChannel());
            if (createScheduledReloadResponse.wasSuccessful()) {
                log.info("Cliente {} com valor {} no canal {} cadastrado com sucesso", scheduledReload.getMsisdn(), scheduledReload.getAmount(), scheduledReload.getChannel());
                responseProcessor = new ResponseProcessor(MigrationStatusEnum.MIGRATED.getStatus());
                responseProcessor = convertToResponseProcessor(createScheduledReloadResponse, responseProcessor);
            } else {
                responseProcessor = new ResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), createScheduledReloadResponse.getMessage());
                responseProcessor = convertToResponseProcessor(scheduledReload, responseProcessor);
            }
        } else {
            log.error("Cliente {} com valor {} no canal {} nao cadastrado", scheduledReload.getMsisdn(), scheduledReload.getAmount(), scheduledReload.getChannel());
            responseProcessor = new ResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), "Customer not registered");
            responseProcessor = convertToResponseProcessor(scheduledReload, responseProcessor);
        }
        return responseProcessor;
    }

    private ResponseProcessor convertToResponseProcessor(ScheduledReload scheduledReload, ResponseProcessor responseProcessor) {
        responseProcessor.setPeriodicity(scheduledReload.getPeriodicity());
        responseProcessor.setAmount(scheduledReload.getAmount());
        responseProcessor.setAnniversary(scheduledReload.getAnniversary());
        responseProcessor.setRecipient(scheduledReload.getMsisdn());
        return  responseProcessor;
    }

    private ResponseProcessor convertToResponseProcessor(CreateScheduledReloadResponse response, ResponseProcessor responseProcessor) {
        responseProcessor.setPeriodicity(response.getPeriodicity());
        responseProcessor.setAmount(response.getAmount());
        responseProcessor.setAnniversary(response.getAniversary());
        responseProcessor.setExternalId(response.getExternalId());
        responseProcessor.setRecipient(response.getRecipient());
        return  responseProcessor;
    }

}
