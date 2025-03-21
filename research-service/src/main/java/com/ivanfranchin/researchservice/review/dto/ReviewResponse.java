package com.ivanfranchin.researchservice.review.dto;

import com.ivanfranchin.researchservice.review.model.Review;

public record ReviewResponse(Long id, Long researcherId, Long articleId, String comment) {

    public static ReviewResponse from(Review review) {
        return new ReviewResponse(review.getId(), review.getResearcher().getId(), review.getArticle().getId(), review.getComment());
    }
}
