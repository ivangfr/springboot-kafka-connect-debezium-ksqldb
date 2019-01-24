package com.mycompany.researchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstituteNotFoundException extends RuntimeException {

    public InstituteNotFoundException(String message) {
        super(message);
    }
}
