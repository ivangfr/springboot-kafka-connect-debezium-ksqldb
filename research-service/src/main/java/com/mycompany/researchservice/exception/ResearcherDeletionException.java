package com.mycompany.researchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResearcherDeletionException extends RuntimeException {

    public ResearcherDeletionException(String message) {
        super(message);
    }

}
