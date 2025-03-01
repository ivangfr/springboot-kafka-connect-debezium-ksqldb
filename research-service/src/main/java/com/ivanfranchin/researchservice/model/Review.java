package com.ivanfranchin.researchservice.model;

import com.ivanfranchin.researchservice.rest.dto.CreateReviewRequest;
import com.ivanfranchin.researchservice.rest.dto.UpdateReviewRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "researcher_id", nullable = false, foreignKey = @ForeignKey(name = "FK_RESEARCHER"))
    private Researcher researcher;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false, foreignKey = @ForeignKey(name = "FK_ARTICLE"))
    private Article article;

    @Column(nullable = false)
    private String comment;

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

    public static Review from(CreateReviewRequest createReviewRequest) {
        Review review = new Review();
        review.setComment(createReviewRequest.comment());
        return review;
    }

    public static void updateFrom(UpdateReviewRequest updateReviewRequest, Review review) {
        if (updateReviewRequest.comment() != null) {
            review.setComment(updateReviewRequest.comment());
        }
    }
}
