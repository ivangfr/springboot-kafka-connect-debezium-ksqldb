package com.mycompany.researchservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateReviewDto {

    @ApiModelProperty(example = "It is a very bad article")
    private String comment;

}
