package com.ivanfranchin.researchservice.rest.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleResponse {

    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
