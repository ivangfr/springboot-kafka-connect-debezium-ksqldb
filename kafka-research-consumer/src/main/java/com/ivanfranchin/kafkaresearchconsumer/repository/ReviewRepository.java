package com.ivanfranchin.kafkaresearchconsumer.repository;

import com.ivanfranchin.kafkaresearchconsumer.model.Review;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends ElasticsearchRepository<Review, Long> {
}
