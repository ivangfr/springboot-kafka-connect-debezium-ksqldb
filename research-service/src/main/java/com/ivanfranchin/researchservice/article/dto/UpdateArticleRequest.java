package com.ivanfranchin.researchservice.article.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateArticleRequest(
        @Schema(example = "Advantages of using Spring Boot over Play Framework") String title) {
}
