package com.mycompany.researchservice.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDto {

    private Long id;
    private String title;
    private LocalDateTime createdAt;

}
