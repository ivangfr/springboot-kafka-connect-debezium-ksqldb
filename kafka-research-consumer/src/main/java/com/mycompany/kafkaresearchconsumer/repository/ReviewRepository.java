package com.mycompany.kafkaresearchconsumer.repository;

import com.mycompany.kafkaresearchconsumer.model.Review;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ReviewRepository extends ElasticsearchRepository<Review, Long> {
}
