package com.ivanfranchin.researchservice.rest.dto;

import lombok.Data;

@Data
public class ReviewResponse {

    private Long id;
    private Long researcherId;
    private Long articleId;
    private String comment;
}
