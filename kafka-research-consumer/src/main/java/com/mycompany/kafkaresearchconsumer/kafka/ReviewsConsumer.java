package com.mycompany.kafkaresearchconsumer.kafka;

import com.mycompany.kafkaresearchconsumer.model.Review;
import com.mycompany.kafkaresearchconsumer.service.ReviewService;
import com.mycompany.research.avro.ReviewMessage;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ReviewsConsumer {

    private final ReviewService reviewService;
    private final MapperFacade mapperFacade;

    public ReviewsConsumer(ReviewService reviewService, MapperFacade mapperFacade) {
        this.reviewService = reviewService;
        this.mapperFacade = mapperFacade;
    }

    @KafkaListener(
            id = "${kafka.reviews.id}",
            topics = "${kafka.reviews.topic}",
            groupId = "${kafka.reviews.group-id}",
            concurrency = "${kafka.reviews.concurrency}"
    )
    public void listen(List<Message<ReviewMessage>> messages, Acknowledgment ack) {
        List<Review> reviews = messages.stream().map(message -> {
            log.info("Received reviewId={}, partition={}, offset={}",
                    message.getPayload().getREVIEWID(),
                    message.getHeaders().get(KafkaHeaders.RECEIVED_PARTITION_ID),
                    message.getHeaders().get(KafkaHeaders.OFFSET));
            return mapperFacade.map(message.getPayload(), Review.class);
        }).collect(Collectors.toList());

        Iterable<Review> reviewIterable = reviewService.saveReviews(reviews);

        reviewIterable.forEach(review -> log.info("Saved reviewId={}", review.getReviewId()));
        ack.acknowledge();
    }

}
