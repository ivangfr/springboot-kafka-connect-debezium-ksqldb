package com.ivanfranchin.researchservice.rest.dto;

import com.ivanfranchin.researchservice.model.Article;

import java.time.LocalDateTime;

public record ArticleResponse(Long id, String title, LocalDateTime createdAt) {

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(article.getId(), article.getTitle(), article.getCreatedAt());
    }
}
