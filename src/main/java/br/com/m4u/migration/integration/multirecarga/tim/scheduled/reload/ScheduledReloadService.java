package br.com.m4u.migration.integration.multirecarga.tim.scheduled.reload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sandro on 03/11/16.
 */
@Service
public class ScheduledReloadService {

    @Autowired
    private RestTemplate restClient;
//    @Value("${tim.endpoint.fronted.create.scheduled.reload}")
    @Autowired
    private Environment env;

    public ResponseEntity createScheduledReload(CreateScheduledReloadRequest request) {
        ResponseEntity response = restClient.postForEntity(null, request, ResponseEntity.class);
        return response;
    }

}
