package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateInstituteRequest(@Schema(example = "MIT") @NotBlank String name) {
}
