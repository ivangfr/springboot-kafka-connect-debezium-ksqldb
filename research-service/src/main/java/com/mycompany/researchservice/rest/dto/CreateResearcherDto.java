package com.mycompany.researchservice.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CreateResearcherDto {

    @ApiModelProperty(example = "Ivan")
    @NotBlank
    private String firstName;

    @ApiModelProperty(position = 2, example = "Franchin")
    @NotBlank
    private String lastName;

    @ApiModelProperty(position = 3, example = "1")
    @Positive
    private Long instituteId;

}
