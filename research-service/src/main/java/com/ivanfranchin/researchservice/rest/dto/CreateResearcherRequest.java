package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateResearcherRequest {

    @Schema(example = "Ivan")
    @NotBlank
    private String firstName;

    @Schema(example = "Franchin")
    @NotBlank
    private String lastName;

    @Schema(example = "1")
    @Positive
    private Long instituteId;
}
