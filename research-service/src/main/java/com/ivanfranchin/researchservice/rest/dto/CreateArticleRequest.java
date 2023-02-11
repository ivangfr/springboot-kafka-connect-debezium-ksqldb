package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateArticleRequest {

    @Schema(example = "Advantages of using ksqlDB over Kafka Streams")
    @NotBlank
    private String title;
}
