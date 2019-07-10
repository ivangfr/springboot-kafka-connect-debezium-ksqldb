package com.mycompany.researchservice.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateInstituteDto {

    @ApiModelProperty(example = "UCSF")
    private String name;

}
