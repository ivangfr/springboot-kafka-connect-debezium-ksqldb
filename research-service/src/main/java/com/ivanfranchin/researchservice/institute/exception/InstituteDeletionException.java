package com.ivanfranchin.researchservice.institute.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class InstituteDeletionException extends RuntimeException {

    public InstituteDeletionException(Long id) {
        super(String.format("Institute with id %s cannot be deleted", id));
    }
}
