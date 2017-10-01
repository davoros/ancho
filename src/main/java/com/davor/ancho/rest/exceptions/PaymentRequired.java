package com.davor.ancho.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
public class PaymentRequired extends RuntimeException {

    public PaymentRequired(String reason){
        super("You have to pay to see the content: " + reason);
    }
}
