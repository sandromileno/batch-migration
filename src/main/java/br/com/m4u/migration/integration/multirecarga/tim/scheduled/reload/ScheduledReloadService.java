package br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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

    public ResponseEntity createScheduledReload(CreateScheduledReloadRequest request, String channel) {
        ResponseEntity response = null;
        try {
            response = restClient.postForObject(env.getProperty("tim.endpoint.fronted.create.scheduled.reload"), request, ResponseEntity.class, channel);
        } catch (HttpClientErrorException ex) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}