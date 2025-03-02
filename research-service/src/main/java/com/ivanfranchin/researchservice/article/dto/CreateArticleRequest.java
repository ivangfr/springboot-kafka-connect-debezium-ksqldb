package com.ivanfranchin.researchservice.article.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateArticleRequest(
        @Schema(example = "Advantages of using ksqlDB over Kafka Streams") @NotBlank String title) {
}
