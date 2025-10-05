package br.edu.infinet.sergiotransacoesapi.model.domain.exceptions;

import org.springframework.http.HttpStatus;

public class ExternalServiceException extends RuntimeException {
    private final HttpStatus status;

    public ExternalServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}