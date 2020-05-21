package com.mycompany.researchservice.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RandomReviewsDto {

    @ApiModelProperty(example = "10")
    private Integer total;

    @ApiModelProperty(position = 1, example = "100")
    private Integer sleep;

}
