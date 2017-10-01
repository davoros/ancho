package com.davor.ancho.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound extends RuntimeException {

    public NotFound(String reason){
        super("The wanted resources has not been found: " + reason);
    }
}
