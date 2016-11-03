package br.com.m4u.migration.reload.processor;

import br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload.ScheduledReloadService;
import br.com.m4u.migration.reload.model.ScheduledReload;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sandro on 01/11/16.
 */
public class ScheduledReloadItemProcessor implements ItemProcessor<ScheduledReload, ScheduledReload> {

    @Autowired
    private RestTemplate restClient;

    @Autowired
    private ScheduledReloadService service;

    @Override
    public ScheduledReload process(ScheduledReload scheduledReload) throws Exception {
        return null;
    }
}
