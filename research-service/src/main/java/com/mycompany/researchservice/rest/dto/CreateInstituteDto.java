package com.mycompany.researchservice.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateInstituteDto {

    @ApiModelProperty(example = "MIT")
    @NotBlank
    private String name;

}
