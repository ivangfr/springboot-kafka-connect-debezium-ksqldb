package com.mycompany.kafkaresearchconsumer.service;

import com.mycompany.kafkaresearchconsumer.model.Review;
import com.mycompany.kafkaresearchconsumer.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Iterable<Review> saveReviews(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }

}
