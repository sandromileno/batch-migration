package br.com.m4u.migration.reload.util;

import br.com.m4u.migration.reload.model.ScheduledReload;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;

/**
 * Created by sandro on 07/11/16.
 */
public class ScheduledReloadLineMapper extends DefaultLineMapper<ScheduledReload> {

    @Override
    public ScheduledReload mapLine(String line, int lineNumber) throws Exception {
        return super.mapLine(line, lineNumber);
    }

    @Override
    public void setFieldSetMapper(FieldSetMapper<ScheduledReload> fieldSetMapper) {
        super.setFieldSetMapper(fieldSetMapper);
    }
}
