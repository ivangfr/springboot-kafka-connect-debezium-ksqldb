package com.mycompany.researchservice.service;

import com.mycompany.researchservice.exception.ReviewNotFoundException;
import com.mycompany.researchservice.model.Review;
import com.mycompany.researchservice.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review validateAndGetReview(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException(String.format("Review with id %s not found", id)));
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }
}
