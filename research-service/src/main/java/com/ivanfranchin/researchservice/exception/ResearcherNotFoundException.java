package com.ivanfranchin.researchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResearcherNotFoundException extends RuntimeException {

    public ResearcherNotFoundException(Long id) {
        super(String.format("Researcher with id %s not found", id));
    }
}
