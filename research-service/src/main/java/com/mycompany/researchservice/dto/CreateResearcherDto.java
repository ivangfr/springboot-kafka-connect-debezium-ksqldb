package com.mycompany.researchservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateResearcherDto {

    @ApiModelProperty(example = "Ivan")
    @NotBlank
    private String firstName;

    @ApiModelProperty(position = 2, example = "Franchin")
    @NotBlank
    private String lastName;

}
