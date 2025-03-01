package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateReviewRequest(
        @Schema(example = "1") @Positive Long researcherId,
        @Schema(example = "1") @Positive Long articleId,
        @Schema(example = "Ln 56: replace the 'a' by 'an'") @NotBlank String comment) {
}
