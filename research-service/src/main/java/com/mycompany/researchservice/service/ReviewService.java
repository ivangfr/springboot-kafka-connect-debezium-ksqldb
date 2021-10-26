package com.mycompany.researchservice.service;

import com.mycompany.researchservice.model.Review;

public interface ReviewService {

    Review validateAndGetReview(Long id);

    Review saveReview(Review review);

    void deleteReview(Review review);
}
