package com.mycompany.kafkaresearchconsumer.mapper;

import com.mycompany.kafkaresearchconsumer.model.Review;
import com.mycompany.research.avro.ReviewMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    default Review toReview(ReviewMessage reviewMessage) {
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
