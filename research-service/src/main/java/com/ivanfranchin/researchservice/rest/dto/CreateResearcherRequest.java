package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CreateResearcherRequest(
        @Schema(example = "Ivan") @NotBlank String firstName,
        @Schema(example = "Franchin") @NotBlank String lastName,
        @Schema(example = "1") @Positive Long instituteId) {
}
