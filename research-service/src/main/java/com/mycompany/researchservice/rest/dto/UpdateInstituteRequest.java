package com.mycompany.researchservice.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateInstituteRequest {

    @Schema(example = "UCSF")
    private String name;
}
