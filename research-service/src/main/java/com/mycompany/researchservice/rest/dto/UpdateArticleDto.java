package com.mycompany.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateArticleDto {

    @Schema(example = "Advantages of using Spring Boot over Play Framework")
    private String title;

}
