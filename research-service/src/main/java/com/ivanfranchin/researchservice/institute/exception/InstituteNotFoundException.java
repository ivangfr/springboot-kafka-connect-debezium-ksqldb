package com.ivanfranchin.researchservice.institute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstituteNotFoundException extends RuntimeException {

    public InstituteNotFoundException(Long id) {
        super(String.format("Institute with id %s not found", id));
    }
}
