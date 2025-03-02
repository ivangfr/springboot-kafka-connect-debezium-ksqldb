package com.ivanfranchin.researchservice.researcher.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResearcherDeletionException extends RuntimeException {

    public ResearcherDeletionException(Long id) {
        super(String.format("Researcher with id %s cannot be deleted", id));
    }
}
