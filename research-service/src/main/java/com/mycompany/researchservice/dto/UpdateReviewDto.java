package com.mycompany.researchservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateReviewDto {

    @ApiModelProperty(example = "Ln 106: replace the 'for' by 'to'")
    private String comment;

}
