package com.mycompany.kafkaresearchconsumer.model;

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
}
