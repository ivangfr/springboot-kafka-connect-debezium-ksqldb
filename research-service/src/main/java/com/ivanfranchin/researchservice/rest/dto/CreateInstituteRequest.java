package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateInstituteRequest {

    @Schema(example = "MIT")
    @NotBlank
    private String name;
}
