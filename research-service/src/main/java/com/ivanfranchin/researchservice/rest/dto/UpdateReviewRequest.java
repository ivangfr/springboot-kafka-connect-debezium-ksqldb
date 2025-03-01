package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateReviewRequest(@Schema(example = "Ln 106: replace the 'for' by 'to'") String comment) {
}
