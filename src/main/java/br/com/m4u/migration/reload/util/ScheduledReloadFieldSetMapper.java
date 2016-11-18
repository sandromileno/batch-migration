package br.com.m4u.migration.reload.util;

import br.com.m4u.migration.reload.enums.ChannelEnum;
import br.com.m4u.migration.reload.model.ScheduledReload;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.util.Calendar;

/**
 * Created by sandro on 07/11/16.
 */
@Component
public class ScheduledReloadFieldSetMapper implements FieldSetMapper<ScheduledReload> {
    @Override
    public ScheduledReload mapFieldSet(FieldSet fieldSet) throws BindException {
        ScheduledReload scheduledReload = new ScheduledReload();
        scheduledReload.setMsisdn(fieldSet.readString("msisdn"));
        scheduledReload.setAmount(fieldSet.readInt("amount")*100);
        scheduledReload.setChannel(ChannelEnum.getChannel(fieldSet.readString("channel")));
        scheduledReload.setAnniversary(calculateAnniversary(fieldSet.readInt("anniversary")));
        return scheduledReload;
    }

    private Long calculateAnniversary(Integer dayOfAnniversary) {

        Calendar calendar = Calendar.getInstance();
        Integer today = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfAnniversary);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (today > dayOfAnniversary) {
            calendar.add(Calendar.MONTH, 1);
            return calendar.getTimeInMillis();
        } else if (today < dayOfAnniversary) {
            return calendar.getTimeInMillis();
        } else if (today.equals(dayOfAnniversary)) {
            return calendar.getTimeInMillis();
        }
        return null;
    }

}
