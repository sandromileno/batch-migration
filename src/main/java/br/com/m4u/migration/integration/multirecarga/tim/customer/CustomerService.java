package br.com.m4u.migration.integration.multirecarga.tim.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sandro on 03/11/16.
 */
@Service
public class CustomerService {

    @Autowired
    private RestTemplate restClient;

    @Autowired
    private Environment env;

    public CustomerService() {
    }

    public FindCustomerResponse findCustomer(String msisdn) {
        FindCustomerResponse response;
        try {
            response = restClient.getForObject(env.getProperty("tim.endpoint.find.customer"), FindCustomerResponse.class, msisdn);
        } catch (HttpClientErrorException ex) {
            response = new FindCustomerResponse();
            response.setMessage(ex.getMessage());
        } catch (Exception e) {
            response = new FindCustomerResponse();
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
