package com.mycompany.researchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArticleDeletionException extends RuntimeException {

    public ArticleDeletionException(String message) {
        super(message);
    }

}
