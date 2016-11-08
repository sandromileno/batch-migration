package br.com.m4u.migration.reload.post.processor;

/**
 * Created by sandro on 07/11/16.
 */
public class ResponseProcessor {

    private String status;
    private String responseBody;

    public ResponseProcessor() {
    }

    public ResponseProcessor(String status, String responseBody) {
        this.status = status;
        this.responseBody = responseBody;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}