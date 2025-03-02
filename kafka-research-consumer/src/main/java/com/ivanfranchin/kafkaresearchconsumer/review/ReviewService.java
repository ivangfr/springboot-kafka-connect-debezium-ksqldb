package com.ivanfranchin.kafkaresearchconsumer.review;

import com.ivanfranchin.kafkaresearchconsumer.review.model.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Iterable<Review> saveReviews(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }
}
