package br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sandro on 03/11/16.
 */
@Service
public class ScheduledReloadService {

    @Autowired
    private RestTemplate restClient;

    @Autowired
    private Environment env;

    public CreateScheduledReloadResponse createScheduledReload(CreateScheduledReloadRequest request, String channel) {
        CreateScheduledReloadResponse response;
        try {
            response = restClient.postForObject(env.getProperty("tim.endpoint.fronted.create.scheduled.reload"), request, CreateScheduledReloadResponse.class, channel);
        } catch (HttpClientErrorException ex) {
            response = new CreateScheduledReloadResponse(ex.getStatusCode(), ex.getMessage());
        } catch(RestClientException rex) {
            response = new CreateScheduledReloadResponse(HttpStatus.INTERNAL_SERVER_ERROR, rex.getMessage());
        } catch (Exception e) {
            response = new CreateScheduledReloadResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        return response;
    }
}