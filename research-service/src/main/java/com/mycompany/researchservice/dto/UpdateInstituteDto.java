package com.mycompany.researchservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateInstituteDto {

    @ApiModelProperty(example = "Berkeley2")
    private String name;

}
