package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateReviewRequest {

    @Schema(example = "1")
    @Positive
    private Long researcherId;

    @Schema(example = "1")
    @Positive
    private Long articleId;

    @Schema(example = "Ln 56: replace the 'a' by 'an'")
    @NotBlank
    private String comment;
}
