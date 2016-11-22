package br.com.m4u.migration.integration.multirecarga.tim.refill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sandro on 22/11/16.
 */
@Service
public class RefillReloadService {

    @Autowired
    private RestTemplate restClient;

    @Autowired
    private Environment env;
}
