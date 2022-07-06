package com.casestudy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PurchasingSpecialistNotFoundException extends RuntimeException {

    public PurchasingSpecialistNotFoundException(String message) {
        super(message);
    }
}
