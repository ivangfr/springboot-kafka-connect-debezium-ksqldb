package com.ivanfranchin.researchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ReviewDeletionException extends RuntimeException {

    public ReviewDeletionException(Long id) {
        super(String.format("Review with id %s cannot be deleted", id));
    }
}
