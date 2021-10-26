package com.mycompany.kafkaresearchconsumer.service;

import com.mycompany.kafkaresearchconsumer.model.Review;
import com.mycompany.kafkaresearchconsumer.repository.ReviewRepository;
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
