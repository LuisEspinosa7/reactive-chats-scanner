package com.lsoftware.reactive.examples.msvreporter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TechnicalException extends RuntimeException {

    private static final long serialVersionUID = 452382267875788250L;

    private HttpStatus status;

    public TechnicalException(String exception, HttpStatus status) {
        super(exception);
        this.status = status;
    }


    public HttpStatus getStatus() {
        return status;
    }

}
