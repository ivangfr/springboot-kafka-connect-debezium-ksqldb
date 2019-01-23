package com.mycompany.researchservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateResearcherDto {

    @ApiModelProperty(example = "Ivan2")
    private String firstName;

    @ApiModelProperty(position = 2, example = "Franchin2")
    private String lastName;

}
