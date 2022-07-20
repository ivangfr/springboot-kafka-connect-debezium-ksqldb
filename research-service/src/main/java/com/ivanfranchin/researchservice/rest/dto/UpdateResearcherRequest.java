package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateResearcherRequest {

    @Schema(example = "Ivan2")
    private String firstName;

    @Schema(example = "Franchin2")
    private String lastName;

    @Schema(example = "2")
    private Long instituteId;
}
