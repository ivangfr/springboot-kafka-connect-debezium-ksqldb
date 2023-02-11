package com.ivanfranchin.researchservice.rest;

import com.ivanfranchin.researchservice.mapper.ReviewMapper;
import com.ivanfranchin.researchservice.model.Review;
import com.ivanfranchin.researchservice.rest.dto.CreateReviewRequest;
import com.ivanfranchin.researchservice.rest.dto.ReviewResponse;
import com.ivanfranchin.researchservice.rest.dto.UpdateReviewRequest;
import com.ivanfranchin.researchservice.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping("/{id}")
    public ReviewResponse getReview(@PathVariable Long id) {
        Review review = reviewService.validateAndGetReview(id);
        return reviewMapper.toReviewResponse(review);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ReviewResponse createReview(@Valid @RequestBody CreateReviewRequest createReviewRequest) {
        Review review = reviewMapper.toReview(createReviewRequest);
        review = reviewService.saveReview(review);
        return reviewMapper.toReviewResponse(review);
    }

    @PatchMapping("/{id}")
    public ReviewResponse updateReview(@PathVariable Long id, @Valid @RequestBody UpdateReviewRequest updateReviewRequest) {
        Review review = reviewService.validateAndGetReview(id);
        reviewMapper.updateReviewFromRequest(updateReviewRequest, review);
        review = reviewService.saveReview(review);
        return reviewMapper.toReviewResponse(review);
    }

    @DeleteMapping("/{id}")
    public ReviewResponse deleteReview(@PathVariable Long id) {
        Review review = reviewService.validateAndGetReview(id);
        reviewService.deleteReview(review);
        return reviewMapper.toReviewResponse(review);
    }
}
