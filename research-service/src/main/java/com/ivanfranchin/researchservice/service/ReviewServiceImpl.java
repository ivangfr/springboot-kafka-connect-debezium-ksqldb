package com.ivanfranchin.researchservice.service;

import com.ivanfranchin.researchservice.repository.ReviewRepository;
import com.ivanfranchin.researchservice.exception.ReviewDeletionException;
import com.ivanfranchin.researchservice.exception.ReviewNotFoundException;
import com.ivanfranchin.researchservice.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review validateAndGetReview(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Review review) {
        try {
            reviewRepository.delete(review);
        } catch (DataIntegrityViolationException e) {
            throw new ReviewDeletionException(review.getId());
        }
    }
}
