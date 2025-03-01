package com.ivanfranchin.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateInstituteRequest(@Schema(example = "UCSF") String name) {
}
