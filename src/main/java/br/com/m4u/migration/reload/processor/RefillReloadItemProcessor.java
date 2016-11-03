package br.com.m4u.migration.reload.processor;

import br.com.m4u.migration.reload.model.RefillReload;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by sandro on 03/11/16.
 */
public class RefillReloadItemProcessor implements ItemProcessor<RefillReload, RefillReload> {
    @Override
    public RefillReload process(RefillReload refillReload) throws Exception {
        return null;
    }
}
