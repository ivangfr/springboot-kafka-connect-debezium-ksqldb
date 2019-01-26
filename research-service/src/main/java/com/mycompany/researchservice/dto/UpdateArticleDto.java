package com.mycompany.researchservice.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateArticleDto {

    @ApiModelProperty(example = "Advantages of using Spring Boot over Play Framework")
    private String title;

}
