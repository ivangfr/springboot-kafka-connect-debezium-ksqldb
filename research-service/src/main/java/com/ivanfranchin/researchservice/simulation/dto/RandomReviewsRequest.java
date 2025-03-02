package com.ivanfranchin.researchservice.simulation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RandomReviewsRequest(
        @Schema(example = "10") Integer total,
        @Schema(example = "100") Integer sleep) {
}
