package com.mycompany.researchservice.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private Long id;
    private Long researcherId;
    private Long articleId;
    private String comment;

}
