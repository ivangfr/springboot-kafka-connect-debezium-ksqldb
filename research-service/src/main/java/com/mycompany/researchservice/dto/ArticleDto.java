package com.mycompany.researchservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDto {

    private Long id;
    private String title;
    private LocalDateTime createdAt;

}
