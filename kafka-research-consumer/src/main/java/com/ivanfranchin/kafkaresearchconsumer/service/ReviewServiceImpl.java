package com.ivanfranchin.kafkaresearchconsumer.service;

import com.ivanfranchin.kafkaresearchconsumer.model.Review;
import com.ivanfranchin.kafkaresearchconsumer.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Iterable<Review> saveReviews(List<Review> reviews) {
        return reviewRepository.saveAll(reviews);
    }
}
