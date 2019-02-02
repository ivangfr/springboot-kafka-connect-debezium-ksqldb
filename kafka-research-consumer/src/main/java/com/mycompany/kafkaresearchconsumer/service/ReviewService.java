package com.mycompany.kafkaresearchconsumer.service;

import com.mycompany.kafkaresearchconsumer.model.Review;

import java.util.List;

public interface ReviewService {

    Iterable<Review> saveReviews(List<Review> reviews);

}
