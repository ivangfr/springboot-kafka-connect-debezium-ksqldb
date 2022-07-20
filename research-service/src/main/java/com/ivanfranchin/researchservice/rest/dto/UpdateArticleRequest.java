package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateArticleRequest {

    @Schema(example = "Advantages of using Spring Boot over Play Framework")
    private String title;
}
