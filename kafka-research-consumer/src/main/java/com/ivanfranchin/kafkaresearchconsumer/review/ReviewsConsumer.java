package com.ivanfranchin.kafkaresearchconsumer.review;

import com.ivanfranchin.kafkaresearchconsumer.review.model.Review;
import com.ivanfranchin.research.avro.ReviewMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReviewsConsumer {

    private final ReviewService reviewService;

    @KafkaListener(
            id = "${spring.kafka.consumer.client-id}",
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.consumer.topic}"
    )
    public void listen(List<Message<ReviewMessage>> messages, Acknowledgment ack) {
        log.info("Received batch of messages with size: {}", messages.size());
        List<Review> reviews = messages.stream()
                .peek(this::logMessageReceived)
                .map(message -> Review.from(message.getPayload()))
                .toList();

        reviewService.saveReviews(reviews).forEach(review -> log.info("Saved reviewId={}", review.getReviewId()));
        ack.acknowledge();
    }

    private void logMessageReceived(Message<ReviewMessage> message) {
        log.info("Received reviewId {} from '{} {}' for the article '{}', partition={}, offset={}",
                message.getPayload().getREVIEWID(),
                message.getPayload().getREVIEWERFIRSTNAME(),
                message.getPayload().getREVIEWERLASTNAME(),
                message.getPayload().getARTICLETITLE(),
                message.getHeaders().get(KafkaHeaders.RECEIVED_PARTITION),
                message.getHeaders().get(KafkaHeaders.OFFSET));
    }
}
