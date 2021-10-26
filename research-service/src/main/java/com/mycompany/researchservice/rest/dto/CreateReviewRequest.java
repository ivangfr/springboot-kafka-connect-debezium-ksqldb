package com.mycompany.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

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
