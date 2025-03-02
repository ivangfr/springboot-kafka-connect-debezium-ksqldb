package com.ivanfranchin.researchservice.institute.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateInstituteRequest(@Schema(example = "UCSF") String name) {
}
