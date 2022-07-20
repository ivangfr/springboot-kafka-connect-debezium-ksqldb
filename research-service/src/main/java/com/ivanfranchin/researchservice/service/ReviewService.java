package com.ivanfranchin.researchservice.service;

import com.ivanfranchin.researchservice.model.Review;

public interface ReviewService {

    Review validateAndGetReview(Long id);

    Review saveReview(Review review);

    void deleteReview(Review review);
}
