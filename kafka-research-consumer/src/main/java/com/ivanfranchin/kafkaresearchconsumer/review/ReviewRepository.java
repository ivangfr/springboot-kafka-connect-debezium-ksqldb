package com.ivanfranchin.kafkaresearchconsumer.review;

import com.ivanfranchin.kafkaresearchconsumer.review.model.Review;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ElasticsearchRepository<Review, Long> {
}
