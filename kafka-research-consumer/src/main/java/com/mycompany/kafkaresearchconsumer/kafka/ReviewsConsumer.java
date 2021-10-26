package com.mycompany.kafkaresearchconsumer.kafka;

import com.mycompany.kafkaresearchconsumer.mapper.ReviewMapper;
import com.mycompany.kafkaresearchconsumer.model.Review;
import com.mycompany.kafkaresearchconsumer.service.ReviewService;
import com.mycompany.research.avro.ReviewMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReviewsConsumer {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @KafkaListener(
            id = "${spring.kafka.consumer.client-id}",
            groupId = "${spring.kafka.consumer.group-id}",
            topics = "${spring.kafka.consumer.topic}"
    )
    public void listen(List<Message<ReviewMessage>> messages, Acknowledgment ack) {
        log.info("Received batch of messages with size: {}", messages.size());
        List<Review> reviews = messages.stream()
                .peek(this::logMessageReceived)
                .map(message -> reviewMapper.toReview(message.getPayload()))
                .collect(Collectors.toList());

        reviewService.saveReviews(reviews).forEach(review -> log.info("Saved reviewId={}", review.getReviewId()));
        ack.acknowledge();
    }

    private void logMessageReceived(Message<ReviewMessage> message) {
        log.info("Received reviewId {} from '{} {}' for the article '{}', partition={}, offset={}",
                message.getPayload().getREVIEWID(),
                message.getPayload().getREVIEWERFIRSTNAME(),
                message.getPayload().getREVIEWERLASTNAME(),
                message.getPayload().getARTICLETITLE(),
                message.getHeaders().get(KafkaHeaders.RECEIVED_PARTITION_ID),
                message.getHeaders().get(KafkaHeaders.OFFSET));
    }
}
