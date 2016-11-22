package br.com.m4u.migration.reload.processor;

import br.com.m4u.migration.integration.multirecarga.tim.customer.CustomerService;
import br.com.m4u.migration.integration.multirecarga.tim.customer.FindCustomerResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.ChangeScheduledReloadResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.CreateScheduledReloadResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.FindScheduledReloadResponse;
import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.ScheduledReloadService;
import br.com.m4u.migration.reload.builder.ScheduledReloadBuilder;
import br.com.m4u.migration.reload.enums.MigrationStatusEnum;
import br.com.m4u.migration.reload.model.ScheduledReload;
import br.com.m4u.migration.reload.post.processor.ScheduledReloadResponseProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by sandro on 01/11/16.
 */
public class ScheduledReloadItemProcessor implements ItemProcessor<ScheduledReload, ScheduledReloadResponseProcessor> {

    private static final Logger log = LoggerFactory.getLogger(ScheduledReloadItemProcessor.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScheduledReloadService scheduledReloadService;

    @Override
    public ScheduledReloadResponseProcessor process(ScheduledReload scheduledReload) throws Exception {
        log.info("Processando cliente {} com valor {} no canal {}", scheduledReload.getMsisdn(), scheduledReload.getAmount(), scheduledReload.getChannel());
        log.info("Iniciando busca do cliente {} no multirecarga", scheduledReload.getMsisdn());
        FindCustomerResponse customerResponse = customerService.findCustomer(scheduledReload.getMsisdn());
        log.info("Resposta da busca do cliente {} no multirecarga - encontrado {}", scheduledReload.getMsisdn(), customerResponse.wasSuccessful());
        ScheduledReloadResponseProcessor scheduledReloadResponseProcessor = null;
        if (customerResponse.wasSuccessful()) {
            FindScheduledReloadResponse findScheduledReloadResponse = scheduledReloadService.findScheduledReload(scheduledReload.getMsisdn(), scheduledReload.getChannel());
            if (findScheduledReloadResponse.hasSheduledReload()) {
                if (findScheduledReloadResponse.wasChanged(scheduledReload)) {
                    log.info("Cliente {} com alteracao de recarga programada", scheduledReload.getMsisdn());
                    ChangeScheduledReloadResponse changeResponse = scheduledReloadService.changeScheduledReload(ScheduledReloadBuilder.build(findScheduledReloadResponse, scheduledReload, customerResponse.getFavouriteCreditCard().getToken()), scheduledReload.getChannel());
                    if (changeResponse.wasSuccessful()) {
                        scheduledReloadResponseProcessor = new ScheduledReloadResponseProcessor(MigrationStatusEnum.MIGRATED.getStatus());
                        scheduledReloadResponseProcessor = convertToResponseProcessor(changeResponse, scheduledReloadResponseProcessor);
                    } else {
                        scheduledReloadResponseProcessor = new ScheduledReloadResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), changeResponse.getMessage());
                        scheduledReloadResponseProcessor = convertToResponseProcessor(scheduledReload, scheduledReloadResponseProcessor);
                    }
                } else {
                    scheduledReloadResponseProcessor = new ScheduledReloadResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), "Customer already has scheduled reload");
                }
            } else {
                log.info("Iniciando cadastro do cliente {} no pegasus", scheduledReload.getMsisdn());
                CreateScheduledReloadResponse createScheduledReloadResponse = scheduledReloadService.createScheduledReload(ScheduledReloadBuilder.build(customerResponse, scheduledReload), scheduledReload.getChannel());
                if (createScheduledReloadResponse.wasSuccessful()) {
                    log.info("Cliente {} com valor {} no canal {} cadastrado com sucesso", scheduledReload.getMsisdn(), scheduledReload.getAmount(), scheduledReload.getChannel());
                    scheduledReloadResponseProcessor = new ScheduledReloadResponseProcessor(MigrationStatusEnum.MIGRATED.getStatus());
                    scheduledReloadResponseProcessor = convertToResponseProcessor(createScheduledReloadResponse, scheduledReloadResponseProcessor);
                } else {
                    scheduledReloadResponseProcessor = new ScheduledReloadResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), createScheduledReloadResponse.getMessage());
                    scheduledReloadResponseProcessor = convertToResponseProcessor(scheduledReload, scheduledReloadResponseProcessor);
                }
            }
        } else {
            log.error("Cliente {} com valor {} no canal {} nao cadastrado", scheduledReload.getMsisdn(), scheduledReload.getAmount(), scheduledReload.getChannel());
            scheduledReloadResponseProcessor = new ScheduledReloadResponseProcessor(MigrationStatusEnum.NOT_MIGRATED.getStatus(), "Customer not registered");
            scheduledReloadResponseProcessor = convertToResponseProcessor(scheduledReload, scheduledReloadResponseProcessor);
        }
        return scheduledReloadResponseProcessor;
    }

    private ScheduledReloadResponseProcessor convertToResponseProcessor(ScheduledReload scheduledReload, ScheduledReloadResponseProcessor scheduledReloadResponseProcessor) {
        scheduledReloadResponseProcessor.setPeriodicity(scheduledReload.getPeriodicity());
        scheduledReloadResponseProcessor.setAmount(scheduledReload.getAmount());
        scheduledReloadResponseProcessor.setAnniversary(scheduledReload.getAnniversary());
        scheduledReloadResponseProcessor.setRecipient(scheduledReload.getMsisdn());
        return scheduledReloadResponseProcessor;
    }

    private ScheduledReloadResponseProcessor convertToResponseProcessor(CreateScheduledReloadResponse response, ScheduledReloadResponseProcessor scheduledReloadResponseProcessor) {
        scheduledReloadResponseProcessor.setPeriodicity(response.getPeriodicity());
        scheduledReloadResponseProcessor.setAmount(response.getAmount());
        scheduledReloadResponseProcessor.setAnniversary(response.getAniversary());
        scheduledReloadResponseProcessor.setExternalId(response.getExternalId());
        scheduledReloadResponseProcessor.setRecipient(response.getRecipient());
        return scheduledReloadResponseProcessor;
    }

    private ScheduledReloadResponseProcessor convertToResponseProcessor(ChangeScheduledReloadResponse response, ScheduledReloadResponseProcessor scheduledReloadResponseProcessor) {
        scheduledReloadResponseProcessor.setPeriodicity(response.getPeriodicity());
        scheduledReloadResponseProcessor.setAmount(response.getAmount());
        scheduledReloadResponseProcessor.setAnniversary(response.getAniversary());
        scheduledReloadResponseProcessor.setExternalId(response.getExternalId());
        scheduledReloadResponseProcessor.setRecipient(response.getRecipient());
        return scheduledReloadResponseProcessor;
    }

}
