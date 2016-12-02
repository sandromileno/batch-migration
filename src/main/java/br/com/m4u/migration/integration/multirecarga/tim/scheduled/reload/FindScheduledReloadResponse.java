package br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload;

import br.com.m4u.migration.reload.model.ScheduledReload;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import java.util.Calendar;
import java.util.List;

/**
 * Created by sandro on 22/11/16.
 */
public class FindScheduledReloadResponse {

    private HttpStatus statusCode;

    private String message;

    @JsonProperty("codigo")
    private Integer code;

    @JsonProperty("objetos")
    private List<ScheduledReloadResponse> objects;

    public FindScheduledReloadResponse() {
    }

    public FindScheduledReloadResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ScheduledReloadResponse getScheduledReloadResponse() {
        for (ScheduledReloadResponse response : objects) {
            return response;
        }
        return null;
    }

    public boolean wasChanged(ScheduledReload scheduledReload) {
        Calendar actualDate = Calendar.getInstance();
        actualDate.setTimeInMillis(scheduledReload.getAnniversary());

        for(ScheduledReloadResponse response : objects) {
            if(response.getRecipient().equals(scheduledReload.getMsisdn())) {
                Calendar newDate = Calendar.getInstance();
                newDate.setTimeInMillis(response.getAnniversary());

                if (response.getAmount().equals(scheduledReload.getAmount()) &&
                        Integer.valueOf(newDate.get(Calendar.DAY_OF_MONTH)).equals(actualDate.get(Calendar.DAY_OF_MONTH))) {
                    return false;
                } else {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean dependenteWasChanged(ScheduledReload scheduledReload) {
        Calendar actualDate = Calendar.getInstance();
        actualDate.setTimeInMillis(scheduledReload.getAnniversary());

        for(ScheduledReloadResponse response : objects) {
            if(response.getRecipient().equals(scheduledReload.getDependent())) {
                Calendar newDate = Calendar.getInstance();
                newDate.setTimeInMillis(response.getAnniversary());

                if (response.getAmount().equals(scheduledReload.getAmount()) &&
                        Integer.valueOf(newDate.get(Calendar.DAY_OF_MONTH)).equals(actualDate.get(Calendar.DAY_OF_MONTH))) {
                    return false;
                } else {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean hasSheduledReload(String receiver) {
        for(ScheduledReloadResponse resp : objects) {
            if(resp.getRecipient().equals(receiver)) {
                return true;
            }
        }
        return false;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<ScheduledReloadResponse> getObjects() {
        return objects;
    }

    public void setObjects(List<ScheduledReloadResponse> objects) {
        this.objects = objects;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
