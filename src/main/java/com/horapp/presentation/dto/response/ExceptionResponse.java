package com.horapp.presentation.dto.response;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExceptionResponse implements Serializable {

    private String backendMessage;
    private HttpStatus status;
    private String method;
    private String endpoint;
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d/M/yyyy HH:mm"));

    public ExceptionResponse() {
    }

    public ExceptionResponse(String backendMessage, HttpStatus status, String method, String endpoint) {
        this.backendMessage = backendMessage;
        this.status = status;
        this.method = method;
        this.endpoint = endpoint;
    }

    public String getBackendMessage() {
        return backendMessage;
    }

    public void setBackendMessage(String backendMessage) {
        this.backendMessage = backendMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
