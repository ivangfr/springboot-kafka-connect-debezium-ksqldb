package com.mycompany.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CreateResearcherDto {

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
