package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateResearcherRequest(
        @Schema(example = "Ivan2") String firstName,
        @Schema(example = "Franchin2") String lastName,
        @Schema(example = "2") Long instituteId) {
}
