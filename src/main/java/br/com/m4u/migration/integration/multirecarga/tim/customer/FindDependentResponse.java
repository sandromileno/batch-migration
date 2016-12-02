package br.com.m4u.migration.integration.multirecarga.tim.customer;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

/**
 * Created by sandro on 02/12/16.
 */
public class FindDependentResponse {

    private HttpStatus statusCode;

    private String message;

    @JsonProperty("msisdn")
    private String msisdn;

    public FindDependentResponse() {
    }

    public FindDependentResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public boolean wasSuccessful() {
        return msisdn != null && msisdn.length() > 1;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
