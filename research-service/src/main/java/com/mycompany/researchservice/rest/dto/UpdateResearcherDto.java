package com.mycompany.researchservice.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateResearcherDto {

    @ApiModelProperty(example = "Ivan2")
    private String firstName;

    @ApiModelProperty(position = 1, example = "Franchin2")
    private String lastName;

    @ApiModelProperty(position = 2, example = "2")
    private Long instituteId;

}
