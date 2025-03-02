package com.ivanfranchin.kafkaresearchconsumer.review.model;

import com.ivanfranchin.research.avro.ReviewMessage;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "reviews", createIndex = false)
public class Review {

    @Id
    private Long reviewId;
    private Long articleId;
    private String articleTitle;
    private Long reviewerId;
    private String reviewerFirstName;
    private String reviewerLastName;
    private Long instituteId;
    private String instituteName;
    private String comment;
    private Long createdAt;

    public static Review from(ReviewMessage reviewMessage) {
        if (reviewMessage == null) {
            return null;
        }
        Review review = new Review();
        review.setReviewId(reviewMessage.getREVIEWID());
        review.setArticleId(reviewMessage.getARTICLEID());
        review.setArticleTitle(reviewMessage.getARTICLETITLE().toString());
        review.setReviewerId(reviewMessage.getREVIEWERID());
        review.setReviewerFirstName(reviewMessage.getREVIEWERFIRSTNAME().toString());
        review.setReviewerLastName(reviewMessage.getREVIEWERLASTNAME().toString());
        review.setInstituteId(reviewMessage.getINSTITUTEID());
        review.setInstituteName(reviewMessage.getINSTITUTENAME().toString());
        review.setComment(reviewMessage.getCOMMENT().toString());
        review.setCreatedAt(reviewMessage.getCREATEDAT());
        return review;
    }
}
