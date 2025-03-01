package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record RandomReviewsRequest(
        @Schema(example = "10") Integer total,
        @Schema(example = "100") Integer sleep) {
}
