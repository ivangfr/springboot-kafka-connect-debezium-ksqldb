package com.ivanfranchin.researchservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ArticleDeletionException extends RuntimeException {

    public ArticleDeletionException(Long id) {
        super(String.format("Article with id %s cannot be deleted", id));
    }
}
