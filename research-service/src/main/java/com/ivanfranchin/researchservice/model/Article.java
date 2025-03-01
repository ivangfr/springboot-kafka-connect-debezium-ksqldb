package com.ivanfranchin.researchservice.model;

import com.ivanfranchin.researchservice.rest.dto.CreateArticleRequest;
import com.ivanfranchin.researchservice.rest.dto.UpdateArticleRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onPrePersist() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static Article from(CreateArticleRequest createArticleRequest) {
        Article article = new Article();
        article.setTitle(createArticleRequest.title());
        return article;
    }

    public static void updateFrom(UpdateArticleRequest updateArticleRequest, Article article) {
        if (updateArticleRequest.title() != null) {
            article.setTitle(updateArticleRequest.title());
        }
    }
}
