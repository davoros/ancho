package com.davor.ancho.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Unauthorized extends RuntimeException {

    public Unauthorized(String reason){
        super("You are unauthorized to access the resource: " + reason);
    }
}
