package com.mycompany.researchservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateArticleDto {

    @ApiModelProperty(example = "Advantages of using KSQL over Kafka Streams")
    @NotBlank
    private String title;

}
