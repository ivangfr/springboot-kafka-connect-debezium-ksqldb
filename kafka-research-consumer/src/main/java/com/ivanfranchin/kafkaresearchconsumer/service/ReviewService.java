package com.ivanfranchin.kafkaresearchconsumer.service;

import com.ivanfranchin.kafkaresearchconsumer.model.Review;

import java.util.List;

public interface ReviewService {

    Iterable<Review> saveReviews(List<Review> reviews);
}
