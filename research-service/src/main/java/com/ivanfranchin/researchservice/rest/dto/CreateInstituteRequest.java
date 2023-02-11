package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateInstituteRequest {

    @Schema(example = "MIT")
    @NotBlank
    private String name;
}
