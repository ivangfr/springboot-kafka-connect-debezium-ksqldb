package com.mycompany.researchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReviewDeletionException extends RuntimeException {

    public ReviewDeletionException(String message) {
        super(message);
    }

}
