package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateReviewRequest {

    @Schema(example = "Ln 106: replace the 'for' by 'to'")
    private String comment;
}
